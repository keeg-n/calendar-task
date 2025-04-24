import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(String newDueDate) {
        this.dueDate = LocalDate.parse(newDueDate);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public boolean isOverdue() {
        return !completed && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return title + " (Due: " + dueDate + ")" + (completed ? " ✔" : " ✘");
    }

    @Override
    public int compareTo(Task other) {
        return this.dueDate.compareTo(other.dueDate);
    }
}
