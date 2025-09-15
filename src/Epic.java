import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTasksID = new ArrayList<>();

    @Override
    public String toString() {
        return (super.toString() + ", " + "\n subTasks=" + subTasksID.toString());
    }

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
    }

    public ArrayList<Integer> getSubTasksID() {
        return subTasksID;
    }

    public void addSubTask(SubTask subTask) {
        subTasksID.add(subTask.getId());
        System.out.println("Задача добавлена в эпик.");
    }

    public void deleteSubTaskById(int id) {
        subTasksID.remove((Integer) id);
        System.out.println("Подзадача удалена из эпика.");
    }

    public void deleteAllSubtasks() {
        subTasksID.clear();
        System.out.println("Все задачи удалены из эпика.");
    }
}