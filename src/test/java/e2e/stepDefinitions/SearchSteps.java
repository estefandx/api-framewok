package e2e.stepDefinitions;

import e2e.api.SearchApi;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;

import static org.hamcrest.Matchers.*;

public class SearchSteps {
    Response response;
    String expectedDataPath = "src/test/resources/expectedData/Search/";

    @When("I query SearchAPI with id {string}")
    public void iQuerySearchAPIWithId(String id) {
        response = SearchApi.getEntityById(id);
    }

    @Then("I should have the response matching {string}")
    public void iShouldHaveTheResponseMatchingExpectedResponse(String responsePath) {

        JsonPath expectedJson = new JsonPath(new File(expectedDataPath + responsePath));
        response.then().body("", equalTo(expectedJson.getMap("")));
    }
}
