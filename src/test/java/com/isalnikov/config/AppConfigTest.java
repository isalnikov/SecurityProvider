/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config;

import com.isalnikov.utils.MessageHelper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
@Configuration
public class AppConfigTest {

    @Bean
    public AuthenticationManager authenticationManager() {
        return new SampleAuthenticationManager();
    }

    @Bean
    public MessageHelper helper() {
        return new MessageHelper();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("message");
        messageSource.setCacheSeconds(5);
        //messageSource.setFallbackToSystemLocale(false);// ???
        return messageSource;
    }

}
