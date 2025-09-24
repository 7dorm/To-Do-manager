package ru.nsu.dunaev.realizations;

import ru.nsu.dunaev.interfaces.OutputHandler;

public class ConsoleOutput implements OutputHandler {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
