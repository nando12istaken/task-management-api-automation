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

public class UpdateTaskApiTest extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(UpdateTaskApiTest.class);

    @Test
    public void shouldUpdateTaskSuccessfully() {

        log.info("Starting Update Task API test");

        String token = AuthClient.loginAndGetToken(
                ConfigManager.get("test.username"),
                ConfigManager.get("test.password")
        );

        // Create task
        TaskRequest request = new TaskRequest();
        request.setTitle("Before Update");
        request.setDescription("Initial description");
        request.setCompleted(false);

        TaskResponse created = TaskClient.createTask(token, request);
        int taskId = created.getId();

        // Update task
        request.setTitle("After Update");
        request.setDescription("Updated description");
        request.setCompleted(true);

        TaskClient.updateTask(token, taskId, request);

        // Verify via Get Tasks
        List<TaskResponse> tasks = TaskClient.getTasks(token);

        TaskResponse updatedTask = tasks.stream()
                .filter(t -> t.getId() == taskId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Updated task not found"));

        Assert.assertEquals(updatedTask.getTitle(), "After Update");
        Assert.assertEquals(updatedTask.getDescription(), "Updated description");
        Assert.assertTrue(updatedTask.isCompleted());

        log.info("Update Task API test completed successfully");
    }
}
