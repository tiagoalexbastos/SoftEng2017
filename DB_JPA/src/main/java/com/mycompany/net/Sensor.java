/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.net;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author daniela
 */

@Entity
@Table(name = "Sensor")
@NamedQueries({
        @NamedQuery(name = "Sensor.getAll", query = "SELECT e FROM Sensor e"),
        @NamedQuery(name="Sensor.getSensorByName", query="SELECT c FROM Sensor c WHERE c.name = :custName")
        
})
public class Sensor implements Serializable {
     private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name", unique= true)
    private String name;

    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public int getId() {
       return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sensor other = (Sensor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sensor{" + "name=" + name + '}';
    }
    
    
    
}
