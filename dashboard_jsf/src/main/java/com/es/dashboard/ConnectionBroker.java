/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.es.dashboard;

/**
 *
 * @author pmatos9
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.servlet.http.HttpServlet;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ThreadLocalRandom;
import org.primefaces.context.RequestContext;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.RequestScoped;
import org.json.simple.JSONObject;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import org.apache.log4j.Logger;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;


@ManagedBean(name="connect")
@ApplicationScoped
public class ConnectionBroker extends HttpServlet implements Serializable{
    
    private int status = 0;
    private LinkedList<String> bigBaite;
    private List<Reading> readings;
    
    final static Logger logger = Logger.getLogger(ConnectionBroker.class);
    
    @PostConstruct
    public void init() {
       
       bigBaite = new LinkedList<>();
       readings = new ArrayList<Reading>();
       ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("systembus"); // RabbitMQ IP
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException ex) {
            logger.error( ex);
        } catch (TimeoutException ex) {
            logger.error( ex);
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException ex) {
           logger.error( ex);
        }

        try {
            channel.exchangeDeclare("sensors", "topic"); // sensors is the name of the exchange
        } catch (IOException ex) {
           logger.error( ex);
        }
        AMQP.Queue.DeclareOk result = null;
        try {
            result = channel.queueDeclare("", false, true, false, null);
        } catch (IOException ex) {
           logger.error( ex);
        }
        String queuename = result.getQueue();
        try {
            channel.queueBind(queuename, "sensors", "realtime.sensordata"); // Binding key is #, this will consume all messages
            channel.queueBind(queuename, "sensors", "realtime.alarms"); // Binding key is #, this will consume all messages
        } catch (IOException ex) {
           logger.error( ex);
        }

       logger.info(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                EventBus eventBus = EventBusFactory.getDefault().eventBus();
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, String>>() {
                }.getType();
                Map<String, String> myMap = gson.fromJson(message, type);
                if (envelope.getRoutingKey().equals("realtime.alarms")) {
                    eventBus.publish("/channel", message);
                    return;
                }
                logger.info(envelope.getRoutingKey());
                
                
                readings.add(new Reading(new Date(Long.parseLong(myMap.get("timestamp"))), myMap.get("data"), myMap.get("name"), myMap.get("room")));
                eventBus.publish("/channel", message);
                
               
            }
        };
        try {
            //RequestContext.getCurrentInstance().execute("function rpt() {$(\".qualquer\").first().trigger(\"click\")}"
            //        + "var timeoutdummy = window.setInterval(rpt,2000);");
            channel.basicConsume(queuename, true, consumer);
        } catch (IOException ex) {
            ex.printStackTrace();
           logger.error( ex);
        }
        
       
    }
    
     public List<Reading> getReadings() {
        return readings;
    }
    
    public void doesNothing(){
    
    }
    
    public static Map<String, ?> makeMap(String s){
        Map<String, String> map = new HashMap<>();
        map.put("data", s);
        return map;
    }    
    
    public int getStatus(){
        System.out.println("b8");
        return status;
    }
    
    public void connecting(){
        
        //System.out.println("Broker-connecting");
        //System.out.println(ThreadLocalRandom.current().nextInt(0,11));
        /*try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost"); // RabbitMQ IP
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("sensors", "topic", false);
            String message = "connecting";
            channel.basicPublish("sensors", "dashboard.request", null, message.getBytes());
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(ConnectionBroker.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    
    
    
    
  
    
    
}
