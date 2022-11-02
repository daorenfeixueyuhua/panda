package com.daoren.web.handler;

import com.daoren.common.base.context.TokenInfoContext;
import com.daoren.common.base.exception.business.BusinessRuntimeException;
import com.daoren.web.annotation.RepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 防重复提交策略
 *
 * @author peng_da
 * @date 2022/11/1 16:04
 */
@Slf4j
@Aspect
@Component
public class RepeatSubmitHandler {
    public static final String REPEAT_PREFIX = "REPEAT_";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.daoren.web.annotation.RepeatSubmit)")
    public void point() {
    }

    @Around("point() && @annotation(annotation)")
    public Object doAround(ProceedingJoinPoint point, final RepeatSubmit annotation) throws Throwable {
        Object result = null;

        final HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        final String uri = request.getRequestURI();
        final String token = TokenInfoContext.token.get();
        // todo 待@User注解完成之后做进一步优化
        final String key = REPEAT_PREFIX + uri + "_" + "panda";
        if (!redisTemplate.opsForValue().setIfAbsent(key, "1", annotation.interval(), TimeUnit.MILLISECONDS)) {
            throw new BusinessRuntimeException(annotation.message());
        }
        result = point.proceed();
        return result;
    }
}
