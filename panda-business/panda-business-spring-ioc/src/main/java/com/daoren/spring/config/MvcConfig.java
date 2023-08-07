package com.daoren.spring.config;

import com.daoren.spring.converter.SportTypeConverter;
import com.daoren.spring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author peng_da
 * @date 2022/11/18 10:25
 */
@ComponentScan(basePackages = {"com.daoren.**"})
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    private ReservationService reservationService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }


    /**
     * 视图解析器
     *
     * @return org.springframework.web.servlet.view.InternalResourceViewResolver
     * @author peng_da
     * @since 2022/11/26 16:04
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
//    /**
//     * 模板解析器
//     * @author peng_da
//     * @since 2022/11/30 15:41
//
//     * @return org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
//     */
//    @Bean
//    public SpringResourceTemplateResolver springResourceTemplateResolver(){
//        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setPrefix("/static");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode("HTML");
//        resolver.setCacheable(false);
//        resolver.setCharacterEncoding("UTF-8");
//        return resolver;
//    }
//
//    /**
//     * 模板引擎
//     * @author peng_da
//     * @since 2022/11/30 15:43
//
//     * @return org.thymeleaf.TemplateEngine
//     */
//    @Bean
//    public SpringTemplateEngine templateEngine(){
//        final SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(springResourceTemplateResolver());
//        return engine;
//    }
//
//
//    /**
//     * 视图解析器
//     * @author peng_da
//     * @since 2022/11/30 15:48
//
//     * @return org.thymeleaf.spring5.view.ThymeleafViewResolver
//     */
//    @Bean
//    public ThymeleafViewResolver viewResolver(){
//        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding("UTF-8");
//        return viewResolver;
//    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SportTypeConverter(reservationService));
    }
}
