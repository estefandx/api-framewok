package e2e.api;

import e2e.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static e2e.api.enums.Route.BASE_PATH;
import static io.restassured.RestAssured.given;

public class baseApi {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(ConfigLoader.getInstance().getBaseUriAPI()).
                addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZmE0NmU4YjdjYzgzZGNmOWM0Y2NlMTU3ZTk4NzkxMiIsIm5iZiI6MTcyMDk3MjcyMy45OTIwNTYsInN1YiI6IjY2OTNmM2E3YWY2MzU5NDIwZDAyNzU5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.L4LrKR18dP09eG-2v_WYOr4wg7cfrgwqXF65OjUllMs").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public static Response post(String path, Object payLoad) {

        return given(getRequestSpec()).
                body(payLoad).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response get(String path) {

        return given(getRequestSpec()).
                when().
                get(path).
                then().
                //spec(getResponseSpec()).
                extract().response();
    }

    public static Response get(String path, Map<String, String> queryParams) {
        return given(getRequestSpec())
                .queryParams(queryParams)
                .get(path)
                .then().
                spec(getResponseSpec()).
                extract().response();
    }
}
