/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.auth;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Igor Salnikov 
 */
public class UserAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private final String sslId;
    private final String login;
    private final String password;

    public UserAuthorizationToken(String username, String password, String sslId) {
        super(username, password, null);
        this.login = username;
        this.password = password;
        this.sslId = sslId;

    }

    public UserAuthorizationToken(String username, String password, String sslId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.login = username;
        this.password = password;
        this.sslId = sslId;

    }

    public String getSslId() {
        return sslId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
