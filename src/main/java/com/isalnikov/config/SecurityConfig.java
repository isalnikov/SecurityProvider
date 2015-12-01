/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isalnikov.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */

@Configuration
@ComponentScan({
    "com.isalnikov.config",
    "com.isalnikov.config.security",
    "com.isalnikov.config.web"
})
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true , jsr250Enabled = true, prePostEnabled = true ,proxyTargetClass = true)
public class SecurityConfig {
    
}
