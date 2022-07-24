Feature: location : continents find id

  Scenario Outline: Get continent by id
    Given A collection of continents with different id
      | id | name   | code    |
      | 1  | Africa | AF      |
      | 2  | Europe | EU      |


    When I get continent with <id>
    Then I return a result for <id> with <name> and <code>
    Examples:
      | id     | name       | code        |
      | "1"    | "Africa"   | "AF"        |
      | "2"    | "Europe"   | "EU"        |