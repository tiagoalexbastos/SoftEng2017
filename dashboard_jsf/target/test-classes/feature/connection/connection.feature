Feature: Connection Broker
    To allow the dashboard to connect to the broker.

Scenario: Broker connection
    Given that we want to connect to the broker. 
    When we try to connect to the localhost IP. 
    Then we should get a successfull connection.
