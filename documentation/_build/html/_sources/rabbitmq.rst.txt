Enterprise Bus: Rabbit MQ
==========================

Topics
-------

1. gateway.data - connection between Gateway and Database; gateway and Processing units; gateway and Alarms
2. database.put - connection between Processing Units and Database; Alarms and Database;
3. dashboard.request - connection between Dashboard and RabbitMQ (Dashboard Requests)
4. dashboard.response - connection between RabbitMQ Broker and Dashboard (RabbitMQ Responses)
5. log4j.logs - connection between RabbitMQ Broker and ELK stack