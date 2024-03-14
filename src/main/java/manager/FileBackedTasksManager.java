package manager;

import enums.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private File file;
    private static final String HEADER_CSV_FILE = "id,type,name,status,description,startTime,duration,epicId\n";

    public FileBackedTasksManager(HistoryManager historyManager) {
        super(historyManager);
    }

    public FileBackedTasksManager(HistoryManager historyManager, File file) {
        super(historyManager);
        this.file = file;
    }

    public void loadFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            Stream<String> lines = bufferedReader.lines().skip(1);

            lines.forEachOrdered(line -> {
                if (line.isEmpty()) {
                    return;
                }
                Task task = fromString(line);

                if (task instanceof Epic) {
                    super.createEpic((Epic) task);
                } else if (task instanceof Subtask) {
                    super.createSubtask((Subtask) task);
                } else {
                    super.createTask(task);
                }

            });

        } catch (IOException e) {
            throw new ManagerSaveException("Не удалось считать данные из файла");
        }
    }


    public void save() {
        try {
            if (Files.exists(file.toPath())) {
                Files.delete(file.toPath());
            }
            Files.createFile(file.toPath());
        } catch (IOException e) {
            throw new ManagerSaveException("Не удалось найти файл для записи данных");
        }

        try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
            fileWriter.write(HEADER_CSV_FILE);

            for (Task task : getAllTasks()) {
                fileWriter.write(toString(task) + "\n");
            }

            for (Epic epic : getAllEpics()) {
                fileWriter.write(toString(epic) + "\n");
            }

            for (Subtask subtask : getAllSubtasks()) {
                fileWriter.write(toString(subtask) + "\n");
            }

            fileWriter.write("\n");
            fileWriter.write(historyToString(getHistoryManager()));
        } catch (IOException e) {
            throw new ManagerSaveException("Не удалось сохранить данные в файл", e);
        }
    }


    @Override
    public Task createTask(Task task) {
        super.createTask(task);
        save();
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        super.createEpic(epic);
        save();
        return epic;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
        return subtask;
    }


    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void deleteSubTaskById(int id) {
        super.deleteSubTaskById(id);
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }


    @Override
    public Task getTaskById(int id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }


    // сохранения истории в CSV
    static String historyToString(HistoryManager manager) {
        List<Task> history = manager.getHistory();
        StringBuilder str = new StringBuilder();

        if (history.isEmpty()) {
            return "";
        }

        for (Task task : history) {
            str.append(task.getId()).append(",");
        }

        if (str.length() != 0) {
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }

    // Метод для восстановления менеджера истории из CSV
    static List<Integer> historyFromString(String value) {
        List<Integer> toReturn = new ArrayList<>();
        if (value != null) {
            String[] id = value.split(",");

            for (String number : id) {
                toReturn.add(Integer.parseInt(number));
            }
            return toReturn;
        }
        return toReturn;
    }


    private String getParentEpicId(Task task) {
        if (task instanceof Subtask) {
            return Integer.toString(((Subtask) task).getEpicId());
        }
        return "";
    }


    private String getType(Task task) {
        if (task instanceof Epic) {
            return Epic.class.getName();
        } else if (task instanceof Subtask) {
            return Subtask.class.getName();
        }
        return Task.class.getName();
    }

    private String toString(Task task) {
        String[] toJoin = {Integer.toString(task.getId()), getType(task).replace("tasks.", ""), task.getName(),
                task.getStatus().toString(), task.getDescription(), String.valueOf(task.getStartTime()),
                String.valueOf(task.getDuration()), getParentEpicId(task)};
        return String.join(",", toJoin);
    }

    private Task fromString(String value) {
        String[] params = value.split(",");
        int id = Integer.parseInt(params[0]);
        String type = params[1];
        String name = params[2];
        Status status = Status.valueOf(params[3].toUpperCase());
        String description = params[4];
        Instant startTime = Instant.parse(params[5]);
        long duration = Long.parseLong(params[6]);
        Integer epicId = type.equals("Subtask") ? Integer.parseInt(params[7]) : null;

        if (type.equals("Epic")) {
            Epic epic = new Epic(description, name, status, startTime, duration);
            epic.setId(id);
            epic.setStatus(status);
            return epic;
        } else if (type.equals("Subtask")) {
            Subtask subtask = new Subtask(description, name, status, epicId, startTime, duration);
            subtask.setId(id);
            return subtask;
        } else {
            Task task = new Task(description, name, status, startTime, duration);
            task.setId(id);
            return task;
        }
    }


}
