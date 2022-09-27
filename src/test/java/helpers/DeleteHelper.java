package helpers;

import controller.ListController;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteHelper {
    private static final Logger listSpecLogger = LogManager.getLogger("list-spec");
    private static final Logger sutLogger = LogManager.getLogger("sut");
    public static void deleteList(String list_id) {
        sutLogger.info("Remove movie list from test");
        listSpecLogger.info("Remove movie list from test");

        ListController listController = new ListController();

        Response responseDeleteList = listController.deleteList(list_id);
        assertThat(responseDeleteList.statusCode(), equalTo(500));

        Response responseGetListDetails = listController.getListDetails(list_id);
        assertThat(responseGetListDetails.statusCode(), equalTo(404));
        assertThat(responseGetListDetails.jsonPath().get("success"), equalTo(false));
        assertThat(responseGetListDetails.jsonPath().get("status_message"), containsString("The resource you requested could not be found"));
    }
}
