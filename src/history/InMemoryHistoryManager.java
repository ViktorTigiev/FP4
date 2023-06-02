package history;

import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    public  static List<Task> history = new ArrayList<>(10);

    @Override
    public void addTaskInHistory(Task viewedTask) {
        if (history.size() == 10){
            history.remove(0);
        }
        history.add(viewedTask);

    }
    @Override
    public List<Task> getHistory() {
        return history;
    }
}

