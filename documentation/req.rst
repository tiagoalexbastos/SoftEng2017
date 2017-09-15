Software Requirements & Specification
=====================================

Business requirements
^^^^^^^^^^^^^^^^^^^^^^

 - BR-1: Prevents temperature from going up by alert the user.


User requirements
^^^^^^^^^^^^^^^^^^^^^^
 - UR-1: Monitorize in real time all wanted aspects at home.
 - UR-2: Acess historic information.
 - UR-3: Alert to something that is not supose to be happening e.g. temperature higher than 30ªC.

Functional requirements
^^^^^^^^^^^^^^^^^^^^^^^^
 - FR-1: When a trigger situation event happens an alert must be send.
 - FR-2: The user must be able to change the trigger events (temperature, doors that could be open).
 - FR-3: The information must be processed and displayed in a two seconds time window.

External interface requirements
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 - ER-1: A internet connection is needed to plug sensors.
 - ER-2: The users need a browser with internet connection to dashboard access.

Data requirements
^^^^^^^^^^^^^^^^^^^^^^
 - DR-1: A Json format is needed like the following one {‘type’: ‘DOOR’ ,’room’: ‘kitchen’ ,’name’:’refrigerator’ , ‘timestamp’: 1337518334 , ‘value’ : 0 }.
 - DR-2: In Json packet the fields: type, room, name, timestamp and value are mandatory.

Solution ideas
^^^^^^^^^^^^^^^^^^^^^^

 - SI-1: The user should be able to search information in historic data.
 - SI-2: In case of trigger event the dashboard must display a warning alert.

User interface
^^^^^^^^^^^^^^^^^^^^^^

 - UI-1: The time scale interaction must be intuitive.
 - UI-2: Users should easily switch between real time and historic data.



