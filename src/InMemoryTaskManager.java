import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int tasksCounter = 0;
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, SubTask> subTasks;
    private HistoryManager historyManager;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
        historyManager = Managers.getDefaultHistory();
    }

    public List<Task> getHistory() {
        return historyManager.getHistory();
    }


    private int getNewId() { // creating an id for a new task
        return ++tasksCounter;
    }

    private boolean isEpicsEmpty() {
        if (epics.isEmpty()) {
            System.out.println("Список эпиков пуст!");
        }
        return epics.isEmpty();
    }

    private boolean isTasksEmpty() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст!");
        }
        return tasks.isEmpty();
    }

    private boolean isSubTasksEmpty() {
        if (subTasks.isEmpty()) {
            System.out.println("Список подзадач пуст!");
        }
        return subTasks.isEmpty();
    }

    //GETTING
    @Override
    public List<Integer> getAllSubTasksFromEpic(int id) {
        return epics.get(id).getSubTasksID();
    }

    @Override
    public Map<Integer, Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Map<Integer, Epic> getAllEpics() {
        return epics;
    }

    @Override
    public Map<Integer, SubTask> getAllSubTasks() {
        return subTasks;
    }

    @Override
    public void printAllSubTasksFromEpic(int id) {
        if (!isEpicById(id)) return;
        int i = 0;
        for (int key : epics.get(id).getSubTasksID()) {
            System.out.print((++i) + ". " + subTasks.get(key));
        }
    }

    //PRINTING ALL
    @Override
    public void printAllTasks() {
        if (isTasksEmpty()) return;
        int i = 0;
        for (int key : tasks.keySet()) {
            System.out.println((++i) + ". " + tasks.get(key));
        }
    }

    @Override
    public void printAllEpics() {
        if (isEpicsEmpty()) return;
        int i = 0;
        for (int key : epics.keySet()) {
            System.out.println((++i) + ". " + epics.get(key));
        }
    }

    @Override
    public void printAllSubTasks() {
        if (isSubTasksEmpty()) return;
        int i = 0;
        for (int key : subTasks.keySet()) {
            System.out.print((++i) + ". " + subTasks.get(key));
        }
    }

    //DELETING ALL
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        subTasks.clear();
        epics.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        for (int key : subTasks.keySet()) {
            int epicId = subTasks.get(key).getEpicId();
            Epic epic = epics.get(epicId);
            epic.deleteSubTaskById(key);
            updateEpicStatusById(epic.getId());
        }
        subTasks.clear();
    }

    //FINDING BY ID
    private boolean isTaskById(int id) {
        return tasks.containsKey(id);
    }

    private boolean isEpicById(int id) {
        return epics.containsKey(id);
    }

    private boolean isSubTaskById(int id) {
        return subTasks.containsKey(id);
    }

    //ADDING NEW
    @Override
    public void addNewTask(Task task) {
        task.setId(getNewId());
        tasks.put(task.getId(), task);
    }
    @Override
    public void addNewEpic(Epic epic) {
        epic.setId(getNewId());
        epics.put(epic.getId(), epic);
    }

    @Override
    public void addNewSubTask(SubTask subTask) {
        if (!isEpicById(subTask.getEpicId())) return;
        subTask.setId(getNewId());
        subTasks.put(subTask.getId(), subTask);
        int epicId = subTask.getEpicId();
        Epic epic = epics.get(epicId);
        epic.addSubTask(subTask);
        updateEpicStatusById(epic.getId());
    }

    //DELETING BY ID
    @Override
    public void deleteTaskById(int id) {
        if (isTaskById(id)) {
            tasks.remove(id);
        }
    }

    @Override
    public void deleteEpicById(int id) {
        if (isEpicById(id)) {
            for (Integer key: epics.get(id).getSubTasksID()) {
                subTasks.remove(key);
            }
            epics.remove(id);
        }
    }

    @Override
    public void deleteSubTaskById(int id) {
        if (isSubTaskById(id)) {
            Epic epic = epics.get(subTasks.get(id).getEpicId());
            epic.deleteSubTaskById(id);
            updateEpicStatusById(epic.getId());
            subTasks.remove(id);
        }
    }

    //UPDATING
    @Override
    public void updateTask(Task task) {
        if (!isTaskById(task.getId())) return;
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        int epicId = epic.getId();
        if (!isEpicById(epicId)) return;
        Epic oldEpic = epics.get(epicId);
        for (int key : oldEpic.getSubTasksID()) {
            subTasks.remove(key);
        }
        oldEpic.deleteAllSubtasks();
        epics.put(epicId, epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        if (!isSubTaskById(subTask.getId())) return;
        if (!isEpicById(subTask.getEpicId())) return;
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        updateEpicStatusById(epic.getId());
    }

//    public void addToHistory(Task task) {
//        List<Task> history = historyManager.getHistory();
//        if (history.size() >= 10) {
//            history.removeFirst();
//        }
//        historyManager.add(task);
//    }

    //GETTING BY ID
    @Override
    public SubTask getSubTaskById(int id) {
        if (isSubTaskById(id)) {
            historyManager.add(subTasks.get(id));
            return subTasks.get(id);
        }
        return null;
    }

    @Override
    public Task getTaskById(int id) {
        if (isTaskById(id)) {
            historyManager.add(tasks.get(id));
            return tasks.get(id);
        }
        return null;
    }

    @Override
    public Epic getEpicById(int id) {
        if (isEpicById(id)) {
            historyManager.add(epics.get(id));
            return epics.get(id);
        }
        return null;
    }

    @Override
    public void updateEpicStatusById(int id) {
        Epic epic = epics.get(id);
        boolean allNew = true;
        boolean allDone = true;
        for (int key : epic.getSubTasksID()) {
            SubTask subTask = subTasks.get(key);
            if (subTask.getStatus() == TaskStatus.NEW) {
                allDone = false;
            } else if (subTask.getStatus() == TaskStatus.DONE) {
                allNew = false;
            } else {
                allNew = false;
                allDone = false;
            }
            if (!allDone && !allNew) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
                break;
            } else if (allDone) {
                epic.setStatus(TaskStatus.DONE);
            } else {
                epic.setStatus(TaskStatus.NEW);
            }
        }
    }
}


