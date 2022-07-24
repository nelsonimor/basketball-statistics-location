Feature: location : countries find name

  Scenario Outline: Get country by name
    Given A collection of countries with different name
      | id | name   | codeiso2     |
      | 1  | France | FR           |
      | 2  | Spain  | ES           |


    When I get a country with <name>
    Then I return country for <id> with <name> and <codeiso2>
    Examples:
      | id     | name       | codeiso2         |
      | "1"    | "France"   | "FR"            |
      | "2"    | "Spain"    | "ES"            |