package controller;

import data.Credentials;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import helpers.Constants;

import java.util.HashMap;

public abstract class BaseController {

    protected RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Constants.BASE_URI)
            .setContentType(ContentType.JSON)
            .addQueryParam("api_key", Credentials.USER_API_KEY)
            .setConfig(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
            .build();

    protected ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .build().logDetail(LogDetail.ALL);

    protected Response makeGetRequest(String path) {
        return RestAssured.given()
                .spec(requestSpecification)
        .when()
                .get(path)
        .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makeGetRequest(HashMap<String, String> queryParams, String path) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
        .when()
                .get(path)
        .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makePostRequest(HashMap<String, String> queryParams, String path) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
        .when()
                .post(path)
        .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makePostRequest(String path, String body) {
        return RestAssured.given()
                .spec(requestSpecification)
                .body(body)
        .when()
                .post(path)
        .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makePostRequest(HashMap<String, String> queryParams, String path, String body) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .body(body)
        .when()
                .post(path)
        .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makeDeleteRequest(HashMap<String, String> queryParams, String path) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
        .when()
                .delete(path)
        .then()
                .spec(responseSpecification)
                .extract().response();
    }
}
