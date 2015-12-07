/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.auth;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Igor Salnikov
 */
public class UserAuthorizationFilter extends UsernamePasswordAuthenticationFilter {


   
    public UserAuthorizationFilter() {
        setFilterProcessesUrl("/login");
    }
    
    

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (RequestMethod.POST.name().equals(request.getMethod())) {
            super.doFilter(req, resp, chain);
        } else {
            chain.doFilter(req, resp);

        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String terminalId = request.getHeader("Authorization");
        
        terminalId = "1";

//        if (request.getHeader("Authorization") == null) {
//            return null; // no header found, continue on to other security filters
//        }
        String userName = obtainUsername(request);
        String password = obtainPassword(request);

        if (terminalId != null) {
            UserAuthorizationToken token = new UserAuthorizationToken(userName, password, terminalId, Arrays.asList(UserAuthority.ROLE_USER));

       
            return super.getAuthenticationManager().authenticate(token);
        }

         throw new BadCredentialsException("Invalid username or password");
    }

}
