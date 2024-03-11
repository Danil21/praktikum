package tasks;

import enums.Status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {

    private final List<Integer> subtaskIds = new ArrayList<>();

    private Instant endTime;

    public Epic(String description, String name, Status status) {
        super(description, name, status);
    }

    public Epic(String description, String name, Status status, Instant startTime, long duration) {
        super(description, name, status, startTime, duration);
        this.endTime = startTime;
    }


    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(int id) {
        subtaskIds.add(id);
    }


    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskIds, epic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskIds);
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getStatus() + "," + getDescription() + ",\n";
    }
}
