/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isalnikov.config.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author igor
 */
public enum UserAuthority implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
