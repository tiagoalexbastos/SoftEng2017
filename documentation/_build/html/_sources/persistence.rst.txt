Persistence : JPA / PostgreSQL
===============================


Table
------

-Room (int ID, String name)
-Sensor(int ID, String name)
-Data (int ID, long time, Sensor name, Room room, int value)

Queries
-------
- getTimeIntervalBySensor
- getRoomByName
- getSensorByName
- getAllRooms