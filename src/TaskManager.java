import java.util.HashMap;

public class TaskManager {
    private static int tasksCounter = 0;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public static int getNewId() { // creating an id for a new task
        return ++tasksCounter;
    }

    public boolean isEpicsEmpty() {
        return epics.isEmpty();
    }

    //PRINTING ALL
    public void printAllTasks(TaskType type) {
        int i = 0;
        switch (type) {
            case TaskType.TASK:
                for (Integer key : tasks.keySet()) {
                    System.out.println((++i) + ". " + tasks.get(key));
                }
                break;
            case TaskType.EPIC:
                for (Integer key : epics.keySet()) {
                    System.out.println((++i) + ". " + epics.get(key));
                }
                break;
            case TaskType.SUBTASK:
                for (Integer key : subTasks.keySet()) {
                    System.out.println((++i) + ". " + subTasks.get(key));
                }
                break;
            default:
                break;
        }
    }

    // DELETING ALL
    public void deleteAllTasks(TaskType type) {
        switch (type) {
            case TaskType.TASK:
                tasks = new HashMap<>();
                break;
            case TaskType.EPIC:
                for (int key : epics.keySet()) {
                    Epic epic = epics.get(key);
                    for (int id: epic.getSubTasks().keySet()) {
                        subTasks.remove(id);
                    }
                    epic.deleteAllSubtasks();
                }
                epics = new HashMap<>();
                break;
            case TaskType.SUBTASK:
                for (Integer key : subTasks.keySet()) {
                    Epic epic = subTasks.get(key).getEpic();
                    epic.deleteSubTaskById(key);
                    epic.updateStatus();
                }
                subTasks = new HashMap<>();
                break;
            default:
                break;
        }
    }

    //FINDING BY ID
    public boolean findTaskById(Integer id, TaskType type) {
        switch (type) {
            case TaskType.TASK:
                if (tasks.containsKey(id)) {
                    //System.out.println(tasks.get(Id));
                    return true;
                } else {
                    System.out.println("Задачи с таким Id нет в списке!");
                    return false;
                }
            case TaskType.EPIC:
                if (epics.containsKey(id)) {
                    //System.out.println(epics.get(Id));
                    return true;
                } else {
                    System.out.println("Эпика с таким Id нет в списке!");
                    return false;
                }
            case TaskType.SUBTASK:
                if (subTasks.containsKey(id)) {
                    //System.out.println(subTasks.get(Id));
                    return true;
                } else {
                    System.out.println("Подзадачи с таким Id нет в списке!");
                    return false;
                }
            default:
                return false;
        }
    }

    public void printTaskById(Integer id, TaskType type) {
        if (findTaskById(id, type)) {
            switch (type) {
                case TaskType.TASK:
                    System.out.println(tasks.get(id));
                    break;
                case TaskType.EPIC:
                    System.out.println(epics.get(id));
                    break;
                case TaskType.SUBTASK:
                    System.out.println(subTasks.get(id));
                    break;
                default:
                    break;
            }
        }
    }

    //ADDING A NEW TASK
    public void addNewTask(String name, String description, TaskStatus status, TaskType type, Integer id) {
        switch (type) {
            case TaskType.TASK:
                Task task = new Task(name, description, status);
                tasks.put(task.getId(), task);
                break;
            case TaskType.EPIC:
                Epic newEpic = new Epic(name, description);
                epics.put(newEpic.getId(), newEpic);
                break;
            case TaskType.SUBTASK:
                Epic epic = epics.get(id);
                SubTask subTask = new SubTask(name, description, status, epic);
                subTasks.put(subTask.getId(), subTask);
                epic.updateStatus();
                break;
            default:
                break;
        }
    }

    //UPDATING
    public void updateTask(Object object, int id, TaskType type) {
        switch (type){
            case TaskType.TASK:
                tasks.put(id, (Task) object);
                break;
            case TaskType.EPIC:
                epics.put(id, (Epic) object);
                break;
            case TaskType.SUBTASK:
                subTasks.put(id, (SubTask) object);
                Epic epic = subTasks.get(id).getEpic();
                epic.updateStatus();
                break;
        }
    }

    //DELETING BY ID
    public void deleteTaskById(int id, TaskType type) {
        switch (type) {
            case TaskType.TASK:
                if (findTaskById(id, type)) {
                    tasks.remove(id);
                    System.out.println("Задача удалена.");
                }
                break;
            case TaskType.EPIC:
                if (findTaskById(id, type)) {
                    epics.get(id).deleteAllSubtasks();
                    epics.remove(id);
                    System.out.println("Эпик удалён.");
                }
                break;
            case TaskType.SUBTASK:
                if (findTaskById(id, type)) {
                    Epic epic = subTasks.get(id).getEpic();
                    epic.deleteSubTaskById(id);
                    epic.updateStatus();
                    subTasks.remove(id);
                    System.out.println("Подзадача удалена.");
                }
                break;
            default:
                break;
        }
    }

    public void printAllSubTasksFromEpic(Integer id) {
        epics.get(id).printSubTasks();
    }
}

//    public void printAllEpics() {
//        for (Integer key : epics.keySet()) {
//            System.out.println(epics.get(key));
//        }
//    }

//    public void printAllSubTasks() {
//        for (Integer key : subTasks.keySet()) {
//            System.out.println(subTasks.get(key));
//        }
//    }

    //    public void deleteAllEpics() {
//        epics = new HashMap<>();
//    }

//    public void deleteAllSubTasks() {
//        for (Integer key : subTasks.keySet()) {
//            Epic epic = subTasks.get(key).getEpic();
//            epic.deleteSubTask(key);
//            epic.updateStatus();
//        }
//        subTasks = new HashMap<>();
//    }

    //    public Epic findEpicById(Integer Id) {
//        if (epics.containsKey(Id)) {
//            return epics.get(Id);
//        } else {
//            System.out.println("Эпика с таким Id нет в списке!");
//            return null;
//        }
//    }

//    public SubTask findSubTaskById(Integer Id) {
//        if (subTasks.containsKey(Id)) {
//            return subTasks.get(Id);
//        } else {
//            System.out.println("Подзадачи с таким Id нет в списке!");
//            return null;
//        }
//    }

    //    public void addNewEpic(String name, String description, TaskStatus status) {
//        Epic epic = new Epic(name, description, status);
//        epics.put(epic.getId(), epic);
//    }

//    public void addNewSubTask(String name, String description, TaskStatus status, Epic epic) {
//        SubTask subTask = new SubTask(name, description, status, epic);
//        subTasks.put(subTask.getId(), subTask);
//        epic.addSubTask(subTask);
//        epic.updateStatus();
//    }

    //    public void updateEpic(Integer id, Epic epic) {
//        Epic epicById = findEpicById(id);
//        if (epicById != null) {
//            epicById = epic;
//        }
//    }

//    public void updateSubTask(Integer id, SubTask subTask) {
//        SubTask subTaskById = findSubTaskById(id);
//        if (subTaskById != null) {
//            subTaskById = subTask;
//            Epic epic = subTaskById.getEpic();
//            epic.updateStatus();
//        }
//    }

    //    public void deleteEpicById(Integer id) {
//        if (findEpicById(id) != null) {
//            epics.remove(id);
//        }
//    }

//    public void deleteSUbTaskById(Integer id) {
//        if (findSubTaskById(id) != null) {
//            Epic epic = subTasks.get(id).getEpic();
//            epic.deleteSubTask(id);
//            epic.updateStatus();
//            subTasks.remove(id);
//        }
//    }


