package ru.nsu.dunaev.realizations;

import ru.nsu.dunaev.taskManagment.Task;
import ru.nsu.dunaev.interfaces.Analyzer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TaskAnalyzer<ID> implements Analyzer<Map<Task<ID>, Long>, ID> {
    private static final Logger logger = Logger.getLogger(TaskAnalyzer.class.getName());
    AtomicLong counter = new AtomicLong();
    @Override
    public Map<Task<ID>, Long> analyze(List<Task<ID>> tasks) {
        logger.info("analyzing tasks");
        return tasks.stream()
                .collect(Collectors.toMap(
                        task -> task,
                        task -> counter.incrementAndGet()
                ));
    }
}
