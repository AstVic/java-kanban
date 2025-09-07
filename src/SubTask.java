public class SubTask extends Task {
    private Epic epic;

    @Override
    public String toString() {
        return (super.toString() + ", epicId=" + epic.getId() + "\n");
    }

    public SubTask() {
        super();
        epic.addSubTask(this);
        type = TaskType.SUBTASK;
    }

    public SubTask(String name, String description, TaskStatus status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
        epic.addSubTask(this);
        type = TaskType.SUBTASK;
    }

    public Epic getEpic() {
        return epic;
    }

}
