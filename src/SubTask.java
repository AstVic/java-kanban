public class SubTask extends Task {
    private final int epicId;

    public SubTask(String name, String description, TaskStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return (super.toString() + ", epicId='" + epicId + "'\n");
    }

    public int getEpicId() {
        return epicId;
    }
}
