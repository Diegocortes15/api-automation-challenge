package specs;

import com.google.gson.Gson;
import controller.AuthController;
import io.restassured.response.Response;
import data.Credentials;
import models.CreateSession;
import models.RequestToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public abstract class Hooks {

    private static final Logger sutLogger = LogManager.getLogger("sut");
    private static final Logger authSpecLogger = LogManager.getLogger("auth-spec");
    private static final Logger listSpecLogger = LogManager.getLogger("list-spec");
    private static final Logger movieSpecLogger = LogManager.getLogger("movie-spec");
    protected final AuthController authController = new AuthController();

    @BeforeSuite
    public void createSession() {
        sutLogger.info("Hook Before Suite: Generating session id");
        authSpecLogger.info("Hook Before Suite: Generating session id");
        listSpecLogger.info("Hook Before Suite: Generating session id");
        movieSpecLogger.info("Hook Before Suite: Generating session id");

        Response responseGetCreateRequestToken = authController.getCreateRequestToken();
        assertThat(responseGetCreateRequestToken.statusCode(), equalTo(200));
        assertThat(responseGetCreateRequestToken.jsonPath().get("$"), hasKey("request_token"));
        Credentials.USER_REQUEST_TOKEN = responseGetCreateRequestToken.jsonPath().get("request_token");

        CreateSession createSession = new CreateSession(
                Credentials.USER_USERNAME,
                Credentials.USER_PASSWORD,
                Credentials.USER_REQUEST_TOKEN);
        String jsonLogin = new Gson().toJson(createSession);

        Response responsePostCreateSessionWithLogin = authController.postCreateSessionWithLogin(jsonLogin);
        assertThat(responsePostCreateSessionWithLogin.statusCode(), equalTo(200));
        assertThat(responsePostCreateSessionWithLogin.jsonPath().get("$"), hasKey("request_token"));
        assertThat(responsePostCreateSessionWithLogin.jsonPath().get("success"), equalTo(true));
        Credentials.USER_REQUEST_TOKEN = responsePostCreateSessionWithLogin.jsonPath().get("request_token");

        RequestToken requestToken = new RequestToken(Credentials.USER_REQUEST_TOKEN);
        String jsonSession = new Gson().toJson(requestToken);

        Response responsePostCreateSession= authController.postCreateSession(jsonSession);
        assertThat(responsePostCreateSession.statusCode(), equalTo(200));
        assertThat(responsePostCreateSession.jsonPath().get("$"), hasKey("session_id"));
        Credentials.SESSION_ID = responsePostCreateSession.jsonPath().get("session_id");
    }
}
