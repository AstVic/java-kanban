import javax.crypto.spec.PSource;
import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    @Override
    public String toString() {
        return (super.toString() + ", " + "\n subTasks=" + subTasks.toString());
    }

    public Epic() {
        super();
        type = TaskType.EPIC;
    }

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
        type = TaskType.EPIC;
    }

    public void printSubTasks() {
        int i = 1;
        for (Integer key : subTasks.keySet()) {
            System.out.print((i++) + ". " + subTasks.get(key));
        }
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        System.out.println("Задача добавлена в эпик.");
    }

    public void deleteSubTaskById(int id) {
        subTasks.remove(id);
        System.out.println("Подзадача удалена из эпика.");
    }

    public void deleteAllSubtasks() {
        subTasks = new HashMap<>();
        System.out.println("Все задачи удалены из эпика.");
    }

    public void updateStatus() {
        boolean allNew = true;
        boolean allDone = true;
        for (Integer key : subTasks.keySet()) {
            SubTask subTask = subTasks.get(key);
            if (subTask.getStatus() == TaskStatus.NEW) {
                allDone = false;
            } else if (subTask.getStatus() == TaskStatus.DONE) {
                allNew = false;
            }
            if (!allDone && !allNew) {
                super.setStatus(TaskStatus.IN_PROGRESS);
                break;
            } else if (allDone) {
                super.setStatus(TaskStatus.DONE);
            } else {
                super.setStatus(TaskStatus.NEW);
            }
        }
    }
}
