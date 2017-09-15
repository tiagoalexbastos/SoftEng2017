Tests
=====

JUnit Tests
-----------

We have JUnit in every class in order to check if every method is behaving the way we expect.


BDD Tests
-----------

**Test 1**

Feature: Connection Broker
  To allow the dashboard to connect to the broker.
  
  Scenario: Broker connection
    Given that we want to connect to the broker.
    When we try to connect to the systembus IP.
    Then we should get a successfull connection.


**Test 2**

Feature: Reading Get
  To allow the dashboard to get data from the values given by the broker.
  
  Scenario: Get reading by timestamp
    Given a reading with the timestamp 1496080135, with data 'somedata', with name TemperaturaDosNarcisos, at farm.
    Then we should get the timestamp 1496080135.


**Test 3**

Feature: Insert reading
  To allow the dashboard to insert and store the values provided by the broker
  
  Scenario: Insert reading
    Given a reading with the timestamp 1496080135, with data 'somedata', with name TemperaturaDosNarcisos, at farm.
    Then 1 reading should have been found




https://docs.google.com/document/d/1vdhJmUuDDxkwn5avWstLzVb9bD8WoFL9q_5HXE1klqA
