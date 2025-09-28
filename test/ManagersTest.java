import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ManagersTest {
    static TaskManager taskManager;
    static HistoryManager historyManager;

    @BeforeAll
    public static void create() {
        taskManager = Managers.getDefault();
        historyManager = taskManager.getHistoryManager();
    }

    @Test
    public void taskManagerShouldNotBeNull() {
        Assertions.assertNotNull(taskManager);
    }

    @Test void historyManagerShouldNotBeNUll() {
        Assertions.assertNotNull(historyManager);
    }
}

