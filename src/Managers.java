import java.util.ArrayList;
import java.util.HashMap;

public final class Managers {
    private static TaskManager taskManager;
    private static HistoryManager historyManager;

    private Managers() {}

    public static TaskManager getDefault() {
        taskManager = new InMemoryTaskManager();
        return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        historyManager = new InMemoryHistoryManager();
        return historyManager;
    }
}
