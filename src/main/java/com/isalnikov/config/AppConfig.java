/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config;

import com.isalnikov.config.hendler.BeanHendlerFactoryPostProcessor;
import com.isalnikov.utils.MessageHelper;
import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Igor Salnikov
 */
@Configuration
public class AppConfig {

    @Bean
    public static BeanHendlerFactoryPostProcessor beanHendlerFactoryPostProcessor() {
        return new BeanHendlerFactoryPostProcessor();
    }

 
}
