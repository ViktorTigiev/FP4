import Model.Epic;
import Services.Managers;
import Services.TaskManager;
import enums.Status;
import Model.SubTask;
import Model.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault(Managers.getDefaultHistory());

        // Инициализируем задачи типа Task
        Task task1 = new Task(manager.newId(), "Task1", "Description1",Status.NEW);
        Task task2 = new Task(manager.newId(), "Task2","Description2",Status.NEW );
        Task changedTask = new Task(1,"changedTask", "changedDescription", Status.IN_PROGRESS);
        // Инициализируем задачи типа Epic
        Epic epic1 = new Epic(manager.newId(), "epic1","epicDescription1");
        Epic epic2 = new Epic(manager.newId(), "epic2","epicDescription2");
        Epic changedEpic = new Epic(4, "changedEpic","changedDescription");
        // Инициализируем задачи типа SubTask
        SubTask subTask1 = new SubTask(manager.newId(), "sub1-1", "des1-1",Status.NEW, 3);
        SubTask subTask2 = new SubTask(manager.newId(), "sub1-2", "des1-2",Status.DONE, 3);
        SubTask subTask3 = new SubTask(manager.newId(), "sub2-1", "des2-1",Status.DONE, 4);
        SubTask subTask4 = new SubTask(6,"sub1-1-1", "des1-1-1", Status.NEW, 3);
        // Создаем задачи типа Task
        manager.addTask(task1);
        manager.addTask(task2);
        // Создаем задачи типа Epic
        manager.addEpic(epic1);
        manager.addEpic(epic2);
        // Создаем задачи типа SubTask
        manager.addSubtask(subTask1);
        manager.addSubtask(subTask2);
        manager.addSubtask(subTask3);
        // Поиск задач по номеру
        manager.getTaskById(1);
        manager.getTaskById(2);
        manager.getEpicById(4);
        manager.getEpicById(3);
        manager.getSubTaskById(7);
        manager.getSubTaskById(6);
        manager.getSubTaskById(5);
        manager.getSubTaskById(6);
        manager.getSubTaskById(7);
        manager.getSubTaskById(7);
        manager.getSubTaskById(5);
        System.out.println("История запросов");
        System.out.println(manager.getHistory());
        System.out.println("Вывод всех задач");
        System.out.println(manager.getAllTasks());
        System.out.println("Вывод всех Epic");
        System.out.println(manager.getAllEpics());
        System.out.println("Вывод всех SubTask");
        System.out.println(manager.getAllSubTasks());
        System.out.println("Вывод обновленных задач");
        //Обновление Task
        manager.updateTask(changedTask);
        //Обновление Epic
        manager.updateEpic(changedEpic);
        //Обновление подзадачи
        manager.updateSubTask(subTask4);
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTasks());
        // Удаление задачи по номеру
        manager.deleteTaskById(2);
        // Удаление Epic по номеру
        manager.deleteEpicById(3);
        // Удаление SubTask по номеру
        //manager.deleteSubTaskById(5);
        System.out.println("Вывод задач после удаления");
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTasks());
        //manager.deleteAllTasks();
        //manager.deleteAllEpics();
        //manager.deleteAllSubTasks();
    }
}