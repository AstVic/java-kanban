import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskTest {
    static TaskManager taskManager;

    @BeforeAll
    public static void createTasks() {
        taskManager = Managers.getDefault();
        taskManager.addNewTask(new Task("T1", null, TaskStatus.DONE)); //1
        taskManager.addNewEpic(new Epic("E1", null)); //2
        taskManager.addNewSubTask(new SubTask("S1", null, TaskStatus.DONE, 2)); //3
        taskManager.addNewTask(new Task("T2", null, TaskStatus.DONE)); //4
        taskManager.addNewSubTask(new SubTask("S2", null, TaskStatus.DONE, 2)); //5
        taskManager.addNewEpic(new Epic("E2", null)); //6
    }

    @Test
    public void tasksShouldBeTheSameWhenTheIDsAreTheSame() {
        Task task = taskManager.getTaskById(1);
        Assertions.assertEquals(task, taskManager.getTaskById(1));
    }

    @Test
    public void tasksShouldNotBeTheSameWhenTheIDsAreDifferent() {
        Task task = taskManager.getTaskById(1);
        Assertions.assertNotEquals(task, taskManager.getTaskById(4));
    }

    @Test
    public void epicsShouldBeTheSameWhenTheIDsAreTheSame() {
        Epic epic = taskManager.getEpicById(2);
        Assertions.assertEquals(epic, taskManager.getEpicById(2));
    }

    @Test
    public void epicsShouldNotBeTheSameWhenTheIDsAreDifferent() {
        Epic epic = taskManager.getEpicById(2);
        Assertions.assertNotEquals(epic, taskManager.getEpicById(6));
    }

    @Test
    public void subTasksShouldBeTheSameWhenTheIDsAreTheSame() {
        SubTask subTask = taskManager.getSubTaskById(3);
        Assertions.assertEquals(subTask, taskManager.getSubTaskById(3));
    }

    @Test
    public void subTasksShouldNotBeTheSameWhenTheIDsAreDifferent() {
        SubTask subTask = taskManager.getSubTaskById(3);
        Assertions.assertNotEquals(subTask, taskManager.getSubTaskById(5));
    }

    @Test
    public void epicAddedIntoItselfShouldBeFailed() {
        Epic epic = taskManager.getEpicById(2);
        epic.addSubTask(epic);
        Assertions.assertFalse(epic.getSubTasksID().contains(2));
    }

    @Test
    public void subTaskCannotBeEpicOfItself() {
        SubTask subTask = taskManager.getSubTaskById(5);
        taskManager.updateSubTask(new SubTask("S3", null, TaskStatus.DONE, 5));
        Assertions.assertNotEquals(5, subTask.getEpicId());
    }


}
