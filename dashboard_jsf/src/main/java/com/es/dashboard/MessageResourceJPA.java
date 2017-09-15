/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.es.dashboard;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

/**
 *
 * @author Nuno
 */
@PushEndpoint("/jpa")
public class MessageResourceJPA implements Serializable {
    @OnOpen
    public void onOpen() {
        System.out.println("Socket is opening!");
    }
    @OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(String message) {
        return message;
    }
}
