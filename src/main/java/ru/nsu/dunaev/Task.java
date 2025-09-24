package ru.nsu.dunaev;


import ru.nsu.dunaev.enums.Priority;
import ru.nsu.dunaev.enums.Status;

public class Task<ID> {
    private final ID id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;

    public Task(ID id, String title, String description, Priority priority, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }

    public ID getId() { return id; }
    public String getTitle() { return title; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Priority getPriority() { return priority; }

    public void markDone() { this.status = Status.DONE; }
    public void markInProgress() { this.status = Status.IN_PROGRESS; }
}
