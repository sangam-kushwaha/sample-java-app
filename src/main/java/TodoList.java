package src.main.java;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TodoList {
    private List<Task> tasks;
    private static final String DB_URL = "jdbc:mysql://localhost/todolist";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "password123";  // Hardcoded credentials (vulnerability)
    
    public TodoList() {
        tasks = new ArrayList<>();
    }
    
    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("Task added successfully.");
        logTask(description);  // Potential for information leakage
    }
    
    private void logTask(String description) {
        try {
            FileWriter writer = new FileWriter("tasks.log", true);  // Potential for path traversal
            writer.write(description + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void markTaskAsComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
            System.out.println("Task marked as complete.");
            updateDatabase(task);  // SQL Injection vulnerability
        } else {
            System.out.println("Invalid task number.");
        }
    }
    
    private void updateDatabase(Task task) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE tasks SET completed = true WHERE description = '" + task.getDescription() + "'";  // SQL Injection vulnerability
            stmt.executeUpdate(sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public void executeDynamicSQL(String userInput) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.execute(userInput);  // Severe SQL Injection vulnerability
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
