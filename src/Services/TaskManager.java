package Services;

import Model.Epic;
import Model.SubTask;
import Model.Task;
import enums.Status;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    int newId();
    void addTask(Task task);
    void addEpic(Epic epic);
    void addSubtask(SubTask subTask);
    ArrayList<Task> getAllTasks();
    ArrayList<Epic> getAllEpics();
    ArrayList<SubTask> getAllSubTasks();
    Task getTaskById(int id);
    Epic getEpicById (int id);
    SubTask getSubTaskById(int id);
    void deleteTaskById(int id);
    void deleteEpicById(int id);
    void deleteSubTaskById(int id);
    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubTasks();
    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubTask(SubTask subTask);
    ArrayList<SubTask> getSubTasksByEpic(int id);
    Status epicStatus(Epic epic);
    List<Task> getHistory();
}
