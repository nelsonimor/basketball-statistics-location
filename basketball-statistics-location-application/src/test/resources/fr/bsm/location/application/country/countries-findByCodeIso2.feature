Feature: location : countries find codeIso2

  Scenario Outline: Get country by codeIso2
    Given A collection of countries with different codeIso2
      | id | name   | codeiso2     | codeiso3   |
      | 1  | France | FR           | FRA     	|
      | 2  | Spain  | ES           | ESP        |


    When I get a country with the code <codeiso2>
    Then I return a country for <id> with <name> and <codeiso2> and <codeiso3>
    Examples:
      | id     | name       | codeiso2        | codeiso3     |
      | "1"    | "France"   | "FR"            | "FRA"        |
      | "2"    | "Spain"    | "ES"            | "ESP"        |