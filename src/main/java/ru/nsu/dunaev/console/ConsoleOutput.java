package ru.nsu.dunaev.console;

import ru.nsu.dunaev.interfaces.OutputHandler;

public class ConsoleOutput implements OutputHandler {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
