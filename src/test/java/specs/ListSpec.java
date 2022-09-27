package specs;

import com.google.gson.Gson;
import controller.ListController;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.AddMovieToList;
import models.CreateMovieList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static helpers.DeleteHelper.deleteList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ListSpec extends Hooks {

    private static final Logger listSpecLogger = LogManager.getLogger("list-spec");
    private static final Logger sutLogger = LogManager.getLogger("sut");
    private final ListController listController = new ListController();

    @Test
    @Description("Test: Verify movie list has been created")
    public void createListTest() {
        sutLogger.info("Test: Verify movie list has been created");
        listSpecLogger.info("Test: Verify movie list has been created");

        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None description test",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("$"), hasKey("list_id"));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));

        String listId = Integer.toString(responseCreateList.jsonPath().get("list_id"));
        deleteList(listId);
    }

    @Test
    @Description("Test: Should provide the details from a list")
    public void getListDetailsTest() {
        sutLogger.info("Test: Should provide the details from a list");
        listSpecLogger.info("Test: Should provide the details from a list");

        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None description test",
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

        deleteList(listId);
    }

    @Test
    @Description("Test: Should add a movie in a movie list")
    public void addItemTest() {
        sutLogger.info("Test: Should add a movie in a movie list");
        listSpecLogger.info("Test: Should a add movie in a movie list");

        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None description test",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));

        String listId = Integer.toString(responseCreateList.jsonPath().get("list_id"));

        AddMovieToList addMovieToList = new AddMovieToList(157336);
        String jsonMovie = new Gson().toJson(addMovieToList);

        Response response = listController.addItem(listId, jsonMovie);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().get("success"), equalTo(true));
        assertThat(response.jsonPath().get("status_message"), containsString("The item/record was updated successfully"));

        deleteList(listId);
    }

    @Test
    @Description("Test: Should remove movies from a list")
    public void clearListTest() {
        sutLogger.info("Test: Should remove movies from a list");
        listSpecLogger.info("Test: Should remove movies from a list");

        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None description test",
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

        Response response = listController.addItem(listId, jsonMovie);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().get("success"), equalTo(true));
        assertThat(response.jsonPath().get("status_message"), containsString("The item/record was updated successfully"));

        Response responseClearList = listController.clearList(listId);
        assertThat(responseClearList.statusCode(), equalTo(201));
        assertThat(responseClearList.jsonPath().get("status_message"), containsString("The item/record was updated successfully"));

        deleteList(listId);
    }
}
