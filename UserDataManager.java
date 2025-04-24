import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDataManager {

    public static void saveTasks(String username, ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(username + "_tasks.txt"))) {
            for (Task task : tasks) {
                writer.write(task.getTitle() + ";" + task.getDescription() + ";" + task.getDueDate() + ";" + task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks(String username) {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(username + "_tasks.txt");

        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    String title = parts[0];
                    String desc = parts[1];
                    LocalDate date = LocalDate.parse(parts[2]);
                    boolean completed = Boolean.parseBoolean(parts[3]);
                    Task task = new Task(title, desc, date);
                    task.setCompleted(completed);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }
}
