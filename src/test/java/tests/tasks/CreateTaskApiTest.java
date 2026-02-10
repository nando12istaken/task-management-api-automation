package tests.tasks;

import client.AuthClient;
import client.TaskClient;
import config.ConfigManager;
import model.auth.TaskRequest;
import model.auth.TaskResponse;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTaskApiTest {

    private static final Logger log = utils.LoggerUtil.getLogger(CreateTaskApiTest.class);

    @Test
    public void createTask(){

        log.info("Starting Create Task API test");

        String token = AuthClient.loginAndGetToken(
                ConfigManager.get("test.username"),
                ConfigManager.get("test.password")
        );

        log.info("Login successful, token received");

        TaskRequest request = new TaskRequest();
        request.setTitle("Create task API test");
        request.setDescription("Valid create task scenarion");
        request.setCompleted(false);

        log.info("Creating task with title: {}", request.getTitle());

        TaskResponse response = TaskClient.createTask(token,request);

        Assert.assertNotNull(response.getId(),"Task id should not be null");
        Assert.assertEquals(response.getTitle(),request.getTitle());
        Assert.assertEquals(response.getDescription(), request.getDescription());
        Assert.assertFalse(response.isCompleted());


        log.info("Create task api test successfull");


    }
}
