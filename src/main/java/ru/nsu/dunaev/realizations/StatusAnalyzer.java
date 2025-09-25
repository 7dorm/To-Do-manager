package ru.nsu.dunaev.realizations;

import ru.nsu.dunaev.taskManagment.Task;
import ru.nsu.dunaev.enums.Status;
import ru.nsu.dunaev.interfaces.Analyzer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatusAnalyzer<ID> implements Analyzer<Map<Status, Long>, ID> {
    @Override
    public Map<Status, Long> analyze(List<Task<ID>> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }
}

