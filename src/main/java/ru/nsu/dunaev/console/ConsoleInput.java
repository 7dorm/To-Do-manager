package ru.nsu.dunaev.console;

import ru.nsu.dunaev.interfaces.InputHandler;

import java.util.Scanner;

public class ConsoleInput<S> implements InputHandler<String> {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String process() {
        return scanner.nextLine();
    }
}
