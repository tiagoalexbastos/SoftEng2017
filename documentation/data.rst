Data & Controllers
============================

Data format
-----------

Json format

- DOOR   : {'type': 'DOOR' ,'room': 'kitchen' ,'name':'refrigerator' , 'timestamp': 1337518334 , 'value' : 0 }
- LIGHTS : {'type': 'LIGHTS' ,'room': 'kitchen' ,'name': 'sink' , 'timestamp': 1337518334 , 'value' : simul_val }
- ENV    : {'type': 'ENV', 'timestamp': 1337518334 , 'insideTemp' : 10.0 , 'outsideTemp' : 10.0 ,'insideHumidity': 10.0 , 'outsideHumidity': 10.0, 'windSpeed' : 10.0 , 'rain': 10.0 }
- MOTION : {'type': 'MOTION' ,'room': 'kitchen' ,'name':'corner' , 'timestamp': 1337518334 , 'value' : 0 }
- POWER  : {'type': 'POWER' ,'room':'kitchen','name': 'lights' , 'timestamp': 1337518334 , 'PowerWatts' : 0.0 , 'ApparentPowerVAs': 0.0 }

Sensors
-------
Possible to have multiple sensors.

Our dashboard has 4 simulated sensors: (2 temperature, 2 humidity)

Data
----

Type
^^^^
- DOOR (open/close)
- LIGHTS (lights on/off)
- ENV
- MOTION (detected/non detected)
- POWER

Actuators (all LIGHTS)
^^^^^^^^^^^^^^^^^^^^^^^
['bedroom:lights', 'master:lights' , 'livingroom:dininglights' , 'livingroom:firelights' , 'livingroom:lights1' ,
'guestbath:fan','guestbath:overheadlight' , 'guestbath:sinklight' ,
'masterbath:sinklight' , 'masterbath:overheadlight' , 'masterbath:fan',
'kitchen:lights' , 'kitchen:sink']



Data Set
^^^^^^^^
- door : DoorSensorName , TimestampUTC , OpenOrClosed
- motion: MotionSensorName , TimestampUTC , MotionOrNoMotion
- enviro: TimestampUTC , insideTemp , outsideTemp , insideHumidity , outsideHumidity , windSpeed, windDirectionDegrees , windGust , windGustDirectionDegrees , rainRate , rain , windChill , heatindex
- switch : SwitchName , TimestampUTC , MaxRealPowerWatts , DimProportion , CircuitNumber (Current real power can be closely approximated by MaxRealPowerWatts * DimProportion.)
- circuit : CircuitName , CircuitNumber , TimestampUTC , RealPowerWatts , ApparentPowerVAs

more info in: http://traces.cs.umass.edu/index.php/Smart/Smart



Simulation
-----------

Simulation of the controllers and sensors/actuators.

1. Run history ``python main.py H``

2. Run random simulation values with new timestamp (now) ``python main.py S``

3. Create new values in terminal ``python main.py``



