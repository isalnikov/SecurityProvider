/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config;

import com.isalnikov.config.auth.UserAuthenticationManager;
import com.isalnikov.config.auth.UserAuthenticationProvider;
import com.isalnikov.config.auth.UserAuthorizationFilter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Igor Salnikov
 */
@Configuration
@ComponentScan({
    "com.isalnikov.config",
    "com.isalnikov.config.auth",
    "com.isalnikov.config.hendler",
    "com.isalnikov.config.security",
    "com.isalnikov.config.web",
    "com.isalnikov.controller",
    "com.isalnikov.utils"
})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthenticationManager authenticationManager;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return authenticationManager;
    }

    @Bean
    protected UserAuthorizationFilter authorizationFilter() throws Exception {
        UserAuthorizationFilter authorizationFilter = new UserAuthorizationFilter();
        authorizationFilter.setAuthenticationManager(authenticationManager());
        return authorizationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(authorizationFilter(), UserAuthorizationFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/csrf").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin() // default login jsp 
                //.failureUrl("/login")
                //.failureHandler((new SimpleUrlAuthenticationFailureHandler())
                        
                .permitAll()
                .and()
                .logout() //default logout jsp 
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // .deleteCookies("JSESSIONID,SPRING_SECURITY_REMEMBER_ME_COOKIE")
                .permitAll();

        http
                .sessionManagement().maximumSessions(1).and().invalidSessionUrl("/login");

//        http
//                .headers()
//                .frameOptions().sameOrigin()
//                .httpStrictTransportSecurity().disable();
//http.exceptionHandling().authenticationEntryPoint(null);
        http
                .headers()
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "default-src 'self'"))
                .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP", "default-src 'self'"));

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/media/**/*.{js,html,css}")
                .antMatchers("/favicon.ico", "/about","/message/**")
                .antMatchers("/", "/invalidSession");
    }
}
