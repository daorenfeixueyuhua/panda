package com.daoren.web.handler;

import com.daoren.common.entity.Result;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * 响应封装
 *
 * @author peng_da
 * @version :
 * @date 2022/5/27 9:35
 * @since :
 */
@RestControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        final ResponseResult annotation = methodParameter.getMethod().getAnnotation(ResponseResult.class);
        return annotation != null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        final Method method = methodParameter.getMethod();
        final ResponseResult annotation = method.getAnnotation(ResponseResult.class);
        final String message = annotation.message();
        return Result.<Object>success(message, o);
    }
}