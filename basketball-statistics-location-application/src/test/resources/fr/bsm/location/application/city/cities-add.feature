Feature: location : cities add one

  Scenario: Add a new city
    Given A list of one city
      | id | name      	 | county      				 |
      | 1  | Brussels    | Bruxelles-Capitale      	 |
    When We add a city with name "Gand" and county "Gand County"
    Then We return "2" cities