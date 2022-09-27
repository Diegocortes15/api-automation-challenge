package controller;

import data.Credentials;
import io.restassured.response.Response;

import java.util.HashMap;

public class ListController extends BaseController {

    private final String resource = "/list";

    public Response createList(String body) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("session_id", Credentials.SESSION_ID);
        return makePostRequest(queryParams, resource, body);
    }

    public Response getListDetails(String listId) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("session_id", Credentials.SESSION_ID);

        String path = resource + "/" + listId;
        return makeGetRequest(queryParams, path);
    }

    public Response addItem(String listId, String body) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("session_id", Credentials.SESSION_ID);
        String path = resource + "/" + listId + "/add_item";
        return makePostRequest(queryParams, path, body);
    }

    public Response clearList(String listId) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("session_id", Credentials.SESSION_ID);
        queryParams.put("confirm", "true");
        String path = resource + "/" + listId + "/clear";
        return makePostRequest(queryParams, path);
    }

    public Response deleteList(String listId) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("session_id", Credentials.SESSION_ID);
        String path = resource + "/" + listId;
        return makeDeleteRequest(queryParams, path);
    }
}
