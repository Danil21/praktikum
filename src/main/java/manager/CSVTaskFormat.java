package manager;

import enums.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CSVTaskFormat {
    public static String getHeader() {
        return "id,type,name,status,description,epicId";
    }


    // сохранения истории в CSV
    public static String historyToString(HistoryManager historyManager) {
        final List<Task> history = historyManager.getHistory();
        if (history.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(history.get(0).getId());
        for (int i = 1; i < history.size(); i++) {
            Task task = history.get(i);
            sb.append(",");
            sb.append(task.getId());
        }
        return sb.toString();
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

    private static String getType(Task task) {
        if (task instanceof Epic) {
            return Epic.class.getName();
        } else if (task instanceof Subtask) {
            return Subtask.class.getName();
        }
        return Task.class.getName();
    }

    public static String toString(Task task) {
        String[] toJoin = {Integer.toString(task.getId()), getType(task).replace("tasks.", ""), task.getName(),
                task.getStatus().toString(), task.getDescription(), getParentEpicId(task)};
        return String.join(",", toJoin);
    }

    private static String getParentEpicId(Task task) {
        if (task instanceof Subtask) {
            return Integer.toString(((Subtask) task).getEpicId());
        }
        return "";
    }

    protected static Task fromString(String value) {
        String[] params = value.split(",");
        int id = Integer.parseInt(params[0]);
        String type = params[1];
        String name = params[2];
        Status status = Status.valueOf(params[3].toUpperCase());
        String description = params[4];
        Integer epicId = type.equals("SUBTASK") ? Integer.parseInt(params[5]) : null;

        if (type.equals("EPIC")) {
            Epic epic = new Epic(description, name, status);
            epic.setId(id);
            epic.setStatus(status);
            return epic;
        } else if (type.equals("SUBTASK")) {
            Subtask subtask = new Subtask(description, name, status, epicId);
            subtask.setId(id);
            return subtask;
        } else {
            Task task = new Task(description, name, status);
            task.setId(id);
            return task;
        }
    }
}
