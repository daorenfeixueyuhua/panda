package com.daoren.log.aspect;

import com.daoren.log.annotation.Log;
import com.daoren.log.entity.SysOperateLog;
import com.daoren.log.enums.BusinessStatus;
import com.daoren.log.service.ISysOperateLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * log切面
 * 执行：@Around => @AfterReturning => @Before
 * todo 日志切面失效
 *
 * @author peng_da
 * @version :
 * @date 2022/2/11 17:35
 * @since :
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private ISysOperateLogService logService;

    @Pointcut("@annotation(com.daoren.log.annotation.Log)")
    public void logPointcut() {
    }

    @Before("logPointcut() && @annotation(log)")
    public void before(JoinPoint joinPoint, Log log) {
        final Object[] args = joinPoint.getArgs();
        System.out.println(log);
    }

    @After("logPointcut() && @annotation(log)")
    public void after(JoinPoint joinPoint, Log log) {
        SysOperateLog logInfo = generateLog(joinPoint, log);
        logInfo.setOperateEndTime(LocalDateTime.now());
        logInfo.setOperateBusinessStatus(BusinessStatus.SUCCESS);
        logService.save(logInfo);
    }

    @AfterThrowing(value = "logPointcut() && @annotation(log)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex, Log log) {
        SysOperateLog logInfo = generateLog(joinPoint, log);
        logInfo.setOperateEndTime(LocalDateTime.now());
        logInfo.setOperateBusinessStatus(BusinessStatus.FAIL);
        logInfo.setOperateErrorMessage(ex.getMessage());
        logService.save(logInfo);
    }

    @AfterReturning("logPointcut() && @annotation(log)")
    public void afterReturning(JoinPoint joinPoint, Log log) {
        System.out.println(log);
    }

    @Around("logPointcut() && @annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        return joinPoint.proceed();
    }

    private SysOperateLog generateLog(JoinPoint joinPoint, Log log) {
        final Object[] args = joinPoint.getArgs();
        SysOperateLog logInfo = new SysOperateLog();
        logInfo.setOperateUserId("panda");
        logInfo.setOperateUserName("panda");
        logInfo.setOperateParams(Arrays.toString(args));
        logInfo.setOperateStartTime(LocalDateTime.now());
        logInfo.setOperateType(log.operatorType());
        logInfo.setOperateBusinessType(log.businessType());
        logInfo.setOperateAddress("");
        logInfo.setOperateTitle(log.title());
        logInfo.setOperateUrl("");
        return logInfo;
    }

}
