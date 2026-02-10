package tests.tasks;

import base.BaseTest;
import client.AuthClient;
import client.TaskClient;
import config.ConfigManager;
import model.auth.TaskRequest;
import model.auth.TaskResponse;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LoggerUtil;

import java.util.List;

public class GetTasksApiTest extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(GetTasksApiTest.class);

    @Test
    public void shouldFetchTasksForLoggedInUser() {

        log.info("Starting Get Tasks API test");

        String token = AuthClient.loginAndGetToken(
                ConfigManager.get("test.username"),
                ConfigManager.get("test.password")
        );

        TaskRequest request = new TaskRequest();
        request.setTitle("Get Tasks API Test");
        request.setDescription("Verify task exists in list");
        request.setCompleted(false);

        TaskResponse createdTask = TaskClient.createTask(token, request);

        List<TaskResponse> tasks = TaskClient.getTasks(token);

        boolean taskFound = tasks.stream().anyMatch(task ->
                task.getId() == createdTask.getId() &&
                        task.getTitle().equals(request.getTitle()) &&
                        task.getDescription().equals(request.getDescription())
        );

        Assert.assertTrue(taskFound,
                "Created task should be present in Get Tasks response");

        log.info("Get Tasks API test completed successfully");
    }
}
