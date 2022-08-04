Feature: location : cities delete one

  Scenario: Delete a city
    Given A list of two city
      | id | name      	  | county      				 |
      | 10  | Brussels    | Bruxelles-Capitale      	 |
      | 20  | Paris       | Paris     	                 |
    When We remove a city with id "20"
    Then We return only "1" city