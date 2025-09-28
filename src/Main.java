import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = taskManager.getHistoryManager();
        Scanner scanner = new Scanner(System.in);
        int command;

        while (true) {
            printSeparation();
            printMenu();
            printSeparation();
            System.out.print("Выберите команду 1-8: ");
            command = scanner.nextInt();
            scanner.nextLine();
            String name;
            String description;
            TaskStatus status;
            int statusInt;

            if (command >= 1 && command <= 6) {
                printSeparation();
                printTaskType();
                printSeparation();
                int type = scanner.nextInt();
                scanner.nextLine();
                if (type != 1 && type != 2 && type != 3) {
                    System.out.println("Такого типа нет!");
                } else {
                    int id;
                    switch (command) {
                        case 1:
                            if (type == 1) {
                                taskManager.printAllTasks();
                            }
                            else if (type == 2) {
                                taskManager.printAllEpics();
                            }
                            else {
                                taskManager.printAllSubTasks();
                            }
                            break;
                        case 2:
                            if (type == 1) taskManager.deleteAllTasks();
                            else if (type == 2) taskManager.deleteAllEpics();
                            else taskManager.deleteAllSubTasks();
                            break;
                        case 3:
                            if (type == 1) {
                                //taskManager.printTasksId();
                                System.out.print("Введите id: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                Task task = taskManager.getTaskById(id);
                            } else if (type == 2) {
                                //taskManager.printEpicsId();
                                System.out.print("Введите id: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                Epic epic = taskManager.getEpicById(id);
                            } else {
                                //taskManager.printSubTasksId();
                                System.out.print("Введите id: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                SubTask subTask = taskManager.getSubTaskById(id);
                            }
                            break;
                        case 4:
                            System.out.print("Введите имя: ");
                            name = scanner.nextLine();
                            System.out.print("Введите описание: ");
                            description = scanner.nextLine();
                            if (type != 2) {
                                printTaskStatus();
                                statusInt = scanner.nextInt();
                                scanner.nextLine();
                                if (statusInt != 1 && statusInt != 2 && statusInt != 3) {
                                    System.out.println("Неверный статус!");
                                    break;
                                } else if (statusInt == 1) {
                                    status = TaskStatus.NEW;
                                } else if (statusInt == 2) {
                                    status = TaskStatus.IN_PROGRESS;
                                } else {
                                    status = TaskStatus.DONE;
                                }
                            } else {
                                status = TaskStatus.NEW;
                            }

                            if (type == 1) {
                                Task task = new Task(name, description, status);
                                taskManager.addNewTask(task);
                            } else if (type == 2) {
                                Epic epic = new Epic(name, description);
                                taskManager.addNewEpic(epic);
                            } else {
                                System.out.println("К какому эпику относится эта подзадача?");
                                taskManager.printAllEpics();
                                System.out.print("Введите id нужного эпика: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                SubTask subTask = new SubTask(name, description, status, id);
                                taskManager.addNewSubTask(subTask);
                            }
                            break;
                        case 5:
                            if (type == 1) {
                                taskManager.printAllTasks();
                                System.out.print("Введите нужный id задачи, которую хотите обновить: ");
                                id = scanner.nextInt();
                            } else if (type == 2) {
                                taskManager.printAllEpics();
                                System.out.print("Введите нужный id эпика, который хотите обновить: ");
                                id = scanner.nextInt();
                            } else {
                                taskManager.printAllSubTasks();
                                System.out.print("Введите нужный id подзадачи, которую хотите обновить: ");
                                id = scanner.nextInt();
                            }

                            scanner.nextLine();
                            System.out.print("Введите новое имя: ");
                            name = scanner.nextLine();
                            System.out.print("Введите новое описание: ");
                            description = scanner.nextLine();
                            if (type != 2) {
                                printTaskStatus();
                                statusInt = scanner.nextInt();
                                scanner.nextLine();
                            } else {
                                statusInt = 1;
                            }
                            if (statusInt != 1 && statusInt != 2 && statusInt != 3) {
                                System.out.println("Неверный статус!");
                                break;
                            } else if (statusInt == 1) {
                                status = TaskStatus.NEW;
                            } else if (statusInt == 2) {
                                status = TaskStatus.IN_PROGRESS;
                            } else {
                                status = TaskStatus.DONE;
                            }

                            if (type == 1) {
                                Task task = taskManager.getTaskById(id);
                                if (task != null) {
                                    task.setName(name);
                                    task.setDescription(description);
                                    task.setStatus(status);
                                    taskManager.updateTask(task);
                                }
                            } else if (type == 2) {
                                Epic epic = taskManager.getEpicById(id);
                                if (epic != null) {
                                    epic.setName(name);
                                    epic.setDescription(description);
                                    epic.setStatus(TaskStatus.NEW);
                                    taskManager.updateEpic(epic);
                                }
                            } else {
                                SubTask subTask = taskManager.getSubTaskById(id);
                                if (subTask != null) {
                                    subTask.setName(name);
                                    subTask.setDescription(description);
                                    subTask.setStatus(status);
                                    taskManager.updateSubTask(subTask);
                                }
                            }
                            break;
                        case 6:
                            if (type == 1) {
                                taskManager.printAllTasks();
                            } else if (type == 2) {
                                taskManager.printAllEpics();
                            } else {
                                taskManager.printAllSubTasks();
                            }
                            System.out.print("Введите нужный id: ");
                            id = scanner.nextInt();
                            scanner.nextLine();
                            if (type == 1) {
                                taskManager.deleteTaskById(id);
                            } else if (type == 2) {
                                taskManager.deleteEpicById(id);
                            } else {
                                taskManager.deleteSubTaskById(id);
                            }
                            break;
                    }
                }
            } else if (command == 7) {
                taskManager.printAllEpics();
                System.out.print("Введите нужный id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                taskManager.printAllSubTasksFromEpic(id);
            } else if (command == 8) {
                int i = 0;
                List<Task> history = historyManager.getHistory();
                for (Task task: history) {
                    System.out.println((++i) + ". " + task);
                }

            } else if (command == 9) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Такой команды нет!");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Меню:");
        System.out.println("1. Вывести список задач/эпиков/подзадач");
        System.out.println("2. Удалить все задачи/эпики/подзадачи");
        System.out.println("3. Получить задачу/эпик/подзадачу по идентификатору");
        System.out.println("4. Добавить задачу/эпик/подзадачу");
        System.out.println("5. Обновить задачу/эпик/подзадачу по идентификатору");
        System.out.println("6. Удалить задачу/эпик/подзадачу по идентификатору");
        System.out.println("7. Вывести список подзадач эпика по идентификатору");
        System.out.println("8. Вывести историю");
        System.out.println("9. Выход");
    }

    public static void printTaskType() {
        System.out.println("Выберите тип задачи:");
        System.out.println("1. Task");
        System.out.println("2. Epic");
        System.out.println("3. Subtask");
    }

    public static void printTaskStatus() {
        System.out.println("Выберите статус задачи:");
        System.out.println("1. " + TaskStatus.NEW);
        System.out.println("2. " + TaskStatus.IN_PROGRESS);
        System.out.println("3. " + TaskStatus.DONE);
    }

    public static void printSeparation() {
        System.out.println("-".repeat(20));
    }
}
