// Main class for the Interactive Calendar App
public class CalendarApp {
    public static void main(String[] args) {
        // Initializing
        User user = new User("JohnDoe");
        user.addTask(new Task("Finish Java Project", "2025-02-10"));
        user.completeTask("Finish Java Project");

        // Show user details and rewards
        user.displayTasks();
        user.getRewardSystem().displayRewards();
    }
}
//representing a Task
class Task {
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
}

// Class representing a User
class User {
    private String username;
    private ArrayList<Task> tasks;
    private RewardSystem rewardSystem;

    public User(String username) {
        this.username = username;
        this.tasks = new ArrayList<>();
        this.rewardSystem = new RewardSystem();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
