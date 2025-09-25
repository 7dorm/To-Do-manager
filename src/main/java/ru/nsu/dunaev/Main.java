package ru.nsu.dunaev;


import ru.nsu.dunaev.console.ConsoleInt;

import ru.nsu.dunaev.enums.Language;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.*;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        setupLogging();

        Language en = Language.ENGLISH;
        Localization localization = new Localization(en);

        ConsoleInt<UUID> consoleInt = new ConsoleInt<>(localization);

        UUID uuid = UUID.randomUUID();
        logger.info("Console.start");
        try {
            consoleInt.run(uuid);
        } catch (ExecutionException | InterruptedException e) {
            consoleInt.shutdown();
            throw new RuntimeException(e);
        }

        consoleInt.shutdown();
    }

    private static void setupLogging() throws IOException{
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.ALL);

        // Remove default console handler
        for (Handler h : rootLogger.getHandlers()) {
            rootLogger.removeHandler(h);
        }

        // Console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
        rootLogger.addHandler(consoleHandler);

        // File handler
        FileHandler fileHandler = new FileHandler("app.log", true);
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());
        rootLogger.addHandler(fileHandler);
    }
}
