import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        int command;

        while (true) {
            printSeparation();
            printMenu();
            printSeparation();
            System.out.print("Выберите команду 1-8: ");
            command = scanner.nextInt();
            scanner.nextLine();

            if (command >= 1 && command <= 6) {
                printSeparation();
                printTaskType();
                printSeparation();
                String typeString = scanner.nextLine();
                if (!typeString.equals("TASK") && !typeString.equals("EPIC") && !typeString.equals("SUBTASK")) {
                    System.out.println("Такого типа нет!");
                } else {
                    TaskType type = TaskType.valueOf(typeString);
                    int id;
                    switch (command) {
                        case 1:
                            taskManager.printAllTasks(type);
                            break;
                        case 2:
                            taskManager.deleteAllTasks(type);
                            break;
                        case 3:
                            System.out.print("Введите id: ");
                            id = scanner.nextInt();
                            if (taskManager.findTaskById(id, type)) {
                                taskManager.printTaskById(id, type);
                            }
                            scanner.nextLine();
                            break;
                        case 4:
                            if (type == TaskType.SUBTASK && taskManager.isEpicsEmpty()) {
                                System.out.println("Нет созданных эпиков! Сначала добавьте эпик, " +
                                        "а потом уже подзадачи!");
                                break;
                            }
                            System.out.print("Введите имя задачи: ");
                            String name = scanner.nextLine();
                            System.out.print("Введите описание задачи: ");
                            String description = scanner.nextLine();
                            TaskStatus status;
                            if (type != TaskType.EPIC) {
                                printTaskStatus();
                                String statusString = scanner.nextLine();
                                if (!statusString.equals("NEW") && !statusString.equals("IN_PROGRESS") &&
                                        !statusString.equals("DONE")) {
                                    System.out.println("Неверный статус!");
                                    break;
                                }
                                status = TaskStatus.valueOf(statusString);
                            } else {
                                status = TaskStatus.NEW;
                            }

                            if (type == TaskType.SUBTASK) {
                                System.out.println("К какому эпику относится эта подзадача?");
                                taskManager.printAllTasks(TaskType.EPIC);
                                System.out.print("Введите нужный id: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                if (!taskManager.findTaskById(id, TaskType.EPIC)) break;
                                taskManager.addNewTask(name, description, status, type, id);
                            } else {
                                taskManager.addNewTask(name, description, status, type, null);
                            }
                            break;
                        case 5:
                            taskManager.printAllTasks(type);
                            System.out.print("Введите нужный id: ");
                            id = scanner.nextInt();
                            scanner.nextLine();
                            if (!taskManager.findTaskById(id, type)) break;
                            if (type == TaskType.TASK) {
                                taskManager.updateTask(new Task(), id, TaskType.TASK);
                            } else if (type == TaskType.EPIC) {
                                taskManager.updateTask(new Epic(), id, TaskType.EPIC);
                            } else if (type == TaskType.SUBTASK) {
                                taskManager.updateTask(new SubTask(), id, TaskType.SUBTASK);
                            }
                            break;
                        case 6:
                            taskManager.printAllTasks(type);
                            System.out.print("Введите нужный id: ");
                            id = scanner.nextInt();
                            scanner.nextLine();
                            if (taskManager.findTaskById(id, type)) {
                                taskManager.deleteTaskById(id, type);
                            }
                            break;
                    }
                }
            } else if (command == 7) {
                if (taskManager.isEpicsEmpty()) {
                    System.out.println("Ни один эпик пока не создан!");
                } else {
                    taskManager.printAllTasks(TaskType.EPIC);
                    System.out.print("Введите нужный id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (taskManager.findTaskById(id, TaskType.EPIC)) {
                        taskManager.printAllSubTasksFromEpic(id);
                    }
                }
            } else if (command == 8) {
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
        System.out.println("8. Выход");
    }

    public  static void printTaskType() {
        System.out.println("Выберите тип задачи:");
        System.out.println(TaskType.TASK);
        System.out.println(TaskType.EPIC);
        System.out.println(TaskType.SUBTASK);
    }

    public  static void printTaskStatus() {
        System.out.println("Выберите статус задачи:");
        System.out.println(TaskStatus.NEW);
        System.out.println(TaskStatus.IN_PROGRESS);
        System.out.println(TaskStatus.DONE);
    }

    public static void printSeparation() {
        System.out.println("-".repeat(20));
    }
}
