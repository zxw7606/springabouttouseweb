package com;


import com.annotation.UserAnnotationFormatterFactory;
import com.sun.corba.se.spi.resolver.LocalResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.number.money.MonetaryAmountFormatter;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;


/**
 * @description: WebApplicationContext 配置
 * @create: 2019-08-12 12:20
 * @author: zxw
 **/

@EnableWebMvc
//@ImportResource("classpath:spring-mvc.xml")
@ComponentScan
@Configuration
public class DispatcherConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(DispatcherConfig.class);


    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.getDefault());
        return localeResolver;
    }


    /***************************************************************************************************/


    /**
     * @Description: 对输出结果和输入的格式转换
     * formatter 和 convert 没有区别
     * formatter 最后也会被转化成 convert
     * @Date: 2019/8/12
     * @Param:
     * @return:
     * @Author: zxw
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new MonetaryAmountFormatter());
        registry.addFormatterForFieldAnnotation(new DateTimeFormatAnnotationFormatterFactory());
        registry.addFormatterForFieldAnnotation(new UserAnnotationFormatterFactory());
    }

//    @Bean("conversionService")
//    public FormattingConversionServiceFactoryBean conversionServiceFactoryBean() {
//        logger.info("FormattingConversionServiceFactoryBean start");
//        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
//        Set<Object> set = new HashSet<>();
//        set.add(new MonetaryAmountFormatter());
//        set.add(new UserAnnotationFormatterFactory());
//        factoryBean.setFormatters(set);
//        return factoryBean;
//    }

    /***************************************************************************************************/


    /**
     * 配置谈判视图解析器
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
//        configurer.mediaType("xml",MediaType.APPLICATION_XML);
    }


    /**
     * jsp配置
     *
     * @param registry 注册视图,和视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MappingJackson2JsonView jackson2JsonView = new MappingJackson2JsonView();
        registry.enableContentNegotiation(jackson2JsonView);
        registry.jsp("/WEB-INF/", ".jsp");

    }
}
