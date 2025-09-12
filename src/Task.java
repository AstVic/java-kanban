public class Task {
    private final String name;
    private final String description;
    private int id;
    private TaskStatus status;

    @Override
    public String toString() {
        String result = "name='" + name + "', description=";
        if (description != null)
            result += description.length();
        else
            result += "0";
        result += ", id='" + id + "', status='" + status.toString() + "'";
        return result;
    }

    public Task(String name, String description, TaskStatus status, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
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

    public void setId(int id) {
        this.id = id;
    }

}
