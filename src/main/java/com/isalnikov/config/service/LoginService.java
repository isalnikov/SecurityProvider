/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isalnikov.config.service;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
public interface LoginService {
    
     int login(String username, String password, String sslId);
    
}
