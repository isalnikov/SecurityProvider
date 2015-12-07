/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.utils;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
@Service
public class MessageHelper {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(Locale Locale, String... params) {

        String result = messageSource.getMessage(params[0], new Object[]{}, Locale);
        return result;
    }

    public String getMessage(String... params) {
        return getMessage(Locale.getDefault(), params);
    }

}
