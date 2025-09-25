package ru.nsu.dunaev.realizations;

import ru.nsu.dunaev.Localization;
import ru.nsu.dunaev.enums.Status;
import ru.nsu.dunaev.interfaces.OutputHandler;
import ru.nsu.dunaev.interfaces.ReportGenerator;

import java.util.Locale;
import java.util.Map;

public class TaskReportGenerator<T extends Map<?, ?>> implements ReportGenerator<T> {
    private final OutputHandler output;
    public TaskReportGenerator(OutputHandler output) {
        this.output = output;
    }

    @Override
    public void generateReport(T analysisResult) {
        analysisResult.forEach((element, count) ->
                output.print(element + ": " + count));
    }
}
