/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.security;

import com.isalnikov.config.auth.UserAuthenticationProvider;
import com.isalnikov.config.auth.UserAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
@Configuration
public class UserWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("root").password(passwordEncoder().encode("root")).roles("USER", "ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .passwordEncoder(passwordEncoder());

        //auth.authenticationProvider(userAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/","/login", "/about").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin() // default login jsp 
                .permitAll()
                .and()
                .logout() //default logout jsp 
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/media/**/*.{js,html,css}")
                .antMatchers("/favicon.ico", "/about");
    }

    @Bean
    public AuthenticationProvider userAuthenticationProvider() {
        UserAuthenticationProvider terminalAuthenticationProvider = new UserAuthenticationProvider();
        return terminalAuthenticationProvider;
    }

}
