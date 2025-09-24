package ru.nsu.dunaev.realizations;

import ru.nsu.dunaev.enums.Status;
import ru.nsu.dunaev.interfaces.OutputHandler;
import ru.nsu.dunaev.interfaces.ReportGenerator;

import java.util.Map;

public class TaskReportGenerator<ID> implements ReportGenerator<Map<Status, Long>> {
    private final OutputHandler output;

    public TaskReportGenerator(OutputHandler output) {
        this.output = output;
    }

    @Override
    public void generateReport(Map<Status, Long> analysisResult) {
        analysisResult.forEach((status, count) ->
                output.print(status + ": " + count));
    }
}
