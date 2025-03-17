package api;
import api.UserAPI.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.JsonReader;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHandler {
    private static final Logger logger = LogManager.getLogger(AuthenticationHandler.class);
    private static String token;
    private static final String LOGIN_ENDPOINT = "/api/login";

    static {
        RestAssured.baseURI = UserAPI.BASE_URL;
    }

    public static String authenticate() {
        Map<String, String> credentials = JsonReader.getLoginData();

        Response response = given()
                .contentType("application/json")
                .body(credentials)
                .when()
                .post(LOGIN_ENDPOINT);

        if (response.getStatusCode() == 200) {
            token = response.jsonPath().getString("token");
            logger.info("Login successful. Token: " + token);
        } else {
            logger.error("Login failed! Status Code: " + response.getStatusCode());
        }
        return token;
    }

    public static String getToken() {
        return token;
    }
}
