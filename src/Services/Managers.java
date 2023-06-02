package Services;

import history.HistoryManager;
import history.InMemoryHistoryManager;

public class Managers {
    public static TaskManager getDefault(HistoryManager historyManager) {
        return new InMemoryTaskManager(historyManager);
    }
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
