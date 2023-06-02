package Services;

import Model.Epic;
import enums.Status;
import Model.Task;
import Model.SubTask;
import history.HistoryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private final HashMap<Integer, Task> allTasks = new HashMap<>();
    private final HashMap<Integer, Epic> allEpics = new HashMap<>();
    private final HashMap<Integer, SubTask> allSubTasks = new HashMap<>();
    private final HistoryManager historyManager;

    private int uniqueId = 0;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public int newId() {
        uniqueId++;
        return uniqueId;
    }
    @Override
    public void addTask(Task task) {
        allTasks.put(task.getId(), task);
    }
    @Override
    public void addEpic(Epic epic) {
        epic.setStatus(epicStatus(epic));
        allEpics.put(epic.getId(), epic);

    }
    @Override
    public void addSubtask(SubTask subTask) {
        allSubTasks.put(subTask.getId(), subTask);
        allEpics.get(subTask.getEpicId()).addSubTaskIds(subTask.getId());
        allEpics.get(subTask.getEpicId()).setStatus(epicStatus(allEpics.get(subTask.getEpicId())));
    }
    @Override
    public ArrayList<Task> getAllTasks() {
            return new ArrayList<>(allTasks.values());
    }
    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(allEpics.values());
    }
    @Override
    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(allSubTasks.values());
    }
    @Override
    public Task getTaskById(int id) {
        historyManager.addTaskInHistory(allTasks.get(id));
        return allTasks.get(id);
    }
    @Override
    public Epic getEpicById (int id) {
        historyManager.addTaskInHistory(allEpics.get(id));
        return allEpics.get(id);
    }
    @Override
    public SubTask getSubTaskById(int id) {
        historyManager.addTaskInHistory(allSubTasks.get(id));
        return allSubTasks.get(id);
    }
    @Override
    public void deleteTaskById(int id) {
            allTasks.remove(id);
    }
    @Override
    public void deleteEpicById(int id) {
            for (int key : allEpics.get(id).getSubTaskIds()) {
                allSubTasks.remove(key);
            }
            allEpics.remove(id);
    }
    @Override
    public void deleteSubTaskById(int id) {
            SubTask subTask = allSubTasks.get(id);
            Epic epic = allEpics.get(subTask.getEpicId());
            ArrayList<Integer> subtaskIds = epic.getSubTaskIds();
            subtaskIds.remove(subTask.getId());
            epic.setSubTaskIds(subtaskIds);
            allSubTasks.remove(id);
            epic.setStatus(epicStatus(epic));
    }
    @Override
    public void deleteAllTasks() {
        allTasks.clear();
    }
    @Override
    public void deleteAllEpics() {
        allSubTasks.clear();
        allEpics.clear();
    }
    @Override
    public void deleteAllSubTasks() {
        allSubTasks.clear();
        for (int key : allEpics.keySet()){
            allEpics.get(key).getSubTaskIds().clear();
            allEpics.get(key).setStatus(Status.NEW);
        }
    }
    @Override
    public void updateTask(Task task) {
            allTasks.put(task.getId(), task);
    }
    @Override
    public void updateEpic(Epic epic) {
            epic.setSubTaskIds(allEpics.get(epic.getId()).getSubTaskIds());
            epic.setStatus(epicStatus(epic));
            allEpics.put(epic.getId(), epic);
    }
    @Override
    public void updateSubTask(SubTask subTask) {
            allSubTasks.put(subTask.getId(), subTask);
            allEpics.get(subTask.getEpicId()).setStatus(epicStatus(allEpics.get(subTask.getEpicId())));
    }
    @Override
    public ArrayList<SubTask> getSubTasksByEpic(int id) {
        ArrayList<SubTask> epicsSubTasks = new ArrayList<>();
        for (int key : allEpics.get(id).getSubTaskIds()){
            epicsSubTasks.add(allSubTasks.get(key));
        }
        return epicsSubTasks;
    }
    @Override
    public Status epicStatus(Epic epic) {
        if (epic.getSubTaskIds().isEmpty()) {
            return Status.NEW;
        }
        int epicIsNew = 0;
        int epicIsDone = 0;
        for (int key : epic.getSubTaskIds()) {
            if (allSubTasks.get(key).getStatus() == Status.NEW) {
                epicIsNew++;
            } else if (allSubTasks.get(key).getStatus() == Status.DONE) {
                epicIsDone++;
            }
        }
        if (epicIsNew == epic.getSubTaskIds().size()) {
            return Status.NEW;
        } else if (epicIsDone == epic.getSubTaskIds().size()) {
            return Status.DONE;
        } else {
            return Status.IN_PROGRESS;
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}