/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            UserAuthorizationToken auth = (UserAuthorizationToken) authentication;

            String login = auth.getLogin();
            String password = auth.getPassword();
            String sslId = auth.getSslId();

                //logger.info(gson.toJson(loginResponse));
            Object principal = auth.getPrincipal();
            UserAuthorizationToken authorizationToken = new UserAuthorizationToken(login, password, sslId, Arrays.asList(UserAuthority.ROLE_USER));
            authorizationToken.setAuthenticated(true);
            return authorizationToken;

        } catch (Exception ex) {
            Logger.getLogger(UserAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UserAuthorizationToken.class.isAssignableFrom(authentication));
    }

}
