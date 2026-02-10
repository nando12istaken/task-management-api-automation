package client;

import io.restassured.http.ContentType;
import model.auth.TaskRequest;
import model.auth.TaskResponse;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TaskClient {

    private static final Logger log =
            utils.LoggerUtil.getLogger(TaskClient.class);

    public static TaskResponse createTask(String token, TaskRequest request){

        log.info("Calling Create Task API | title={}", request.getTitle());

        TaskResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("Authorization","Bearer " + token)
                        .body(request)
                        .when()
                        .post("/tasks")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(TaskResponse.class);

        log.info("Create Task API successful | taskId={}", response.getId());

        return response;
    }

    public static List<TaskResponse> getTasks(String token) {

        log.info("Calling Get Tasks API for current user");

        List<TaskResponse> tasks =
                given()
                        .log().all()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("/tasks")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList("", TaskResponse.class);

        log.info("Get Tasks API successful | taskCount={}", tasks.size());
        return tasks;
    }



    public static void updateTask(String token, int taskId, TaskRequest request) {

        log.info("Calling Update Task API | taskId={} | completed={}",
                taskId, request.isCompleted());

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .put("/tasks/" + taskId)
                .then()
                .statusCode(200);

        log.info("Update Task API successful | taskId={}", taskId);
    }


    public static void deleteTask(String token, int taskId) {

        log.info("Calling Delete Task API | taskId={}", taskId);

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/tasks/" + taskId)
                .then()
                .statusCode(204);

        log.info("Delete Task API successful | taskId={}", taskId);
    }

}
