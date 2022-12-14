package specs;

import com.google.gson.Gson;
import controller.AuthController;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.CreateSession;
import models.RequestToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import data.Credentials;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class AuthSpec {

    private static final Logger authSpecLogger = LogManager.getLogger("auth-spec");
    private static final Logger sutLogger = LogManager.getLogger("sut");
    private final AuthController authController = new AuthController();

    @Test
    @Description("Test: Verify session id has been created successfully")
    public void createSession() {
        sutLogger.info("Test: Verify session id has been created successfully");
        authSpecLogger.info("Test: Verify session id has been created successfully");
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
