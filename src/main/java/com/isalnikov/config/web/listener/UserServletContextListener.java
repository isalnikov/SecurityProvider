/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isalnikov.config.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Igor Salnikov 
 */

@WebListener
public class UserServletContextListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
          System.out.println("==== sessionCreated ====" + se.getSession().getId());
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
          System.out.println("==== sessionDestroyed ====" + se.getSession().getId());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
