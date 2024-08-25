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


  Scenario: Create a list of movies, search a movie by name, and add it to the list
   # When I create a list for movies
    #  | name        | description              | language |
     # | top list 33 | this is my personal list | en       |
    And I search for a movie by name "The Matrix Reloaded" of the year "2003"
    And I validate that the movie is not in the list
    And I add the movie to the list
    Then I should have all the movies in the new list




