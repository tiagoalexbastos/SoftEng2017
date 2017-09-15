1 - USER MANUAL
===============

Deploy
-------
- run maven tests: ``mvn test`` (optional)
- run jenkins: ``sudo`` (with tests)
- run docker-compose: ``sudo docker-compose up`` (instead of jenkins)


Dashboard (User)
----------------
website: ``http://deti-engsoft-06.ua.pt:12215/dashboard/``



1. Access real time data: press the button "Realtime" on the sidebar

.. figure:: _static/history4.png

	Figure: Realtime button

.. figure:: _static/relatime.png

	Figure: Dashboard with realtime data

2. Access historic data: choose the button "History" on the left side

.. figure:: _static/history3.png

	Figure: History button

.. figure:: _static/history2.png

	Figure: Dashboard with history


3. See alerts: The alers will automaticly show up on the righ corner in red.

.. figure:: _static/alert.png

	Figure: Dashboard alert message

4. See the details of one point in the graphic: Pass the cursor on top of the point in question

.. figure:: _static/detail.png

	Figure: Detail of each point

Kibana (Website for monitorization)
--------------------------------------

website: ``http://deti-engsoft-06.ua.pt:5601/``

.. figure:: _static/kibana.png

	Figure: Kibana


To see the dashboard, import the file export.json.

.. figure:: _static/kibana2.png

	Figure: Kibana Dashboard




