package src.main.java;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks;
    
    public TodoList() {
        tasks = new ArrayList<>();
    }
    
    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("Task added successfully.");
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
}


