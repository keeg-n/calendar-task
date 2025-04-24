import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                
                ✨ Welcome to the Calendar Task App! ✨
                1. Launch GUI
                2. Use Command-Line (CLI)
                3. Exit
                """);
        System.out.print("Select an option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> SwingUtilities.invokeLater(LoginGUI::new);
            case "2" -> runCLI(scanner);
            case "3" -> {
                System.out.println("Goodbye! 👋");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice. Restart and try again.");
        }
    }

    public static void runCLI(Scanner scanner) {
        User user = null;

        while (true) {
            if (user == null) {
                System.out.print("\nEnter your username (or type 'exit' to quit): ");
                String username = scanner.nextLine();
                if (username.equalsIgnoreCase("exit")) break;

                user = FileHandler.loadTasksFromFile(username);
                if (user == null) {
                    user = new User(username);
                    System.out.println("New user created.");
                }

                System.out.println("\n🔔 Overdue Tasks:");
                user.displayOverdueTasks();
            }

            System.out.println("""
                    
                    📋 MENU
                    1. Add Task
                    2. Complete Task
                    3. Change Task
                    4. Remove Task
                    5. View Tasks
                    6. View Rewards
                    7. Save Tasks
                    8. Logout
                    9. Exit
                    """);

            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String due = scanner.nextLine();
                    user.addTask(new Task(title, desc, LocalDate.parse(due)));
                }
                case 2 -> {
                    System.out.print("Enter title of task to complete: ");
                    user.completeTask(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter title of task to change: ");
                    String oldTitle = scanner.nextLine();
                    System.out.print("New title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("New description: ");
                    String newDesc = scanner.nextLine();
                    System.out.print("New due date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    user.changeTask(oldTitle, newTitle, newDesc, newDate);
                }
                case 4 -> {
                    System.out.print("Enter title of task to remove: ");
                    user.removeTask(scanner.nextLine());
                }
                case 5 -> user.displayTasks();
                case 6 -> user.getRewardSystem().displayProgress();
                case 7 -> {
                    FileHandler.saveTasksToFile(user);
                    System.out.println("Tasks saved.");
                }
                case 8 -> {
                    FileHandler.saveTasksToFile(user);
                    user = null;
                    System.out.println("Logged out.");
                }
                case 9 -> {
                    System.out.println("Exiting...");
                    FileHandler.saveTasksToFile(user);
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
