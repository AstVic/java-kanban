public class Task {
    protected String name;
    protected String description;
    protected final int id;
    protected TaskStatus status;
    protected TaskType type;

    @Override
    public String toString() {
        String result = "name=" + name + ", description=";
        if (description != null)
            result += description.length();
        else
            result += "0";
        result += ", id=" + id + ", status=" + status.toString() + ", type=" + type.toString();
        return result;
    }

    public Task() {
        name = "name";
        description = "description";
        id = TaskManager.getNewId();
        status = TaskStatus.NEW;
        type = TaskType.TASK;
    }

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.id = TaskManager.getNewId();
        this.status = status;
        type = TaskType.TASK;
    }

    public TaskType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

}
