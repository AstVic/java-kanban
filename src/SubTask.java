public class SubTask extends Task {
    private final int epicId;

    @Override
    public String toString() {
        return (super.toString() + ", epicId='" + epicId + "'\n");
    }

    public SubTask(String name, String description, TaskStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
