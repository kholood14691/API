package utilities;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static io.restassured.RestAssured.*;

public class RestHandler {

    private static final Logger logger = LogManager.getLogger(RestHandler.class);
    private static String token;


    public static Response getRequest(String endpoint) {
        logger.info("Sending GET request to URL: " + endpoint);

        Response response = given()
                .when()
                .get(endpoint);

        logResponseDetails(response);
        return response;
    }

    public static Response postRequest(String endpoint, Object body) {
        logger.info("Sending POST request to URL: " + endpoint);
        logger.info("Request body: " + body.toString());

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);

        logResponseDetails(response);
        return response;
    }

    public static Response putRequest(String endpoint, Object body) {
        logger.info("Sending PUT request to URL: " + endpoint);
        logger.info("Request body: " + body.toString());

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);

        logResponseDetails(response);
        return response;
    }

    public static Response deleteRequest(String endpoint) {
        logger.info("Sending DELETE request to URL: " + endpoint);

        Response response = given()
                .when()
                .delete(endpoint);

        logResponseDetails(response);
        return response;
    }

    private static void logResponseDetails(Response response) {
        if (response != null) {
            logger.info("Response status code: " + response.getStatusCode());
            logger.info("Response body: " + response.getBody().asString());
            logger.info("Response headers: " + response.getHeaders());
        } else {
            logger.error("Response is null");
        }
    }
}
