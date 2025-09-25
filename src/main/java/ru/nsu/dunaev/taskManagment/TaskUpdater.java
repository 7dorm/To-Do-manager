package ru.nsu.dunaev.taskManagment;

import ru.nsu.dunaev.enums.Status;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskUpdater<ID> {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void parallelUpdateStatus(List<Task<ID>> tasks, Status oldStatus, Status newStatus) {
        List<Callable<Void>> callables = tasks.stream()
                .filter(task -> task.getStatus() == oldStatus)
                .map(task -> (Callable<Void>) () -> {
                    System.out.println(Thread.currentThread().getName() + " updating task: " + task.getTitle());
                    task.setStatus(newStatus);
                    return null;
                }).toList();

        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
