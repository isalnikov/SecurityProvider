/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.utils;

import java.util.Locale;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
@Service
public class MessageHelper {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(Locale Locale, Object... params) {

        Assert.state(! ( Objects.isNull(params) || params.length == 0 || params[0] == null || ((String) params[0]).isEmpty()), "MessageHelper getMessage -> params is null");

        Object[] args = new Object[]{};

        if (params.length > 1) {
            args = new Object[params.length - 1];
            System.arraycopy(params, 1, args, 0, params.length - 1);
        }

        String result = messageSource.getMessage((String) params[0], args, Locale);
        return result;
    }

    public String getMessage(Object... params) {
        return getMessage(Locale.getDefault(), params);
    }

}
