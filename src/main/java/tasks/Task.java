package tasks;

import enums.Status;

import java.time.Instant;
import java.util.Objects;

public class Task {

    private String name;
    private String description;
    private Integer id;
    private Status status;
    private Instant startTime;
    private long duration;

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String description, String name, Status status, Instant startTime, long duration) {
        this.description = description;
        this.name = name;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(description, task.description) && Objects.equals(name, task.name)
                && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, id, name, status);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + status + "," + description + ",\n";
    }

}
