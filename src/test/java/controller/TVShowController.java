package controller;

import data.Credentials;
import io.restassured.response.Response;

import java.util.HashMap;

public class TVShowController extends BaseController {

    private final String resource = "/tv";

    public Response getTVShowDetails(int tv_id) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", Credentials.USER_API_KEY);
        String path = resource + "/" + tv_id;
        return makeGetRequest(queryParams, path);
    }

    public Response getTVShowAlternativeTitles(int tv_id) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", Credentials.USER_API_KEY);
        String path = resource + "/" + tv_id + "/alternative_titles";
        return makeGetRequest(queryParams, path);
    }


}
