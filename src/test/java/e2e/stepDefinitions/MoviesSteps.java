package e2e.stepDefinitions;

import e2e.api.AccountApi;
import e2e.api.ListApi;
import e2e.api.SearchApi;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class MoviesSteps {
    Response response;
    List<String> listIdMovies = new ArrayList<>();
    String idList = "8311527";
    Integer idMovie;

    @When("I query the {int} top movies")
    public void iQueryTopMovies(int numTopMovies) {
        response = AccountApi.getRatedMovies();
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> movies = jsonPath.getList("results");

        listIdMovies = movies.stream()
                .map(movie -> movie.get("id").toString())
                .limit(numTopMovies)
                .collect(Collectors.toList());
        listIdMovies.forEach(System.out::println);
    }


    @And("I add the movies in the favorite movie list")
    public void iAddTheMoviesInTheFavoriteMovieList() {
        for (String id : listIdMovies) {
            Map<String, Object> payload = Map.of(
                    "media_type", "movie",
                    "media_id", Integer.parseInt(id),
                    "favorite", true
            );
            Response response = AccountApi.postAddFavotireMovie(payload);


        }
    }

    @Then("I should have all the movies in the favorite movie list")
    public void iShouldHaveAllTheMoviesInTheFavoriteMovieList() {
    }

    @When("I create a list for movies")
    public void i_create_a_list_for_movies(DataTable dataTable) {
        List<Map<String, String>> moviesList = dataTable.asMaps(String.class, String.class);


        Map<String, String> firstDataRow = moviesList.get(0);


        String name = firstDataRow.get("name");
        String description = firstDataRow.get("description");
        String language = firstDataRow.get("language");

        Map<String, Object> payload = Map.of(
                "name", name ,
                "description", description,
                "language", language
        );
        response = ListApi.postCreateList(payload);
        response.then().statusCode(201);
        idList = response.jsonPath().getString("list_id");
        System.out.println("value id" + idList);


    }

    @When("I search for a movie by name {string} of the year {string}")
    public void i_search_for_a_movie_by_name_of_the_year(String name, String year) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("query", name);
        queryParams.put("page", "1");
        queryParams.put("year",year);
        response = SearchApi.getSearchMovie(queryParams);

        List<Map<String, ?>> results = response.jsonPath().getList("results");


        Map<String, ?> movie = results.stream()
                .filter(m -> name.equals(m.get("title")))
                .findFirst()
                .orElse(null);

        if (movie != null) {

            idMovie = (Integer) movie.get("id");


            System.out.println("Rid movie : " + idMovie);
        } else {
            System.out.println("Movie with title \"" + name + "\" not found.");
        }


    }
    @When("I validate that the movie is not in the list")
    public void i_validate_that_the_movie_is_not_in_the_list() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("movie_id", String.valueOf(idMovie));
        response = ListApi.getMoviExistList("8311527", queryParams);
        boolean itemPresent = response.jsonPath().getBoolean("item_present");
        response.then().statusCode(200);
        if(itemPresent){
            throw new AssertionError("The movie with ID " + idMovie + " is already in the list with ID " + idList);
        }




    }
    @When("I add the movie to the list")
    public void i_add_the_movie_to_the_list() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("media_id", String.valueOf(idMovie));
        response = ListApi.addItemToList(idList,requestBody);


    }
    @Then("I should have all the movies in the new list")
    public void i_should_have_all_the_movies_in_the_new_list() {
        response.then().statusCode(201);
    }
}


