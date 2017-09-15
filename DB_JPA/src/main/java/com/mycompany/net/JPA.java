/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.net;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 *
 * @author daniela
 */
public class JPA {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    int init = 0;
    final static Logger logger = Logger.getLogger(JPA.class);
    public JPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.mycompany_DB_JPA_jar_1.0-SNAPSHOTPU");
        entityManager = entityManagerFactory.createEntityManager();
        
    }
    
    public void initDB(){
        entityManager.getTransaction().begin();
        
        List<Room> rooms = getAllRooms(entityManager);
        /*if (rooms.size() == 9){
            rooms.forEach((r) -> {
                System.out.println(r.getName());
            });
        }*/
        
        if(rooms.size() != 9){
            if(rooms.size() > 9){
                //delete all
                logger.info("DELETE ALL");
                Query q1 = entityManager.createNativeQuery("DELETE FROM ROOMS");
                q1.executeUpdate();
                //entityManager.createNamedQuery("Room.deleteAll");
                entityManager.getTransaction().commit();
                entityManager.getTransaction().begin();
            }

            
            logger.info("Begin adding elements!!");
            //put rooms
            Room r1 = new Room("kitchen"); entityManager.persist(r1);
            Room r2 = new Room("livingroom"); entityManager.persist(r2);
            Room r3 = new Room("master"); entityManager.persist(r3);
            Room r4 = new Room("bedroom"); entityManager.persist(r4);
            Room r5 = new Room("masterbath"); entityManager.persist(r5);
            Room r6 = new Room("guestbath"); entityManager.persist(r6);
            Room r7 = new Room("diningroom"); entityManager.persist(r7);
            Room r8 = new Room("office"); entityManager.persist(r8);
            Room r9 = new Room("All"); entityManager.persist(r9);

            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        }
        
        List<Room> tmp = getAllRooms(entityManager);
        logger.debug("SHOW TABLE: Room");
        tmp.forEach((r) -> {
            logger.debug("Adding:"+ r.getName() + "to table Rooms");
        });
        
        entityManager.getTransaction().commit();
    }
    
   
    //--------------parse RabbitMQ-----------------
    
    public void saveRawToDB( Map<String, String> myMap ){
        entityManager.getTransaction().begin();
        Room r1 = getRoomByName(entityManager, myMap.get("room"));
        Sensor s1 = getSensorByName(myMap.get("name"));
        if (s1 == null) s1 = new Sensor(myMap.get("name"));
        entityManager.persist(s1);
        Data new_data = new Data(Long.parseLong(myMap.get("timestamp")), s1, r1, Integer.parseInt(myMap.get("data")));
        entityManager.persist(new_data);
        entityManager.getTransaction().commit();
    }

    public void saveAlertToDB (Map<String, String> myMap){
        entityManager.getTransaction().begin();
        //create Alert database -- TODO
        
        Room r1 = getRoomByName(entityManager, myMap.get("room"));
        Sensor s1 = getSensorByName(myMap.get("name"));
        Data new_data = new Data(Long.parseLong(myMap.get("timestamp")), s1, r1, Integer.parseInt(myMap.get("data")));
        entityManager.persist(new_data);
        entityManager.getTransaction().commit();
    }

    public void processRequest(Map<String, String> myMap, Channel channel){
        System.out.println("before begin");
        System.out.println("before getTime");
        List<Data> tmp = getTimeIntervalBySensor(Long.parseLong(myMap.get("start")) , Long.parseLong(myMap.get("end")) , 
                myMap.get("name") , myMap.get("room"));

        System.out.println("before commit");
        System.out.println("free");
        Data data;
        //PropertyConfigurator.configure(log4JPropertyFile);
        //send to queue
        for (Iterator<Data> iterator = tmp.iterator(); iterator.hasNext();) {
            data = (Data) iterator.next();
            String message = data.toString(); 
            try {
                channel.basicPublish("sensors", "dashboard.response", null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            } catch (IOException ex) {
               // logger.error(ex);
            }
           
        }


    }
    
    //-------------- aux functions-------------------------

    /**
     *
     * @param em
     * @return
     */
    public List<Room> getAllRooms(EntityManager em) {
        return em.createNamedQuery("Room.getAll").getResultList();
    }
    
    public Room getRoomByName(EntityManager em, String name) {
        List<Room> q = em.createNamedQuery("Room.getRoomByName") 
                .setParameter("custName", name)
                .getResultList();
        Room r = null;
        for (Iterator<Room> iterator = q.iterator(); iterator.hasNext();) {
            r = (Room) iterator.next();
            break;
        }
        
        return r ;
    }
    
    public Sensor getSensorByName(String name){
        List<Sensor> q = entityManager.createNamedQuery("Sensor.getSensorByName") 
                .setParameter("custName", name)
                .getResultList();
        Sensor r = null;
        for (Iterator<Sensor> iterator = q.iterator(); iterator.hasNext();) {
            r = (Sensor) iterator.next();
            break;
        }
        return r;
    }

    public List<Data> getTimeIntervalBySensor(Long start , Long end , String name, String room){
        entityManager.getTransaction().begin();
        List<Data> p = entityManager.createNamedQuery("Data.getTimeIntervalBySensor")
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("name", name)
                .getResultList();
        entityManager.getTransaction().commit();
        return p;

    }
    
    

}
