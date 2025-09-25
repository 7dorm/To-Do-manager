package ru.nsu.dunaev.taskManagment;

import ru.nsu.dunaev.enums.Status;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class TaskManager<ID> {
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());
    private final Map<ID, Task<ID>> tasks = new ConcurrentHashMap<>();

    public synchronized void addTask(Task<ID> task) {
        tasks.put(task.getId(), task);
    }

    public synchronized void removeTask(ID id) {
        tasks.remove(id);
    }

    public Integer getLength(){
        return tasks.size();
    }

    public String toString(){
        return "Not implemented"; // tasks.stream().map(Task::toString).collect(Collectors.joining("\n"));
    }

    public List<Task<ID>> getAllTasks() {
        logger.info("getAllTasks");
        List<Task<ID>> result = new ArrayList<>();
        for (ID taskID : tasks.keySet()) {
            result.add(tasks.get(taskID));
        }
        logger.info(result.toString());
        return Collections.unmodifiableList(result);
    }

    public synchronized List<Task<ID>> getTasksByStatus(Status status) {
        List<Task<ID>> result = new ArrayList<>();
        for (ID taskId : tasks.keySet()) {
            if (tasks.get(taskId).getStatus().equals(status)) {result.add(tasks.get(taskId));}
        }
        return Collections.unmodifiableList(result);
    }
}
