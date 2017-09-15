Feature: Insert reading
    To allow the dashboard to insert and store the values provided by the broker
 
Scenario: Insert reading
    Given a reading with the timestamp 1496080135, with data 'somedata', with name TemperaturaDosNarcisos, at farm. 
    Then 1 reading should have been found