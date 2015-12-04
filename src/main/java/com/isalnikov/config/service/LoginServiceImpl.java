/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    protected static final ArrayList<String> users = new ArrayList<>();

    static {
        users.add("user");
    }

    @Override
    public int login(String username, String password, String sslId) {

        if (users.contains(username)) {
            return 0;
        }

        return 1;

    }

}
