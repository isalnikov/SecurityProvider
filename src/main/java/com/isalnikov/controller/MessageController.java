/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.controller;

import com.isalnikov.utils.MessageHelper;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Igor Salnikov 
 */
@Controller
public class MessageController {

    @Autowired
    private MessageHelper messageHelper;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/message/{code}"},
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity index(@PathVariable("code") String code, Locale locale) {

        String result = messageSource.getMessage(code, new Object[]{}, locale);
        System.out.println(String.format("%s  :  %s", locale.getLanguage(), result));

        String res = String.format("%s  :  %s", locale.getLanguage(), messageHelper.getMessage(code));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json;charset=UTF-8");
        ResponseEntity responseEntity = new ResponseEntity<>(res, headers, HttpStatus.OK);
        return responseEntity;
    }

}
