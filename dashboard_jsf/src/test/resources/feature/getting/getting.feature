Feature: Reading Get
    To allow the dashboard to get data from the values given by the broker.

Scenario: Get reading by timestamp
    Given a reading with the timestamp 1496080135, with data 'somedata', with name TemperaturaDosNarcisos, at farm. 
    Then we should get the timestamp 1496080135.
