package tasks;

import enums.Status;
import enums.TaskType;

import java.util.Objects;


public class Subtask extends Task {

    private final int epicId;

    public Subtask(String description, String name, Status status, int epicId) {
        super(description, name, status);
        this.epicId = epicId;
    }

    public TaskType getType() {
        return TaskType.SUBTASK;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicId == subtask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getStatus() + "," + getDescription() + "\n";
    }
}

