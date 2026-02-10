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

public class DeleteTaskApiTest extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(DeleteTaskApiTest.class);

    @Test
    public void shouldDeleteTaskSuccessfully() {

        log.info("Starting Delete Task API test");

        String token = AuthClient.loginAndGetToken(
                ConfigManager.get("test.username"),
                ConfigManager.get("test.password")
        );

        // Create task
        TaskRequest request = new TaskRequest();
        request.setTitle("Delete Task Test");
        request.setDescription("To be deleted");
        request.setCompleted(false);

        TaskResponse created = TaskClient.createTask(token, request);
        int taskId = created.getId();

        // Delete task
        TaskClient.deleteTask(token, taskId);

        // Verify via Get Tasks
        List<TaskResponse> tasks = TaskClient.getTasks(token);

        boolean taskStillExists = tasks.stream()
                .anyMatch(t -> t.getId() == taskId);

        Assert.assertFalse(taskStillExists,
                "Deleted task should not appear in Get Tasks response");

        log.info("Delete Task API test completed successfully");
    }
}
