Feature: location : countries find all

  Scenario: Get all countries
    Given A list of countries
      | id | name      |
      | 1  | France    |
      | 2  | Spain	   |
    When We get all countries
    Then We return 2 countries with id "1","2", name "France","Spain"