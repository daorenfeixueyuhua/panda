package com.daoren.web.handler;

import com.daoren.common.base.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author peng_da
 * @version :
 * @date 2022/5/27 9:51
 * @since :
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时异常消息处理
     *
     * @param ex       : 异常 {@link RuntimeException}
     * @param request  : 请求
     * @param response : 响应
     * @return com.daoren.common.base.entity.Result
     * @author peng_da
     * @since 2022/5/27 10:29
     */
    @ExceptionHandler({RuntimeException.class})
    public Result handlerRuntimeException(RuntimeException ex, HttpServletRequest request,
                                          HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return Result.fail(ex.getMessage());
    }

    /**
     * 参数校验异常消息处理
     *
     * @param ex       : 异常 {@link BindException}
     * @param request  : 请求
     * @param response : 响应
     * @return com.daoren.common.base.entity.Result
     * @author peng_da
     * @since 2022/5/27 10:30
     */
    @ExceptionHandler({BindException.class})
    public Result handlerBindException(BindException ex, HttpServletRequest request,
                                       HttpServletResponse response) {
        BindingResult exBindingResult = ex.getBindingResult();
        MessageFormat format = new MessageFormat("[参数错误：字段：{0}，错误值：{1}，原因：{2}]");
        List<Object> errorMessage = exBindingResult.getFieldErrors().stream().map(item ->
                format.format(new Object[]{
                        item.getField(),
                        item.getRejectedValue() == null ? "空" : item.getRejectedValue(),
                        item.getDefaultMessage()})
        ).collect(Collectors.toList());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return Result.fail(errorMessage.toString());
    }

    /**
     * 平铺参数校验异常消息处理
     *
     * @param ex       : 异常 {@link ConstraintViolationException}
     * @param request  : 请求
     * @param response : 响应
     * @return com.daoren.common.base.entity.Result
     * @author peng_da
     * @since 2022/5/27 11:05
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result handlerConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request,
                                                      HttpServletResponse response) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        MessageFormat format = new MessageFormat("[参数错误：字段：{0}，错误值：{1}，原因：{2}]");
        List<Object> errorMessage = constraintViolations.stream().map(item ->
                format.format(new Object[]{
                        item.getPropertyPath(),
                        item.getInvalidValue(),
                        item.getMessage()})
        ).collect(Collectors.toList());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return Result.fail(errorMessage.toString());
    }
}
