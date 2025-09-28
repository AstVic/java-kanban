import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryTaskManagerTest {

    InMemoryTaskManager inMemoryTaskManager;

    @BeforeEach
    public void createTasks() {
        inMemoryTaskManager = new InMemoryTaskManager();
        inMemoryTaskManager.addNewTask(new Task("T1", null, TaskStatus.DONE)); //1
        inMemoryTaskManager.addNewEpic(new Epic("E1", null)); //2
        inMemoryTaskManager.addNewSubTask(new SubTask("S1", null, TaskStatus.DONE, 2)); //3
    }

    @Test
    public void gettingTaskByIdShouldNotBeNull() {
        Assertions.assertNotNull(inMemoryTaskManager.getTaskById(1));
    }

    @Test
    public void gettingEpicByIdShouldNotBeNull() {
        Assertions.assertNotNull(inMemoryTaskManager.getEpicById(2));
    }

    @Test
    public void gettingSubTaskByIdShouldNotBeNull() {
        Assertions.assertNotNull(inMemoryTaskManager.getSubTaskById(3));
    }
}
