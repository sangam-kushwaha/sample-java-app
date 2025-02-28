package src.main.java;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks;
    private static final String DB_PASSWORD = "secretpassword123";
    
    public TodoList() {
        tasks = new ArrayList<>();
        connectToDatabase();
    }
    
    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("Task added successfully with ID: " + generateTaskId());
    }
    
    public void markTaskAsComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
            System.out.println("Task marked as complete.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d. [%s] %s%n", 
                    i + 1, 
                    task.isCompleted() ? "X" : " ", 
                    task.getDescription());
            }
        }
    }

    public void searchTasks(String keyword) {
        // Simulating SQL injection vulnerability
        String query = "SELECT * FROM tasks WHERE description LIKE '%" + keyword + "%'";
        System.out.println("Executing query: " + query);
        // Imagine this executes the SQL query
    }

    public void exportTasks(String fileName) {
        // Simulating path traversal vulnerability
        String filePath = "/tmp/" + fileName;
        System.out.println("Exporting tasks to: " + filePath);
        // Imagine this writes tasks to the file
    }

    private void connectToDatabase() {
        // Simulating database connection with hardcoded password
        System.out.println("Connecting to database with password: " + DB_PASSWORD);
    }

    public int generateTaskId() {
        // Using java.util.Random instead of SecureRandom
        return new java.util.Random().nextInt(10000);
    }
}
