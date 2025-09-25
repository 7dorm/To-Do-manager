package ru.nsu.dunaev.taskManagment;


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

    // Getter
    public ID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    // Setter
    public void setTitle(String title) {
        synchronized (this.title) {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        synchronized (this.description) {
            this.description = description;
        }
    }

    public void setStatus(Status status) {
        synchronized (this.status) {
            this.status = status;
        }
    }

    public void setPriority(Priority priority) {
        synchronized (this.priority) {
            this.priority = priority;
        }
    }

    public void markDone() {
        synchronized (status) {
            this.status = Status.DONE;
        }
    }

    public void markInProgress() {
        synchronized (status) {
            this.status = Status.IN_PROGRESS;
        }
    }
}
