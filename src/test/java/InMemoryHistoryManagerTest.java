import enums.Status;
import manager.HistoryManager;
import manager.InMemoryHistoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryHistoryManagerTest {


//    public void printTasks() {
//        if (tasks.size() == 0) {
//            System.out.println("Task list is empty");
//            return;
//        }
//
//        for (Task task : tasks.values()) {
//            System.out.println("Task{" +
//                    "description='" + task.getDescription() + '\'' +
//                    ", id=" + task.getId() +
//                    ", name='" + task.getName() + '\'' +
//                    ", status=" + task.getStatus() +
//                    '}');
//        }
//    }
//
//    public void printEpics() {
//        if (epics.size() == 0) {
//            System.out.println("Epic list is empty");
//            return;
//        }
//        for (Epic epic : epics.values()) {
//            System.out.println("Epic{" +
//                    "subtasksIds=" + epic.getSubtaskIds() +
//                    ", description='" + epic.getDescription() + '\'' +
//                    ", id=" + epic.getId() +
//                    ", name='" + epic.getName() + '\'' +
//                    ", status=" + epic.getStatus() +
//                    '}');
//        }
//    }

    HistoryManager manager;
    private int id = 0;

    public int generateId() {
        return ++id;
    }

   // protected Task createTask() {
//        return new Task("Description", "Title", Status.NEW, Instant.now(), 0);
//    }
    protected Task createTask() {
        return new Task("Description", "Title", Status.NEW);
    }

    @BeforeEach
    public void beforeEach() {
        manager = new InMemoryHistoryManager();
    }

    @Test
    public void shouldAddTasksToHistory() {
        Task task1 = createTask();
        int newTaskId1 = generateId();
        task1.setId(newTaskId1);
        Task task2 = createTask();
        int newTaskId2 = generateId();
        task2.setId(newTaskId2);
        Task task3 = createTask();
        int newTaskId3 = generateId();
        task3.setId(newTaskId3);
        manager.add(task1);
        manager.add(task2);
        manager.add(task3);
        assertEquals(List.of(task1, task2, task3), manager.getHistory());
    }

    @Test
    public void shouldRemoveTask() {
        Task task1 = createTask();
        int newTaskId1 = generateId();
        task1.setId(newTaskId1);
        Task task2 = createTask();
        int newTaskId2 = generateId();
        task2.setId(newTaskId2);
        Task task3 = createTask();
        int newTaskId3 = generateId();
        task3.setId(newTaskId3);
        manager.add(task1);
        manager.add(task2);
        manager.add(task3);
        manager.remove(task2.getId());
        assertEquals(List.of(task1, task3), manager.getHistory());
    }

    @Test
    public void shouldRemoveOnlyOneTask() {
        Task task = createTask();
        int newTaskId = generateId();
        task.setId(newTaskId);
        manager.add(task);
        manager.remove(task.getId());
        assertEquals(Collections.EMPTY_LIST, manager.getHistory());
    }

    @Test
    public void shouldHistoryIsEmpty() {
        Task task1 = createTask();
        int newTaskId1 = generateId();
        task1.setId(newTaskId1);
        Task task2 = createTask();
        int newTaskId2 = generateId();
        task2.setId(newTaskId2);
        Task task3 = createTask();
        int newTaskId3 = generateId();
        task3.setId(newTaskId3);
        manager.remove(task1.getId());
        manager.remove(task2.getId());
        manager.remove(task3.getId());
        assertEquals(Collections.EMPTY_LIST, manager.getHistory());
    }

    @Test
    public void shouldNotRemoveTaskWithBadId() {
        Task task = createTask();
        int newTaskId = generateId();
        task.setId(newTaskId);
        manager.add(task);
        manager.remove(0);
        assertEquals(List.of(task), manager.getHistory());
    }

}
