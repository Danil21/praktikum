import enums.Status;
import manager.FileBackedTaskManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static enums.Status.NEW;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBackedTaskManagerTest<T extends TaskManager> {

    protected FileBackedTaskManager manager;

    public static final Path path = Path.of("src/resources/test.csv");
    File file = new File(String.valueOf(path));

    @BeforeEach
    public void beforeEach() {
        manager = new FileBackedTaskManager(file);
    }

    @AfterEach
    public void afterEach() {
//        try {
//            Files.delete(path);
//        } catch (IOException exception) {
//            System.out.println(exception.getMessage());
//        }
    }

    @Test
    public void correctlySaveAndLoad() {
        Task task = new Task("Description", "Title", Status.NEW);
        FileBackedTaskManager  fileManager = FileBackedTaskManager.loadFromFile(file);
        Epic epic = new Epic("Description", "Title", Status.NEW);

        manager.createTask(task);
        manager.createEpic(epic);

        assertEquals(List.of(task), manager.getAllTasks());
        assertEquals(List.of(epic), manager.getAllEpics());

    }

    @Test
    public void saveAndLoadEmptyTasksEpicsSubtasks() {
        FileBackedTaskManager fileManager = new FileBackedTaskManager(file);
        //fileManager.save();
        fileManager.loadFromFile(file);
        assertEquals(Collections.EMPTY_LIST, manager.getAllTasks());
        assertEquals(Collections.EMPTY_LIST, manager.getAllEpics());
        assertEquals(Collections.EMPTY_LIST, manager.getAllSubtasks());
    }

    @Test
    public void saveAndLoadEmptyHistory() {
        FileBackedTaskManager fileManager = new FileBackedTaskManager(file);
        //        fileManager.save();
        fileManager.loadFromFile(file);
        assertEquals(Collections.EMPTY_LIST, manager.getHistory());
    }
}
