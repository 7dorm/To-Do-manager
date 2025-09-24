package ru.nsu.dunaev;

import ru.nsu.dunaev.enums.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager<ID> {
    private final List<Task<ID>> tasks = new ArrayList<>();

    public void addTask(Task<ID> task) {
        tasks.add(task);
    }

    public String toString(){
        return tasks.stream().map(Task::toString).collect(Collectors.joining("\n"));
    }

    public void removeTask(ID id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public List<Task<ID>> getAllTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public List<Task<ID>> getTasksByStatus(Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }
}
