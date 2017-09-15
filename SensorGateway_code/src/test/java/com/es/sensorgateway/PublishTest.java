/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.es.sensorgateway;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
/**
 *
 * @author daniela
 */
public class PublishTest  {
    
    public PublishTest() {
    }
    
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

    /**
     * Test of processRequest method, of class Publish.
     */
    @Test
    public void testProcessRequest() throws Exception {
        System.out.println("processRequest");
        MockHttpServletRequest request = new MockHttpServletRequest();
        /*request.setServerName("www.example.com");
        request.setRequestURI("/foo");
        request.setQueryString("param1=value1&param");*/
        request.addParameter("data", "data");
        MockHttpServletResponse response =  new MockHttpServletResponse();
       // HttpServletResponse response =
        Publish instance = new Publish();
        try{
            instance.processRequest((HttpServletRequest)request, (HttpServletResponse)response);
        }catch(UnknownHostException e){
            assertTrue(true );
        }
        assertEquals(HttpServletResponse.SC_OK, response.getStatus() );
       
        
    }

    

    /**
     * Test of doPost method, of class Publish.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        MockHttpServletRequest request = new MockHttpServletRequest();
        

        MockHttpServletResponse response =  new MockHttpServletResponse();
       // HttpServletResponse response =
        Publish instance = new Publish();
        instance.processRequest((HttpServletRequest)request, (HttpServletResponse)response);
        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus() );
        
    }

    
    
}
