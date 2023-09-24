package helpers;
import com.google.gson.Gson;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredFactory {

    public String toJson(Object model){
        return new Gson().toJson(model);
    }

    public String getKeyValue(Response response, String key){
        return response.jsonPath().get(key);
    }

    public void assertStatusCode(Response response, int statusCode) {
        assertThat(response.statusCode(), equalTo(statusCode));
    }

    public void assertHasKey(Response response, String key) {
        assertThat(response.jsonPath().get("$"), hasKey(key));
    }

    public void assertKeyEqualTo(Response response, String key, String value) {
        assertThat(response.jsonPath().get(key), equalTo(value));
    }

    public void assertKeyEqualTo(Response response, String key, boolean value) {
        assertThat(response.jsonPath().get(key), equalTo(value));
    }

    public void assertKeyContainsString(Response response, String key, String value) {
        assertThat(response.jsonPath().get(key), containsString(value));
    }

}
