/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.auth;

import java.util.Arrays;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Igor Salnikov 
 */
@Component
public class UserAuthenticationProvider implements  AuthenticationProvider {

    

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    
            UserAuthorizationToken auth = (UserAuthorizationToken) authentication;

      
            String login = auth.getLogin();
            String password = auth.getPassword();
            String sslId = auth.getSslId();

           //logger.info(gson.toJson(loginResponse));
            Object principal = auth.getPrincipal();
            UserAuthorizationToken authorizationToken = new UserAuthorizationToken(login, password, sslId, Arrays.asList(UserAuthority.ROLE_USER));
            authorizationToken.setAuthenticated(true);
            return authorizationToken;

    
        //throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UserAuthorizationToken.class.isAssignableFrom(authentication));
    }

 

}
