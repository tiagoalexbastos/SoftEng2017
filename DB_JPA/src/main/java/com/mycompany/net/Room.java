/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.net;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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
@Table(name = "Room")
@NamedQueries({
        @NamedQuery(name = "Room.getAll", query = "SELECT e FROM Room e"),
        @NamedQuery(name = "Room.deleteAll", query = "DELETE FROM Room"),
        @NamedQuery(name="Room.getRoomByName", query="SELECT c FROM Room c WHERE c.name = :custName")
        
})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
   
    @Column(name = "name", unique= true)
    private String name;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }
    
    public int getId() {
       return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public String getName( ){
       return name;
    }

    public void setName( String deptName ){
       this.name = deptName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final Room other = (Room) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Room{" + "name=" + name + '}';
    }
    

    

    
       
}
