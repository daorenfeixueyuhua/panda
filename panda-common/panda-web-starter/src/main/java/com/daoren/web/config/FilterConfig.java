package com.daoren.web.config;

import com.daoren.web.filter.PandaRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

/**
 * @author peng_da
 * @version :
 * @date 2022/5/27 16:31
 * @since :
 */
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> registrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        final PandaRequestFilter filter = new PandaRequestFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }
}
