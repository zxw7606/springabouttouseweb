package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


/**
 * 国际化配置
 */
@Configuration
public class MessageConfig {
    private final static Logger logger = LoggerFactory.getLogger(MessageConfig.class);


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/valid");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    @PostConstruct
    public void init() {
        logger.debug("国际化配置 成功\n" +
                " message init success");
    }

//    @Bean(name = "localeResolver")
//    @Primary
//    public LocaleResolver localResolver(){
//        return new MyLocaleResolver();
//    }
//
//
//    public static class MyLocaleResolver extends SessionLocaleResolver {
//        @Override
//        public Locale resolveLocale(HttpServletRequest request) {
//            String lc = request.getParameter("locale");
//            if (StringUtils.hasText(lc)) {
//                return new Locale(lc);
//            }
//            return super.resolveLocale(request);
//        }
//    }
}
