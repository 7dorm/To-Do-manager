package ru.nsu.dunaev.interfaces;

import ru.nsu.dunaev.Task;

import java.util.List;

public interface Analyzer<T, ID> {
    T analyze(List<Task<ID>> tasks);
}
