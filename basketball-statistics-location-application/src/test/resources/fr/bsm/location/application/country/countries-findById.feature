Feature: location : countries find id

  Scenario Outline: Get country by id
    Given A collection of countries with different id
      | id | name   | codeiso3      |
      | 1  | France | FRA           |
      | 2  | Spain  | ESP           |


    When I get country with <id>
    Then I return a result for <id> with <name> and <codeiso3>
    Examples:
      | id     | name       | codeiso3         |
      | "1"    | "France"   | "FRA"            |
      | "2"    | "Spain"    | "ESP"            |