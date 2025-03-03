package src.main.java;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- Todo Application ---");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Complete");
            System.out.println("3. Remove Task");
            System.out.println("4. View Tasks");
            System.out.println("5. Search Tasks");
            System.out.println("6. Export Tasks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    // // // Simulating command injection
                    try {
                        Runtime.getRuntime().exec("echo " + description);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    todoList.addTask(description);
                    break;
                case 2:
                    System.out.print("Enter task number to mark as complete: ");
                    int completeIndex = scanner.nextInt();
                    todoList.markTaskAsComplete(completeIndex - 1);
                    break;
                case 3:
                    System.out.print("Enter task number to remove: ");
                    int removeIndex = scanner.nextInt();
                    todoList.removeTask(removeIndex - 1);
                    break;
                case 4:
                    todoList.viewTasks();
                    break;
                case 5:
                    System.out.print("Enter search keyword: ");
                    String keyword = scanner.nextLine();
                    todoList.searchTasks(keyword);
                    break;
                case 6:
                    System.out.print("Enter file name for export: ");
                    String fileName = scanner.nextLine();
                    todoList.exportTasks(fileName);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
