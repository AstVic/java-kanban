import java.util.HashMap;

public class TaskManager {
    private int tasksCounter = 0;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public int getNewId() { // creating an id for a new task
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

    public void printAllSubTasksFromEpic(int id) {
        if (!isEpicById(id)) return;
        int i = 0;
        for (int key : epics.get(id).getSubTasksID()) {
            System.out.print((++i) + ". " + subTasks.get(key));
        }
    }

//    public void printTasksId() {
//        System.out.print("Tasks' IDs: ");
//        for (int key : tasks.keySet()) {
//            System.out.print(key + " ");
//        }
//        System.out.println();
//    }
//
//    public void printEpicsId() {
//        System.out.print("Epics' IDs: ");
//        for (int key : epics.keySet()) {
//            System.out.print(key + " ");
//        }
//        System.out.println();
//    }
//
//    public void printSubTasksId() {
//        System.out.print("SubTasks' IDs: ");
//        for (int key : subTasks.keySet()) {
//            System.out.print(key + " ");
//        }
//        System.out.println();
//    }

    //PRINTING ALL
    public void printAllTasks() {
        if (isTasksEmpty()) return;
        int i = 0;
        for (int key : tasks.keySet()) {
            System.out.println((++i) + ". " + tasks.get(key));
        }
    }

    public void printAllEpics() {
        if (isEpicsEmpty()) return;
        int i = 0;
        for (int key : epics.keySet()) {
            System.out.println((++i) + ". " + epics.get(key));
        }
    }

    public void printAllSubTasks() {
        if (isSubTasksEmpty()) return;
        int i = 0;
        for (int key : subTasks.keySet()) {
            System.out.print((++i) + ". " + subTasks.get(key));
        }
    }

    //DELETING ALL
    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        subTasks.clear();
        epics.clear();
    }

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
        if (tasks.containsKey(id)) {
            return true;
        } else {
            System.out.println("Задачи с таким id нет в списке!");
            return false;
        }
    }

    private boolean isEpicById(int id) {
        if (epics.containsKey(id)) {
            return true;
        } else {
            System.out.println("Эпика с таким id нет в списке!");
            return false;
        }
    }

    private boolean isSubTaskById(int id) {
        if (subTasks.containsKey(id)) {
            return true;
        } else {
            System.out.println("Подзадачи с таким id нет в списке!");
            return false;
        }
    }

    //ADDING NEW
    public void addNewTask(Task task) {
        task.setId(getNewId());
        tasks.put(task.getId(), task);
    }

    public void addNewEpic(Epic epic) {
        epic.setId(getNewId());
        epics.put(epic.getId(), epic);
    }

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
    public void deleteTaskById(int id) {
        if (isTaskById(id)) {
            tasks.remove(id);
        }
    }

    public void deleteEpicById(int id) {
        if (isEpicById(id)) {
            for (Integer key: epics.get(id).getSubTasksID()) {
                subTasks.remove(key);
            }
            epics.remove(id);
        }
    }

    public void deleteSubTaskById(int id) {
        if (isSubTaskById(id)) {
            Epic epic = epics.get(subTasks.get(id).getEpicId());
            epic.deleteSubTaskById(id);
            updateEpicStatusById(epic.getId());
            subTasks.remove(id);
        }
    }

    //PRINTING BY ID
//    public void printTaskById(int id) {
//        if (isTaskById(id)) {
//            System.out.println(tasks.get(id));
//        }
//    }
//
//    public void printEpicById(int id) {
//        if (isEpicById(id)) {
//            System.out.println(epics.get(id));
//        }
//    }
//
//    public void printSubTaskById(int id) {
//        if (isSubTaskById(id)) {
//            System.out.println(subTasks.get(id));
//        }
//    }

    //UPDATING
    public void updateTask(Task task) {
        if (!isTaskById(task.getId())) return;
        tasks.put(task.getId(), task);
    }

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

    public void updateSubTask(SubTask subTask) {
        if (!isSubTaskById(subTask.getId())) return;
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        updateEpicStatusById(epic.getId());
    }

    //GETTING BY ID
    public SubTask getSubTaskById(int id) {
        if (isSubTaskById(id)) {
            return subTasks.get(id);
        }
        return null;
    }

    public Task getTaskById(int id) {
        if (isTaskById(id)) {
            return tasks.get(id);
        }
        return null;
    }

    public Epic getEpicById(int id) {
        if (isEpicById(id)) {
            return epics.get(id);
        }
        return null;
    }

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


