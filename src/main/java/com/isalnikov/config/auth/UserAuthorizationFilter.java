/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Igor Salnikov
 */
public class UserAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    public UserAuthorizationFilter() {
        setFilterProcessesUrl("/login");
        setUsernameParameter("username");
        setPasswordParameter("password");

    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager); //To change body of generated methods, choose Tools | Templates.
    }

    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    @Autowired
    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if ("POST".equals(request.getMethod())) {
            super.doFilter(req, resp, chain);
        } else {
            chain.doFilter(req, resp);

        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String sslId = request.getHeader("SSL_CLIENT_S_DN_CN");

        if (sslId == null) {
            sslId = "1000000415";
        }

//        if (request.getHeader("Authorization") == null) {
//            return null; // no header found, continue on to other security filters
//        }
        String userName = obtainUsername(request);
        String password = obtainPassword(request);

        if (sslId != null) {
            return new UserAuthorizationToken(userName, password, sslId);
        }
        return null;
    }

}
