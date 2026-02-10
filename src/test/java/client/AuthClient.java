package client;

import model.auth.LoginRequest;
import model.auth.LoginResponse;
import model.auth.RegisterRequest;
import org.apache.logging.log4j.Logger;


import static io.restassured.RestAssured.given;

public class AuthClient {
    private static final Logger log = utils.LoggerUtil.getLogger(AuthClient.class);

    public static void registerUser(String email, String password){
        log.info("Calling Register API for user {}", email);

        RegisterRequest request = new RegisterRequest();
        request.setEmail(email);
        request.setPassword(password);

        given()
                .log().all()
                .contentType("application/json")
                .body(request)
                .when()
                .post("auth/register")
                .then()
                .log().all()
                .statusCode(201);

        log.info("Register API call completed");

    }

    public static String loginAndGetToken(String email, String password){
        log.info("Calling Login API for user {}", email);

        LoginRequest request = new LoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        LoginResponse response =
                given()
                        .log().all()
                        .contentType("application/json")
                        .body(request)
                        .when()
                        .post("auth/login")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(LoginResponse.class);

        log.info("Login API successful, token received");
        return response.getToken();

    }

}
