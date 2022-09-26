package specs;

import com.google.gson.Gson;
import controller.ListController;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.RateMovie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import controller.MovieController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MovieSpec extends Hooks {

    private static final Logger sutLogger = LogManager.getLogger("sut");
    private static final Logger movieSpecLogger = LogManager.getLogger("movie-spec");
    private final MovieController movieController = new MovieController();

    @Test
    @Description("Test: Should provide the details from a movie")
    public void movieDetailsTest() {
        sutLogger.info("Test: Should provide the details from a movie");
        movieSpecLogger.info("Test: Should provide the details from a movie");

        Response response = movieController.getMovieDetails(4935);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().get("title"), containsString("Howl's Moving Castle"));
        assertThat(response.jsonPath().get("release_date"), equalTo("2004-09-09"));
        assertThat(response.jsonPath().get("production_companies.name"), hasItem("Studio Ghibli"));
    }

    @Test
    @Description("Test: Should update the movie rated vote")
    public void ratedMovieTest() {
        sutLogger.info("Test: Should update the movie rated vote");
        movieSpecLogger.info("Test: Should update the movie rated vote");

        RateMovie rateMovie = new RateMovie(10);
        String rateMovieJson = new Gson().toJson(rateMovie);

        Response response = movieController.postRateMovie(4935, rateMovieJson);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().get("status_message"), containsString("The item/record was updated successfully"));
    }
}
