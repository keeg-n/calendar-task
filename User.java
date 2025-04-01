import java.util.ArrayList;
import java.util.Collections;

public class User {
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

    // Add these getter methods below the constructor
    public String getUsername() {
        return username;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        Collections.sort(tasks);
    }

    public void completeTask(String taskTitle) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(taskTitle)) {
                task.markCompleted();
                rewardSystem.addPoints(10);
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void displayTasks() {
        System.out.println("Tasks for " + username + ":");
        for (Task task : tasks) {
            System.out.println(task.getTitle() + " - " + task.getDescription() + " - Due: " + task.getDueDate() + " - Completed: " + task.isCompleted());
        }
    }

    public RewardSystem getRewardSystem() {
        return rewardSystem;
    }
}

