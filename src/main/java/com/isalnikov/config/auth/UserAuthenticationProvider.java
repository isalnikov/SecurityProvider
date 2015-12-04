/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.auth;

import com.isalnikov.config.service.LoginService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Igor Salnikov
 */
@Service
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean supports(Class<?> authentication) {
        return (UserAuthorizationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserAuthorizationToken auth = (UserAuthorizationToken) authentication;

        String login = auth.getLogin();
        String password = auth.getPassword();
        String terminalId = auth.getTerminalId();

        if (loginService.login(login, password, terminalId) == 0) {

            Object principal = auth.getPrincipal();
            UserAuthorizationToken authorizationToken = new UserAuthorizationToken(login, password, terminalId, Arrays.asList(UserAuthority.ROLE_USER));

            return authorizationToken;
        }

        throw new BadCredentialsException("Invalid username or password");

    }

}
