package com.mycompany.net;

import com.mycompany.net.Room;
import com.mycompany.net.Sensor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-30T13:47:48")
@StaticMetamodel(Data.class)
public class Data_ { 

    public static volatile SingularAttribute<Data, Sensor> sensor;
    public static volatile SingularAttribute<Data, Long> id;
    public static volatile SingularAttribute<Data, Long> time;
    public static volatile SingularAttribute<Data, Integer> value;
    public static volatile SingularAttribute<Data, Room> room;

}