package e2e.stepDefinitions;

import e2e.api.AccountApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class MoviesSteps {
    Response response;
    List<String> idMovies = new ArrayList<>();

    @When("I query the {int} top movies")
    public void iQueryTopMovies(int numTopMovies) {
        response = AccountApi.getRatedMovies();
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> movies = jsonPath.getList("results");

        idMovies = movies.stream()
                .map(movie -> movie.get("id").toString())
                .limit(numTopMovies)
                .collect(Collectors.toList());
        idMovies.forEach(System.out::println);
    }


    @And("I add the movies in the favorite movie list")
    public void iAddTheMoviesInTheFavoriteMovieList() {
        for (String id : idMovies) {
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
}
