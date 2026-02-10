package model.auth;

public class TaskResponse {
    private int id;
    private String title;
    private String description;
    private boolean completed;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
}
