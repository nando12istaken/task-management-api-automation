package tests.auth;

import base.BaseTest;
import client.AuthClient;
import config.ConfigManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

public class LoginApiTest extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(LoginApiTest.class);

    @Test
    public void shouldLoginSuccessfully() {
        log.info("Starting Login API test");

        String token = AuthClient.loginAndGetToken(
                ConfigManager.get("test.username"),
                ConfigManager.get("test.password")
        );

        Assert.assertNotNull(token);
        log.info("Login API test completed");
    }
}
