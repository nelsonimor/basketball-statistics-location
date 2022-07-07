Feature: location : regions find all

  Scenario: Get all regions
    Given A list of regions
      | id | name      |
      | 1  | Balcan    |
      | 2  | Caucasus  |
    When We get all regions
    Then We return 2 regions with id "1","2", name "Balcan","Caucasus"