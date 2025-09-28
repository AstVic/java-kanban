import java.util.List;
import java.util.Map;

public interface TaskManager {

    //GETTING LISTS
    List<Integer> getAllSubTasksFromEpic(int id);

    Map<Integer, Task> getAllTasks();
    Map<Integer, Epic> getAllEpics();
    Map<Integer, SubTask> getAllSubTasks();

    void printAllSubTasksFromEpic(int id);

    //PRINTING ALL
    void printAllTasks();

    void printAllEpics();

    void printAllSubTasks();

    //DELETING ALL
    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();

    //ADDING NEW
    void addNewTask(Task task);

    void addNewEpic(Epic epic);

    void addNewSubTask(SubTask subTask);

    //DELETING BY ID
    void deleteTaskById(int id);

    void deleteEpicById(int id);

    void deleteSubTaskById(int id);

    //UPDATING
    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    //GETTING BY ID
    SubTask getSubTaskById(int id);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    void updateEpicStatusById(int id);

    List<Task> getHistory();
}
