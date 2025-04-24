import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskDashboardGUI extends JFrame {
    private final String username;
    private final User user;
    private TaskTableModel taskTableModel;
    private JTable taskTable;
    private JLabel rewardLabel;

    public TaskDashboardGUI(String username) {
        this.username = username;
        this.user = FileHandler.loadTasksFromFile(username);
        setTitle("Welcome, " + username);
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Setup task table
        ArrayList<Task> tasks = user.getTasks();
        taskTableModel = new TaskTableModel(tasks);
        taskTable = new JTable(taskTableModel);
        add(new JScrollPane(taskTable), BorderLayout.CENTER);

        // Checkbox updates
        taskTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                Task task = taskTableModel.getTasks().get(row);
                if (task.isCompleted()) {
                    user.completeTask(task.getTitle());
                }
                UserDataManager.saveTasks(username, taskTableModel.getTasks());
                updateRewards();
            }
        });

        // Reward label
        rewardLabel = new JLabel("Reward Progress: Level 1 - 0%", SwingConstants.CENTER);
        rewardLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(rewardLabel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton editButton = new JButton("Edit Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton customizeButton = new JButton("Customize Character");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(customizeButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button logic
        addButton.addActionListener(e -> TaskDialog.showAddDialog(this, username, taskTableModel));
        editButton.addActionListener(e -> {
            int selected = taskTable.getSelectedRow();
            if (selected != -1) {
                Task selectedTask = taskTableModel.getTasks().get(selected);
                TaskDialog.showEditDialog(this, username, selectedTask, taskTableModel);
            }
        });
        deleteButton.addActionListener(e -> {
            int selected = taskTable.getSelectedRow();
            if (selected != -1) {
                taskTableModel.getTasks().remove(selected);
                taskTableModel.fireTableDataChanged();
                UserDataManager.saveTasks(username, taskTableModel.getTasks());
                updateRewards();
            }
        });
        customizeButton.addActionListener(e -> new CharacterCustomizerGUI(username));
        logoutButton.addActionListener(e -> {
            FileHandler.saveTasksToFile(user);
            dispose();
            new LoginGUI();
        });

        checkOverdueTasks();
        updateRewards();
        setVisible(true);
    }

    private void checkOverdueTasks() {
        LocalDate today = LocalDate.now();
        for (Task task : taskTableModel.getTasks()) {
            if (task.getDueDate().isBefore(today) && !task.isCompleted()) {
                JOptionPane.showMessageDialog(this, "You have an overdue task: " + task.getTitle(),
                        "Overdue Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void updateRewards() {
        int completed = 0;
        for (Task task : taskTableModel.getTasks()) {
            if (task.isCompleted()) {
                completed++;
            }
        }
        int level = (completed / 5) + 1;
        int progress = (completed % 5) * 20;
        rewardLabel.setText("Reward Progress: Level " + level + " - " + progress + "%");
    }
}
