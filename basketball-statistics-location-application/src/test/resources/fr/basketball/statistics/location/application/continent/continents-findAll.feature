Feature: location : continents find all

  Scenario: Get all continents
    Given A list of sales continents
      | id | name    | code |
      | 1  | Africa  | AF   |
      | 2  | Europe  | EU   |
    When We get all continents
    Then We return 2 continents with id "1","2", name "Africa","Europe" and code "AF", "EU"