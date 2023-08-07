package com.daoren.spring.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

/**
 * custom interceptor
 * extend HandlerInterceptorAdapter 代替 implements HandlerInterceptor
 *
 * @author peng_da
 * @date 2022/11/29 14:20
 */
public class MeasurementInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final int startTime = Instant.now().getNano();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        final int startTime = (int) request.getAttribute("startTime");
        final int endTime = Instant.now().getNano();
        modelAndView.addObject("handlingTime", endTime - startTime);
    }
}
