import java.util.Scanner;

/**
 * Main class to run the Calendar Application.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = new User(username);

        while (true) {
            System.out.println("\n1. Add Task\n2. Complete Task\n3. View Tasks\n4. View Rewards\n5. Save Tasks\n6. Load Tasks\n7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task due date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    user.addTask(new Task(title, description, dueDate));
                    break;
                case 2:
                    System.out.print("Enter task title to complete: ");
                    String taskToComplete = scanner.nextLine();
                    user.completeTask(taskToComplete);
                    break;
                case 3:
                    user.displayTasks();
                    break;
                case 4:
                    user.getRewardSystem().displayRewards();
                    break;
                case 5:
                    FileHandler.saveTasksToFile(user);
                    System.out.println("Tasks saved successfully.");
                    break;
                case 6:
                    user = FileHandler.loadTasksFromFile(username);
                    if (user == null) {
                        user = new User(username);
                    }
                    break;
                case 7:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}