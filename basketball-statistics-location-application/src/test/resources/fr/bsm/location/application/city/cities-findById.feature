Feature: location : cities find id

  Scenario Outline: Get city by id
    Given A collection of cities with different id
      | id | name      |
      | 1  | Paris 	   |
      | 2  | Marseille |


    When I get city with <id>
    Then I return a result for <id> with <name>
    Examples:
      | id     | name          |
      | "1"    | "Paris"       |
      | "2"    | "Marseille"   |