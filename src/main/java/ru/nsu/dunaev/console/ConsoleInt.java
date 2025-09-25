package ru.nsu.dunaev.console;

import ru.nsu.dunaev.Localization;

import ru.nsu.dunaev.enums.Priority;
import ru.nsu.dunaev.enums.Status;

import ru.nsu.dunaev.interfaces.InputHandler;
import ru.nsu.dunaev.interfaces.OutputHandler;
import ru.nsu.dunaev.interfaces.ReportGenerator;
import ru.nsu.dunaev.interfaces.Analyzer;

import ru.nsu.dunaev.realizations.TaskAnalyzer;
import ru.nsu.dunaev.realizations.TaskReportGenerator;

import ru.nsu.dunaev.taskManagment.Task;
import ru.nsu.dunaev.taskManagment.TaskManager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class ConsoleInt<T> {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private static final Logger logger = Logger.getLogger(ConsoleInt.class.getName());

    private final InputHandler<String> inputHandler = new ConsoleInput<>();
    private final OutputHandler outputHandler = new ConsoleOutput();
    private final TaskManager<T> taskManager = new TaskManager<>();
    private final Localization localization;
    private final ReportGenerator<Map<?, Long>> taskReportGenerator = new TaskReportGenerator<>(outputHandler);
    private final Analyzer<Map<Task<T>, Long>, T> taskAnalyzer = new TaskAnalyzer<>();

    public ConsoleInt(Localization loc) {
        this.localization = loc;
        logger.warning("Console initialized");
    }

     public void run(T type) throws ExecutionException, InterruptedException {
        System.out.println(localization.get());
        String input = inputHandler.process();

         switch (input) {
             case "1" -> {
                 logger.info("add task");
                 System.out.println(localization.get()); // ask title

                 String title = inputHandler.process();
                 System.out.println(localization.get()); // ask description

                 String description = inputHandler.process();
                 System.out.println(localization.get()); // ask priority

                 Priority priority = switch (inputHandler.process()) {
                     case "2" -> Priority.MEDIUM;
                     case "3" -> Priority.HIGH;
                     default -> Priority.LOW;
                 };

                 if (type instanceof UUID) {
                     taskManager.addTask(new Task<>(
                             (T) UUID.randomUUID(),
                             title,
                             description,
                             priority,
                             Status.NEW));
                 } else if (type instanceof Integer) {
                     taskManager.addTask(new Task<>(
                             (T) taskManager.getLength(),
                             title,
                             description,
                             priority,
                             Status.NEW));
                 } else if (type instanceof String) {
                     System.out.println(localization.get());
                     String id = inputHandler.process();
                     taskManager.addTask(new Task<>(
                             (T) id,
                             title,
                             description,
                             priority,
                             Status.NEW));
                 }
                 logger.info("task created");
             }
             case "2" -> {
                 logger.info("task list");
                 this.taskReportGenerator.generateReport(
                         this.taskAnalyzer.analyze(
                                 this.taskManager.getAllTasks()
                         )
                 );
             }
             case "3" -> {
                 logger.info("task remove");
                 this.taskReportGenerator.generateReport(
                         this.taskAnalyzer.analyze(
                                 this.taskManager.getAllTasks()
                         )
                 );
                 // not implemented
             }
             case "4" -> {
                 return;
             }
         }
        this.run(type);
    }

    public void shutdown() {
        executor.shutdown();
    }

}
