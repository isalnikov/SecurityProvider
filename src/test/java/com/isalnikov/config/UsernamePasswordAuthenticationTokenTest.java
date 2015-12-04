/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isalnikov.config;

import com.isalnikov.config.auth.UserAuthorizationToken;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Igor Salnikov <igor.salnikov@stoloto.ru>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class)
@WebAppConfiguration
@EnableWebSecurity
public class UsernamePasswordAuthenticationTokenTest {

    @Autowired
    public SampleAuthenticationManager manager;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of beanHendlerFactoryPostProcessor method, of class AppConfig.
     */
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN", "USER"})
    public void testAuthentication() {

        String name = "name";
        String password = "name";

        try {
            Authentication request = new UserAuthorizationToken(name, password, null);
            Authentication result = manager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);

        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }

        System.out.println("Successfully authenticated. Security context contains: " + SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @Ignore
    public void testMVC() throws Exception {

        ResultActions ra = mvc.perform(logout());
        System.out.println(ra);
//        mvc
// .perform(post("/").with(csrf()));
//        mvc.perform(
//                get("/").with(
//                        httpBasic("user", "password")));
    }

}
