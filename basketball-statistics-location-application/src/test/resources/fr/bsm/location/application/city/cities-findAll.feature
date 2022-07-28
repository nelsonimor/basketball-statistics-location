Feature: location : cities find all

  Scenario: Get all cities
    Given A list of cities
      | id | name      	 | county      				 |
      | 1  | Brussels    | Bruxelles-Capitale      	 |
    When We get all cities
    Then We return 1 city with id "1" and name "Brussels" and county "Bruxelles-Capitale"