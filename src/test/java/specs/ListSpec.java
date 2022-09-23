package specs;

import com.google.gson.Gson;
import controller.ListController;
import io.restassured.response.Response;
import models.AddMovieToList;
import models.CreateMovieList;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ListSpec extends Hooks {

    private final ListController listController = new ListController();

    @Test
    public void createListTest() {
        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None Description",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("$"), hasKey("list_id"));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));
    }

    @Test
    public void getListDetailsTest() {
        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None Description",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("$"), hasKey("list_id"));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));

        String listId = Integer.toString(responseCreateList.jsonPath().get("list_id"));

        Response response = listController.getListDetails(listId);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().get("id"), equalTo(listId));
        assertThat(response.jsonPath().get("name"), containsString("This is my awesome test list"));
        assertThat(response.jsonPath().get("iso_639_1"), equalTo("en"));
    }

    @Test
    public void addItemsTest() {
        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None Description",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));

        String listId = Integer.toString(responseCreateList.jsonPath().get("list_id"));

        AddMovieToList addMovieToList = new AddMovieToList(157336);
        String jsonMovie = new Gson().toJson(addMovieToList);

        Response response = listController.addItems(listId, jsonMovie);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().get("success"), equalTo(true));
        assertThat(response.jsonPath().get("status_message"), containsString("The item/record was updated successfully"));
    }

    @Test
    public void clearListTest() {
        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None Description",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("$"), hasKey("list_id"));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));

        String listId = Integer.toString(responseCreateList.jsonPath().get("list_id"));

        AddMovieToList addMovieToList = new AddMovieToList(157336);
        String jsonMovie = new Gson().toJson(addMovieToList);

        Response response = listController.addItems(listId, jsonMovie);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().get("success"), equalTo(true));
        assertThat(response.jsonPath().get("status_message"), containsString("The item/record was updated successfully"));

        Response responseClearList = listController.clearList(listId);
        assertThat(responseClearList.statusCode(), equalTo(201));
        assertThat(responseClearList.jsonPath().get("status_message"), containsString("The item/record was updated successfully"));
    }

    @Test
    public void deleteListTest() {
        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None Description",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("$"), hasKey("list_id"));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));

        String listId = Integer.toString(responseCreateList.jsonPath().get("list_id"));

        Response responseClearList = listController.deleteList(listId);
        assertThat(responseClearList.statusCode(), equalTo(200));
        assertThat(responseClearList.jsonPath().get("status_message"), containsString("The item/record was deleted successfully"));
    }
}
