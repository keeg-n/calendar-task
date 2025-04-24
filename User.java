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
                if (!task.isCompleted()) {
                    task.markCompleted();
                    rewardSystem.completeTask(task);
                    System.out.println("✅ Task marked as completed! Points awarded.");
                } else {
                    System.out.println("Task was already completed.");
                }
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void changeTask(String title, String newTitle, String newDescription, String newDueDate) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                task.setDueDate(newDueDate);
                System.out.println("✏️ Task updated.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void removeTask(String title) {
        boolean removed = tasks.removeIf(task -> task.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("🗑️ Task removed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void displayTasks() {
        System.out.println("📋 Tasks for " + username + ":");
        for (Task task : tasks) {
            System.out.print(task.getTitle() + " - " + task.getDescription() + " - Due: " + task.getDueDate());
            if (task.isCompleted()) {
                System.out.println(" ✅ Completed");
            } else if (task.isOverdue()) {
                System.out.println(" ⚠️ Overdue");
            } else {
                System.out.println(" ⏳ Pending");
            }
        }
    }

    public void displayOverdueTasks() {
        boolean found = false;
        for (Task task : tasks) {
            if (task.isOverdue()) {
                System.out.println("⚠️ " + task.getTitle() + " - Due: " + task.getDueDate());
                found = true;
            }
        }
        if (!found) {
            System.out.println("🎉 No overdue tasks!");
        }
    }

    public RewardSystem getRewardSystem() {
        return rewardSystem;
    }

    public ThemeManager getThemeManager() {
        return themeManager;
    }
}
