/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isalnikov.config;

import com.isalnikov.config.auth.UserAuthenticationProvider;
import com.isalnikov.config.auth.UserAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Igor Salnikov 
 */

@Configuration
@ComponentScan({
    "com.isalnikov.config",
    "com.isalnikov.config.auth",
    "com.isalnikov.config.security",
    "com.isalnikov.config.web"
})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

     @Autowired
     private UserAuthenticationProvider authenticationProvider;
    
  
  
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 

    
    
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
     
        auth.authenticationProvider(authenticationProvider);
        
        auth
                .inMemoryAuthentication()
                .withUser("root").password(passwordEncoder().encode("root")).roles("USER", "ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(new UserAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
               // .authenticationProvider(userAuthenticationProvider);
        
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/about").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin() // default login jsp 
                .permitAll()
                .and()
                .logout() //default logout jsp 
                .deleteCookies("JSESSIONID,SPRING_SECURITY_REMEMBER_ME_COOKIE")
                .permitAll();

        http
                .sessionManagement().maximumSessions(1);
        
//http.exceptionHandling().authenticationEntryPoint(null);
    
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/media/**/*.{js,html,css}")
                .antMatchers("/favicon.ico", "/about");
    }
}
