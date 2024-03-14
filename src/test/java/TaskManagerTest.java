import enums.Status;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskManagerTest<T extends TaskManager> {

    protected TaskManager manager = Managers.getInMemoryTaskManager(Managers.getDefault());

    protected Task task() {
        return new Task("Description", "Title", Status.NEW, Instant.now(), 0);
    }

    protected Epic epic() {
        return new Epic("Description", "Title", Status.NEW, Instant.now(), 0);
    }

    protected Subtask createSubtask(Epic epic) {
        return new Subtask("Description", "Title", Status.NEW, epic.getId(), Instant.now(), 0);
    }

    @Test
    public void createNewTask() {
        Task task = task();
        manager.createTask(task);
        List<Task> tasks = manager.getAllTasks();
        assertNotNull(task.getStatus());
        assertEquals(Status.NEW, task.getStatus());
        assertEquals(List.of(task), tasks);
    }

    @Test
    public void createEmptyEpic() {
        Epic epic = epic();
        manager.createEpic(epic);
        List<Epic> epics = manager.getAllEpics();
        assertNotNull(epic.getStatus());
        assertEquals(Status.NEW, epic.getStatus());
        assertEquals(Collections.EMPTY_LIST, epic.getSubtaskIds());
        assertEquals(List.of(epic), epics);
    }

    @Test
    public void createSubtask() {
        Epic epic = epic();
        manager.createEpic(epic);
        Subtask subtask = createSubtask(epic);
        manager.createSubtask(subtask);
        List<Subtask> subtasks = manager.getAllSubtasks();
        assertNotNull(subtask.getStatus());
        assertEquals(epic.getId(), subtask.getEpicId());
        assertEquals(Status.NEW, subtask.getStatus());
        assertEquals(List.of(subtask), subtasks);
        assertEquals(List.of(subtask.getId()), epic.getSubtaskIds());
    }
}
