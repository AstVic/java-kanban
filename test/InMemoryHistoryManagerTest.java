import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InMemoryHistoryManagerTest {
    InMemoryTaskManager inMemoryTaskManager;
    InMemoryHistoryManager inMemoryHistoryManager;

    @BeforeEach
    public void createTasks() {
        inMemoryTaskManager = new InMemoryTaskManager();
        inMemoryHistoryManager = new InMemoryHistoryManager();
        inMemoryTaskManager.addNewTask(new Task("T1", null, TaskStatus.DONE)); //1
        inMemoryTaskManager.addNewEpic(new Epic("E1", null)); //2
        inMemoryTaskManager.addNewSubTask(new SubTask("S1", null, TaskStatus.DONE, 2)); //3
    }

    @Test
    public void taskShouldBeChangedAfterBeingAddedToManager() {
        Task task = inMemoryTaskManager.getTaskById(1);
        inMemoryHistoryManager.add(task);
        List<Task> history = inMemoryHistoryManager.getHistory();
        Assertions.assertEquals(history.getFirst(), task);
    }

    @Test
    public void epicsShouldBeChangedAfterBeingAddedToManager() {
        Epic epic = inMemoryTaskManager.getEpicById(2);
        inMemoryHistoryManager.add(epic);
        List<Task> history = inMemoryHistoryManager.getHistory();
        Assertions.assertEquals(history.getFirst(), epic);
    }

    @Test
    public void subTaskShouldBeChangedAfterBeingAddedToManager() {
        SubTask subTask = inMemoryTaskManager.getSubTaskById(3);
        inMemoryHistoryManager.add(subTask);
        List<Task> history = inMemoryHistoryManager.getHistory();
        Assertions.assertEquals(history.getFirst(), subTask);
    }
}