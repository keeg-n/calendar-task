// Task.java
/**
 * Represents a task with a title, description, due date, and completion status.
 */
public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private String dueDate;
    private boolean isCompleted;

    public Task(String title, String description, String dueDate) {
        this.title = title;
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

    public String getTitle() {
        return title;
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
