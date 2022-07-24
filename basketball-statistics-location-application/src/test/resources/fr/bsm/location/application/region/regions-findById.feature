Feature: location : regions find id

  Scenario Outline: Get region by id
    Given A collection of regions with different id
      | id | name     |
      | 1  | Balkan   |
      | 2  | Caucasus |


    When I get region with <id>
    Then I return a result for <id> with <name>
    Examples:
      | id     | name         |
      | "1"    | "Balkan"     |
      | "2"    | "Caucasus"   |