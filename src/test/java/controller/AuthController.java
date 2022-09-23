package controller;

import io.restassured.response.Response;

public class AuthController extends BaseController {

    public Response getCreateRequestToken() {
        String pathAuthenticationTokenNew = "/authentication/token/new";
        return makeGetRequest(pathAuthenticationTokenNew);
    }

    public Response postCreateSessionWithLogin(String body) {
        String pathAuthenticationTokenValidateLogin = "/authentication/token/validate_with_login";
        return makePostRequest(pathAuthenticationTokenValidateLogin, body);
    }

    public Response postCreateSession(String body) {
        String pathAuthenticationTokenValidateLogin = "/authentication/session/new";
        return makePostRequest(pathAuthenticationTokenValidateLogin, body);
    }
}
