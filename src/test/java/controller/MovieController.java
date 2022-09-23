package controller;

import data.Credentials;
import io.restassured.response.Response;

import java.util.HashMap;

public class MovieController extends BaseController {

    private final String resource = "/movie";

    public Response getMovieDetails(int movie_id) {
        String path = resource + "/" + movie_id;
        return makeGetRequest(path);
    }

    public Response postRateMovie(int movie_id, String body) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("session_id", Credentials.SESSION_ID);
        String path = resource + "/" + movie_id + "/rating";
        return makePostRequest(queryParams, path, body);
    }
}
