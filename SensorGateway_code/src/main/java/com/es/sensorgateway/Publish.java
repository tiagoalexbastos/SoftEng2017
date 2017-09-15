/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.es.sensorgateway;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import java.util.Base64;
import org.apache.log4j.Logger;
/**
 *
 * @author Nuno
 */
public class Publish extends HttpServlet {
    final static Logger logger = Logger.getLogger(Publish.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnknownHostException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            
            logger.info("Connection with rabbit Mq stablish!");
            
            byte[] original;
            String message= null;
            try{
                message = request.getParameter("data");
                original = Base64.getUrlDecoder().decode(message);
            }catch(Exception ex){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(HttpServletResponse.SC_BAD_REQUEST);
                logger.debug("Ignoring message: " + message );
                return;
            }
            
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("systembus"); // RabbitMQ IP
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            //POR aqui as cenas de autenticação
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(HttpServletResponse.SC_OK);
            logger.info("Received request from REST gateway!");
            
            channel.exchangeDeclare("sensors", "topic", false);
            channel.basicPublish("sensors", "realtime.sensordata", null, original);
            logger.info("Message sent to broker: " + message);
            
            channel.close();
            connection.close();
            logger.info("Connection with rabbit Mq closed!");
            
        } catch (TimeoutException ex) {
            logger.error( ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
