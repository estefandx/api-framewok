@allure.label.component:sample
Feature: Search Test

  #@regression
  #Scenario Outline: get by Id
  #  When I query SearchAPI with id "<id>"
   # Then I should have the response matching "<expectedResponse>"
    #Examples:
     # | id        | expectedResponse |
      #| 119888711 | response1.json   |


  Scenario: User gets top movies
    When I query the 10 top movies
    And I add the movies in the favorite movie list
    Then I should have all the movies in the favorite movie list

