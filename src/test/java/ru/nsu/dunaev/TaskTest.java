package ru.nsu.dunaev;

import org.junit.jupiter.api.*;
import ru.nsu.dunaev.enums.Priority;
import ru.nsu.dunaev.enums.Status;
import ru.nsu.dunaev.taskManagment.Task;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


class TaskTest {

    Task<UUID> taskUUID;
    Task<Integer> taskInteger;
    Task<String> taskString;


    @BeforeEach
    public void initialize() {
        taskUUID = new Task<>(
                UUID.randomUUID(),
                "Test UUID task",
                "Some test description",
                Priority.LOW,
                Status.NEW);
        taskString = new Task<>(
                "first",
                "Test String task",
                "Some test description",
                Priority.LOW,
                Status.NEW);
        taskInteger = new Task<>(
                1,
                "Test String task",
                "Some test description",
                Priority.LOW,
                Status.NEW);
    }

    @Test
    void testToString() {

    }

    @Test
    void getUUIDId() throws NoSuchFieldException, IllegalAccessException {
        Field field = this.taskUUID.getClass().getDeclaredField("id");
        field.setAccessible(true);
        var tid = field.get(this.taskUUID);
        assert(tid instanceof UUID);
        UUID id = (UUID) tid;
        assert(id.equals(this.taskUUID.getId()));
    }

    @Test
    void getStringId() throws NoSuchFieldException, IllegalAccessException {
        Field field = this.taskString.getClass().getDeclaredField("id");
        field.setAccessible(true);
        var tid = field.get(this.taskString);
        assert(tid instanceof String);
        String id = (String) tid;
        assert(id.equals(this.taskString.getId()));
    }

    @Test
    void getIntegerId() throws NoSuchFieldException, IllegalAccessException {
        Field field = this.taskInteger.getClass().getDeclaredField("id");
        field.setAccessible(true);
        var tid = field.get(this.taskInteger);
        assert(tid instanceof Integer);
        Integer id = (Integer) tid;
        assert(id.equals(this.taskInteger.getId()));
    }

    @Test
    void getTitle() {
        assert(!this.taskUUID.getTitle().isEmpty());
        assert(!this.taskString.getTitle().isEmpty());
        assert(!this.taskInteger.getTitle().isEmpty());
    }

    @Test
    void getStatus() {
    }

    @Test
    void setStatus() {
    }

    @Test
    void getPriority() {
    }

    @Test
    void markDone() {
    }

    @Test
    void markInProgress() {
    }

    @Test
    void parallelAccess() throws InterruptedException {
        AtomicInteger thread = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Title
        List<Callable<Void>> title = new ArrayList<>();
        title.add(() -> { taskUUID.setTitle("First"); thread.set(1); return null; });
        title.add(() -> { taskUUID.setTitle("Second"); thread.set(2); return null; });
        title.add(() -> { taskUUID.setTitle("Third"); thread.set(3); return null; });
        executorService.invokeAll(title);

        assert thread.get() != 1 || (this.taskUUID.getTitle().equals("First"));
        assert thread.get() != 2 || (this.taskUUID.getTitle().equals("Second"));
        assert thread.get() != 3 || (this.taskUUID.getTitle().equals("Third"));

        thread.set(0);

        // Description
        List<Callable<Void>> description = new ArrayList<>();
        description.add(() -> { taskUUID.setDescription("First"); thread.set(1); return null; });
        description.add(() -> { taskUUID.setDescription("Second"); thread.set(2); return null; });
        description.add(() -> { taskUUID.setDescription("Third"); thread.set(3); return null; });
        executorService.invokeAll(description);

        assert thread.get() != 1 || (this.taskUUID.getDescription().equals("First"));
        assert thread.get() != 2 || (this.taskUUID.getDescription().equals("Second"));
        assert thread.get() != 3 || (this.taskUUID.getDescription().equals("Third"));

        thread.set(0);

        // Priority
        List<Callable<Void>> priority = new ArrayList<>();
        priority.add(() -> { taskUUID.setPriority(Priority.HIGH); thread.set(1); return null; });
        priority.add(() -> { taskUUID.setPriority(Priority.MEDIUM); thread.set(2); return null; });
        executorService.invokeAll(priority);

        assert thread.get() != 1 || (this.taskUUID.getPriority().equals(Priority.HIGH));
        assert thread.get() != 2 || (this.taskUUID.getPriority().equals(Priority.MEDIUM));

        thread.set(0);

        // Status
        List<Callable<Void>> status = new ArrayList<>();
        status.add(() -> { taskUUID.setStatus(Status.DONE); thread.set(1); return null; });
        status.add(() -> { taskUUID.setStatus(Status.IN_PROGRESS); thread.set(2); return null; });
        executorService.invokeAll(status);

        assert thread.get() != 1 || (this.taskUUID.getStatus().equals(Status.DONE));
        assert thread.get() != 2 || (this.taskUUID.getStatus().equals(Status.IN_PROGRESS));
        thread.set(0);

        executorService.shutdown();
    }
}
