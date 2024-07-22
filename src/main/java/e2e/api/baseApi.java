package e2e.api;

import e2e.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

import io.restassured.specification.ResponseSpecification;

import static e2e.api.enums.Route.BASE_PATH;
import static io.restassured.RestAssured.given;

public class baseApi {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(ConfigLoader.getInstance().getBaseUriAPI()).
                //addHeader("Authorization", "Bearer " + token).
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
                spec(getResponseSpec()).
                extract().response();
    }
}
