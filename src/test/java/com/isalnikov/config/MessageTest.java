/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isalnikov.config;

import com.isalnikov.utils.MessageHelper;
import java.util.Arrays;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class)
public class MessageTest {
    
    @Autowired
    private MessageHelper messageHelper;
    
      @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    
    @Test
    public void testTextConfigurer() {
       System.out.println(messageHelper.getMessage("loginController.logout"));
    }
    
    @Test
    public void testRuTextConfigurer() {
     System.out.println(messageHelper.getMessage(new Locale("ru")  , "loginController.logout"));
       System.out.println(messageHelper.getMessage(new Locale("ru_RU")  , "loginController.logout"));
    }
    
    @Test
    public void testEnRuTextConfigurer() {
       System.out.println(messageHelper.getMessage("loginController.logout"));
       System.out.println(messageHelper.getMessage("loginController"));
    }
    @Test(expected  =  IllegalStateException.class)
    public void testNull() {
       System.out.println(messageHelper.getMessage(Locale.CANADA,null));
       System.out.println(messageHelper.getMessage(""));
       System.out.println(messageHelper.getMessage(new Object[]{}));
       System.out.println(messageHelper.getMessage(new Object[]{""}));
    }
    

    
    @Test
    public void testHelloConfigurer() {
       System.out.println(messageHelper.getMessage("hello"));
       System.out.println(messageHelper.getMessage(Locale.CANADA ,"hello"));
       System.out.println(messageHelper.getMessage(Locale.ENGLISH ,"hello"));
       System.out.println(messageHelper.getMessage(new Locale("ru") ,"hello"));
       System.out.println(messageHelper.getMessage(new Locale("ru_RU") ,"hello"));
    }
    @Test
    public void testHelloParamsConfigurer() {
       System.out.println(messageHelper.getMessage("hello.name", "igor"));
       System.out.println(messageHelper.getMessage(Locale.CANADA ,"hello.name","igor"));
       System.out.println(messageHelper.getMessage(Locale.ENGLISH ,"hello.name","igor"));
       System.out.println(messageHelper.getMessage(Locale.ENGLISH ,"hello.name","игорь"));
       System.out.println(messageHelper.getMessage(new Locale("ru") ,"hello.name", "игорь"));
       System.out.println(messageHelper.getMessage(new Locale("ru_RU") ,"hello.name","игорь"));
    }
    @Test
    public void testEnTextConfigurer() {
       System.out.println(messageHelper.getMessage(Locale.ENGLISH , "loginController.logout"));
    }
    
    @Test
    public void testCopy() {

        String[] params = new String[]{"test"};

        Object[] args = new Object[]{};

        if (params.length > 1) {

            args = new Object[params.length - 1];

            System.arraycopy(params, 1, args, 1, params.length - 1);
        }
            
        System.out.println(Arrays.toString(params)); 
        System.out.println(Arrays.toString(args)); 
         
        params = new String[]{"hello","Igor"};

         args = new Object[]{};

        if (params.length > 1) {

            args = new Object[params.length - 1];

            System.arraycopy(params, 1, args, 0, params.length - 1);
        }
            
        System.out.println(Arrays.toString(params)); 
        System.out.println(Arrays.toString(args)); 
         
         
         
        
        
    }

}
