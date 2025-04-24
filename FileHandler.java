import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandler {

    public static void saveTasksToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(user.getUsername() + "_tasks.txt"))) {
            for (Task task : user.getTasks()) {
                writer.write(task.getTitle() + ";" +
                        task.getDescription() + ";" +
                        task.getDueDate().toString() + ";" +
                        task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static User loadTasksFromFile(String username) {
        User user = new User(username);
        File file = new File(username + "_tasks.txt");

        if (!file.exists()) return user;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String title = parts[0];
                    String description = parts[1];
                    LocalDate dueDate = LocalDate.parse(parts[2]);
                    boolean completed = Boolean.parseBoolean(parts[3]);
                    Task task = new Task(title, description, dueDate);
                    task.setCompleted(completed);
                    user.getTasks().add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return user;
    }
}
