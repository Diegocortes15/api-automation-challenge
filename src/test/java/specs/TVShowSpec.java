package specs;

import controller.TVShowController;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TVShowSpec {

    private static final Logger sutLogger = LogManager.getLogger("sut");
    private static final Logger tvShowSpec = LogManager.getLogger("tv-show-spec");
    private final TVShowController tvShowController = new TVShowController();

    @Test
    @Description("Test: Should provide the details from a TV show")
    public void getTVShowDetailsTest() {
        sutLogger.info("Test: Should provide the details from a TV show");
        tvShowSpec.info("Test: Should provide the details from a TV show");

        Response response = tvShowController.getTVShowDetails(890);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().get("name"), equalTo("Neon Genesis Evangelion"));
        assertThat(response.jsonPath().get("number_of_seasons"), equalTo(1));
        assertThat(response.jsonPath().get("number_of_episodes"), equalTo(26));
    }

    @Test
    @Description("Test: Should provide the alternatives titles from a TV show")
    public void getTVShowAlternativeTitlesTest() {
        sutLogger.info("Test: Should provide the alternatives titles from a TV show");
        tvShowSpec.info("Test: Should provide the alternatives titles from a TV show");

        Response response = tvShowController.getTVShowAlternativeTitles(890);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().get("results.title"), hasItems(
                "Neon Genesis Evangelion",
                "Shin Seiki Evangerion",
                "Shinseiki Evangelion",
                "Gospel of a New Century"
        ));
    }
}
