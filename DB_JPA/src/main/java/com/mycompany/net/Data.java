/*
 * Data's table
 */
package com.mycompany.net;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.json.simple.JSONObject;
//import org.json.
/**
 *
 * @author daniela
 */
@Entity
@Table(name = "Data")

@NamedQueries({
        @NamedQuery(name = "Data.getTimeIntervalBySensor", 
            query = "SELECT c FROM Data c WHERE c.sensor.name = :name AND c.time >= :start AND c.time <= :end")
        
})

public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "timestampUTC")
    private long time;
    
    @ManyToOne
    @JoinColumn(name = "sensor", nullable=false)
    private Sensor sensor;
    
    @ManyToOne
    @JoinColumn(name = "room", nullable=false)
    private Room room;
    
    @Column(name = "dataValue")
    private int value;

    public Data() {
    }

    public Data(long time, Sensor name, Room room, int value) {
        this.time = time;
        this.sensor = name;
        this.room = room;
        this.value = value;
    }
    
    

    public long getTime() {
        return time;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public Room getRoom() {
        return room;
    }
    

    public int getValue() {
        return value;
    }
    
            
    public Long getId() {
        return id;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        //myMap.get("timestamp"))), myMap.get("data"), myMap.get("name"), myMap.get("room")))
        //{ "name":"John", "age":31, "city":"New York" };
        JSONObject json = new JSONObject();
        json.put("timestamp", time);
        json.put("data", value);
        json.put("name", sensor.getName() );
        json.put("room", room.getName());
        return json.toString();
    }

    
    
    
    
}
