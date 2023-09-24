package specs;

import controller.AuthController;
import helpers.RestAssuredFactory;
import helpers.LoggerLoad;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.CreateSession;
import models.RequestToken;
import org.testng.annotations.Test;
import data.Credentials;

public class AuthSpec {

    private final AuthController authController = new AuthController();
    private final RestAssuredFactory restAssuredFactory = new RestAssuredFactory();

    @Test
    @Description("Test: Verify session id has been created successfully")
    public void createSession() {
        LoggerLoad.info("Test: Verify session id has been created successfully");
        Response responseGetCreateRequestToken = authController.getCreateRequestToken();
        restAssuredFactory.assertStatusCode(responseGetCreateRequestToken, 200);
        restAssuredFactory.assertHasKey(responseGetCreateRequestToken, "request_token");
        Credentials.USER_REQUEST_TOKEN = restAssuredFactory.getKeyValue(responseGetCreateRequestToken, "request_token");

        CreateSession createSession = new CreateSession(
                Credentials.USER_USERNAME,
                Credentials.USER_PASSWORD,
                Credentials.USER_REQUEST_TOKEN);
        String jsonLogin = restAssuredFactory.toJson(createSession);

        Response responsePostCreateSessionWithLogin = authController.postCreateSessionWithLogin(jsonLogin);
        restAssuredFactory.assertStatusCode(responsePostCreateSessionWithLogin, 200);
        restAssuredFactory.assertHasKey(responsePostCreateSessionWithLogin, "request_token");
        restAssuredFactory.assertKeyEqualTo(responsePostCreateSessionWithLogin, "success", true);
        Credentials.USER_REQUEST_TOKEN = restAssuredFactory.getKeyValue(responsePostCreateSessionWithLogin, "request_token");

        RequestToken requestToken = new RequestToken(Credentials.USER_REQUEST_TOKEN);
        String jsonSession = restAssuredFactory.toJson(requestToken);

        Response responsePostCreateSession = authController.postCreateSession(jsonSession);
        restAssuredFactory.assertStatusCode(responsePostCreateSession, 200);
        restAssuredFactory.assertHasKey(responsePostCreateSession, "session_id");
        Credentials.SESSION_ID = restAssuredFactory.getKeyValue(responsePostCreateSession, "session_id");
    }
}
