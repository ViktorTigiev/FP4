package history;

import Model.Task;

import java.util.List;

public interface HistoryManager {
    void addTaskInHistory(Task viewedTask);
    List<Task> getHistory();
}
