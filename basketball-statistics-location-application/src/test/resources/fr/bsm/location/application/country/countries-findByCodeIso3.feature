Feature: location : countries find codeIso3

  Scenario Outline: Get country by codeIso3
    Given A collection of countries with different codeIso3
      | id | name   | number     	| codeiso3   |
      | 1  | France | 123           | FRA     	|
      | 2  | Spain  | 456           | ESP        |


    When I get country with the code <codeiso3>
    Then I return country for <id> with <name> and <number> and <codeiso3>
    Examples:
      | id     | name       | number           | codeiso3     |
      | "1"    | "France"   | "123"            | "FRA"        |
      | "2"    | "Spain"    | "456"            | "ESP"        |