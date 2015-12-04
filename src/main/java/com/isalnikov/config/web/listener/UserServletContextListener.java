/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Igor Salnikov
 */
@WebListener
public class UserServletContextListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("==== sessionCreated ====" + se.getSession().getId());
        System.out.println(getPrincipal());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("==== sessionDestroyed ====" + se.getSession().getId());
        System.out.println(getPrincipal());
    }

    private Object getPrincipal() {
        Object principal = null;
        String username = null;
        try {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        } catch (Exception e) {
        }

        System.out.println(principal);

        return username;

    }
}
