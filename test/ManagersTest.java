import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagersTest {
    TaskManager taskManager;
    HistoryManager historyManager;

    @BeforeEach
    public void create() {
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    public void taskManagerShouldNotBeNull() {
        Assertions.assertNotNull(taskManager);
    }

    @Test void historyManagerShouldNotBeNUll() {
        Assertions.assertNotNull(historyManager);
    }
}

