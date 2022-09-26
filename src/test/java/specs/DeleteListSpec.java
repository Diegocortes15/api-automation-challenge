package specs;

import com.google.gson.Gson;
import controller.ListController;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.CreateMovieList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteListSpec {

    private static final Logger listSpecLogger = LogManager.getLogger("list-spec");
    private static final Logger sutLogger = LogManager.getLogger("sut");
    private final ListController listController = new ListController();

    @Test
    @Description("Test: Should delete a movie list")
    public void deleteListTest() {
        sutLogger.info("Test: Should delete a movie list");
        listSpecLogger.info("Test: Should delete a movie list");

        CreateMovieList createMovieList = new CreateMovieList(
                "This is my awesome test list",
                "None Description1",
                "en");
        String jsonList = new Gson().toJson(createMovieList);

        Response responseCreateList = listController.createList(jsonList);
        assertThat(responseCreateList.statusCode(), equalTo(201));
        assertThat(responseCreateList.jsonPath().get("$"), hasKey("list_id"));
        assertThat(responseCreateList.jsonPath().get("status_message"), containsString("The item/record was created successfully"));
        assertThat(responseCreateList.jsonPath().get("success"), equalTo(true));

        String listId = Integer.toString(responseCreateList.jsonPath().get("list_id"));

        Response responseDeleteList = listController.deleteList(listId);
        assertThat(responseDeleteList.statusCode(), equalTo(500));

        Response responseGetListDetails = listController.getListDetails(listId);
        assertThat(responseGetListDetails.statusCode(), equalTo(404));
        assertThat(responseGetListDetails.jsonPath().get("success"), equalTo(false));
        assertThat(responseGetListDetails.jsonPath().get("status_message"), containsString("The resource you requested could not be found"));
    }
}
