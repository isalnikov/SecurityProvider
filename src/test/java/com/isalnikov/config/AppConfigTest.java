/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */

@Configuration
public class AppConfigTest {
    
    @Bean 
    public AuthenticationManager  authenticationManager(){
        return new SampleAuthenticationManager();
    }
    
    
 

  

}
