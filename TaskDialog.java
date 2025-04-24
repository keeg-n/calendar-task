import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskDialog {

    public static void showAddDialog(JFrame parent, String username, TaskTableModel model) {
        Task task = showTaskDialog(parent, null);
        if (task != null) {
            model.getTasks().add(task);
            model.fireTableDataChanged();
            UserDataManager.saveTasks(username, model.getTasks());
        }
    }

    public static void showEditDialog(JFrame parent, String username, Task task, TaskTableModel model) {
        Task updated = showTaskDialog(parent, task);
        if (updated != null) {
            User user = FileHandler.loadTasksFromFile(username);
            user.changeTask(task.getTitle(), updated.getTitle(), updated.getDescription(), updated.getDueDate().toString());

            int index = model.getTasks().indexOf(task);
            model.getTasks().set(index, updated);
            model.fireTableDataChanged(); // refresh GUI


            UserDataManager.saveTasks(username, model.getTasks());
        }
    }

    private static Task showTaskDialog(JFrame parent, Task existingTask) {
        JTextField titleField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField dueDateField = new JTextField("yyyy-mm-dd");

        if (existingTask != null) {
            titleField.setText(existingTask.getTitle());
            descriptionField.setText(existingTask.getDescription());
            dueDateField.setText(existingTask.getDueDate().toString());
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Due Date (yyyy-mm-dd):"));
        panel.add(dueDateField);

        int result = JOptionPane.showConfirmDialog(parent, panel,
                existingTask == null ? "Add Task" : "Edit Task",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText().trim();
                String desc = descriptionField.getText().trim();
                LocalDate dueDate = LocalDate.parse(dueDateField.getText().trim());
                return new Task(title, desc, dueDate);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(parent, "Invalid date format. Use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
