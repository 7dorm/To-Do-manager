package ru.nsu.dunaev.interfaces;

public interface ReportGenerator<T> {
    void generateReport(T analysisResult);
}
