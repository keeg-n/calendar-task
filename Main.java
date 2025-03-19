import java.io.*;
import java.util.*;

public class CalendarApp {
    public static void main(String[] args) {
        User user = new User("JohnDoe");
        user.addTask(new Task("Finish Java Project", "2025-02-10"));
        user.addTask(new Task("Submit Report", "2025-02-12"));
        user.completeTask("Finish Java Project");
        
        user.displayTasks();
        user.getRewardSystem().displayRewards();
        
        // Save tasks to file
        FileHandler.saveTasksToFile(user);
        
        // Read and display saved tasks
        User loadedUser = FileHandler.loadTasksFromFile("JohnDoe");
        if (loadedUser != null) {
            loadedUser.displayTasks();
        }
    }
}

class Task implements Comparable<Task> {
    private String description;
    private String dueDate;
    private boolean isCompleted;

    public Task(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }
    
    @Override
    public int compareTo(Task other) {
        return this.dueDate.compareTo(other.dueDate);
    }
}

class User {
    private String username;
    private ArrayList<Task> tasks;
    private RewardSystem rewardSystem;
    private ThemeManager themeManager;

    public User(String username) {
        this.username = username;
        this.tasks = new ArrayList<>();
        this.rewardSystem = new RewardSystem();
        this.themeManager = new ThemeManager("Default");
    }

    public void addTask(Task task) {
        tasks.add(task);
        Collections.sort(tasks);  // Sort tasks by due date
    }

    public void completeTask(String taskDescription) {
        for (Task task : tasks) {
            if (task.getDescription().equals(taskDescription)) {
                task.markCompleted();
                rewardSystem.addPoints(10);
            }
        }
    }

    public void displayTasks() {
        System.out.println("Tasks for " + username + ":");
        for (Task task : tasks) {
            System.out.println(task.getDescription() + " - Due: " + task.getDueDate() + " - Completed: " + task.isCompleted());
        }
        System.out.println("Completed Tasks: " + countCompletedTasks(tasks.size() - 1));
    }
    
    private int countCompletedTasks(int index) {  // Recursion for counting completed tasks
        if (index < 0) return 0;
        return (tasks.get(index).isCompleted() ? 1 : 0) + countCompletedTasks(index - 1);
    }

    public RewardSystem getRewardSystem() {
        return rewardSystem;
    }
    
    public ThemeManager getThemeManager() {
        return themeManager;
    }
}

class RewardSystem {
    private int points;

    public RewardSystem() {
        this.points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void displayRewards() {
        System.out.println("Reward Points: " + points);
    }
}

class ThemeManager {
    private String theme;

    public ThemeManager(String theme) {
        this.theme = theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public String getTheme() {
        return theme;
    }
}

class FileHandler {
    public static void saveTasksToFile(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(user.username + "_tasks.txt"))) {
            for (Task task : user.tasks) {
                writer.println(task.getDescription() + "," + task.getDueDate() + "," + task.isCompleted());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static User loadTasksFromFile(String username) {
        User user = new User(username);
        try (BufferedReader reader = new BufferedReader(new FileReader(username + "_tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Task task = new Task(parts[0], parts[1]);
                if (Boolean.parseBoolean(parts[2])) {
                    task.markCompleted();
                }
                user.addTask(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            return null;
        }
        return user;
    }
}
