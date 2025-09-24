package ru.nsu.dunaev;


import ru.nsu.dunaev.enums.Priority;
import ru.nsu.dunaev.enums.Status;
import ru.nsu.dunaev.interfaces.OutputHandler;
import ru.nsu.dunaev.realizations.ConsoleOutput;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        TaskManager<UUID> manager = new TaskManager<>();

        manager.addTask(new Task<>(UUID.randomUUID(), "Buy groceries", "", Priority.HIGH, Status.NEW));
        manager.addTask(new Task<>(UUID.randomUUID(), "Write report", "", Priority.MEDIUM, Status.NEW));

        OutputHandler consoleOutput = new ConsoleOutput();

        consoleOutput.print(manager.toString());

    }
}
