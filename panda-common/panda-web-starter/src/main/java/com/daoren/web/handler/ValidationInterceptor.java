package com.daoren.web.handler;

import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 平铺参数校验
 *
 * @author peng_da
 * @version :
 * @date 2022/5/27 10:36
 * @since :
 */
public class ValidationInterceptor implements HandlerInterceptor, InitializingBean {

    private final Map<MethodParameter, HandlerMethodArgumentResolver> argumentResolverCache
            = new ConcurrentHashMap<>(256);
    private final Map<Class<?>, Set<Method>> initBindCache
            = new ConcurrentHashMap<>(64);
    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;
    @Autowired
    private RequestMappingHandlerAdapter adapter;
    private List<HandlerMethodArgumentResolver> argumentResolvers;

    @Override
    public void afterPropertiesSet() throws Exception {
        argumentResolvers = adapter.getArgumentResolvers();
    }

    private HandlerMethodArgumentResolver getArgumentResolver(MethodParameter parameter) {
        HandlerMethodArgumentResolver resolver = this.argumentResolverCache.get(parameter);
        if (resolver == null) {
            for (HandlerMethodArgumentResolver argumentResolver : this.argumentResolvers) {
                if (argumentResolver.supportsParameter(parameter)) {
                    resolver = argumentResolver;
                    this.argumentResolverCache.put(parameter, resolver);
                    break;
                }
            }
        }
        return resolver;
    }

    private WebDataBinderFactory getDataBinderFactory(HandlerMethod handlerMethod) {
        Class<?> handlerType = handlerMethod.getBeanType();
        Set<Method> methods = this.initBindCache.get(handlerType);
        if (methods == null || methods.size() == 0) {
            methods = MethodIntrospector.selectMethods(handlerType, RequestMappingHandlerAdapter.INIT_BINDER_METHODS);
            this.initBindCache.put(handlerType, methods);
        }
        List<InvocableHandlerMethod> initBinderMethods = new ArrayList<>();
        for (Method method : methods) {
            Object bean = handlerMethod.getBean();
            initBinderMethods.add(new InvocableHandlerMethod(bean, method));
        }
        return new ServletRequestDataBinderFactory(initBinderMethods, adapter.getWebBindingInitializer());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Validated validated = method.getMethodAnnotation(Validated.class);
            // todo 导致无法校验实体的中的字段
            if (validated != null) {
                ValidatorImpl validatorImpl = (ValidatorImpl) validatorFactoryBean.getValidator();
                MethodParameter[] parameters = method.getMethodParameters();
                Object[] parametersValues = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    MethodParameter parameter = parameters[i];
                    Annotation[] annotations = parameter.getMethodAnnotations();
                    if (annotations.length != 0) {
                        HandlerMethodArgumentResolver resolver = getArgumentResolver(parameter);
                        Assert.notNull(resolver, "Unknown parameter type [" + parameter.getParameterType().getName() + "]");
                        ModelAndViewContainer container = new ModelAndViewContainer();
                        container.addAllAttributes(RequestContextUtils.getInputFlashMap(request));

                        WebDataBinderFactory webDataBinderFactory = getDataBinderFactory(method);

                        Object value = resolver.resolveArgument(parameter, container, new ServletWebRequest(request, response), webDataBinderFactory);
                        parametersValues[i] = value;
                    }
                }
                Set<ConstraintViolation<Object>> violations = validatorImpl.validateParameters(method.getBean(), method.getMethod(), parametersValues, validated.value());
                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException(violations);
                }
            }
        }
        return true;
    }
}
