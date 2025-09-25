package ru.nsu.dunaev.console;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.dunaev.Localization;
import ru.nsu.dunaev.enums.Language;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

class ConsoleIntTest {

    static ConsoleInt<UUID> consoleInt;

    @BeforeAll
    static void setUp() {
        Localization loc = new Localization(Language.ENGLISH);
    }

    @Test
    void run() throws ExecutionException, InterruptedException {
        consoleInt.run(UUID.randomUUID());
    }

    @AfterAll
    static void shutdown() {
        consoleInt.shutdown();
    }
}
