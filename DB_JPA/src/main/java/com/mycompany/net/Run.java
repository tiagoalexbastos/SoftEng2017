/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 *
 * @author daniela
 */
public class Run {
final static Logger logger = Logger.getLogger(Run.class);


    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.util.concurrent.TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        // TODO code application logic here
        
        JPA jpa = new JPA();
        jpa.initDB();
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("systembus"); // RabbitMQ IP
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("sensors", "topic"); // sensors is the name of the exchange
        AMQP.Queue.DeclareOk result = channel.queueDeclare("", false, true, false, null);
        String queuename = result.getQueue();
        //bind to sensor info
        channel.queueBind(queuename, "sensors", "gateway.data"); // Binding key is #, this will consume all messages
        
        //bind to the dashboard
        channel.queueBind(queuename, "sensors", "dashboard.request");

        //bind to Processing units output
        channel.queueBind(queuename, "sensors", "database.put");


        
        logger.info(" [*] Waiting for messages. To exit press CTRL+C");
        
        
        
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    
                    try {
                        String message = new String(body, "UTF-8");
                        Gson gson = new Gson();
                        Type type = new TypeToken<Map<String, String>>(){}.getType();
                        Map<String, String> myMap = gson.fromJson(message, type);
                        logger.info(" [x] " + envelope.getRoutingKey() + " - Received '" + message + "'");
                        
                        String routing_key = envelope.getRoutingKey();
                        if("gateway.data".equals(routing_key) ){
                            jpa.saveRawToDB(myMap);
                        }
                        else if( "database.put".equals(routing_key)){
                            jpa.saveAlertToDB(myMap);
                        }
                        else if("dashboard.request".equals(routing_key) ){
                            Thread.sleep(1000);
                            jpa.processRequest(myMap , channel);
                        }
                        else
                            logger.error("NOT A VALID MESSAGE!"); 
                            

                    } catch (Exception e) {
                       logger.error(e.toString());
                    }
                
                
            }
        };
        
        
        channel.basicConsume(queuename, true, consumer);
            
       
        
        //channel.close();
        //connection.close();


        //entityManager.close();
        //entityManagerFactory.close();

    }
    
    
    
}
