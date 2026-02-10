package tests.auth;

import client.AuthClient;
import config.ConfigManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class RegisterApiTest {

    private static final Logger log =
            utils.LoggerUtil.getLogger(RegisterApiTest.class);

    @Test
    public void shouldRegisterUserSuccessfully() {
        log.info("Starting Register API test");

        // 1️⃣ Generate unique email ONLY for this test
        String email = "test_" + System.currentTimeMillis() + "@mail.com";
        String password = "Password@123";

        log.info("Registering new user with email: {}", email);


        AuthClient.registerUser(email,password);

        log.info("Register API test completed");
    }
}
