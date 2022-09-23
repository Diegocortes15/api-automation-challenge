package specs;

import controller.TVShowController;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TVShowSpec {

    private final TVShowController tvShowController = new TVShowController();

    @Test
    public void getTVShowDetailsTest() {
        Response response = tvShowController.getTVShowDetails(890);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().get("name"), equalTo("Neon Genesis Evangelion"));
        assertThat(response.jsonPath().get("number_of_seasons"), equalTo(1));
        assertThat(response.jsonPath().get("number_of_episodes"), equalTo(26));
    }

    @Test
    public void getTVShowEpisodeGroupsTest() {
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
