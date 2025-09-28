import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTasksID = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
    }

    @Override
    public String toString() {
        return (super.toString() + ", " + "\n subTasks=" + subTasksID.toString());
    }

    public List<Integer> getSubTasksID() {
        return subTasksID;
    }

    public void addSubTask(Task subTask) {
        if (subTask.getId() != this.getId()) {
            subTasksID.add(subTask.getId());
        }
        //System.out.println("Задача добавлена в эпик.");
    }

    public void deleteSubTaskById(int id) {
        subTasksID.remove((Integer) id);
        //System.out.println("Подзадача удалена из эпика.");
    }

    public void deleteAllSubtasks() {
        subTasksID.clear();
        //System.out.println("Все задачи удалены из эпика.");
    }
}