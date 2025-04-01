// FileHandler.java
import java.io.*;
import java.util.*;

/**
 * Handles saving and loading user tasks from a file.
 */
public class FileHandler {
    public static void saveTasksToFile(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(user.getUsername() + "_tasks.txt"))) {
            for (Task task : user.getTasks()) {
                writer.println(task.getTitle() + "," + task.getDescription() + "," + task.getDueDate() + "," + task.isCompleted());
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
                Task task = new Task(parts[0], parts[1], parts[2]);
                if (Boolean.parseBoolean(parts[3])) {
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