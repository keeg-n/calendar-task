import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages user accounts with basic username-password storage.
 */
public class UserAccountManager {
    private static final String ACCOUNTS_FILE = "user_accounts.txt";
    private static Map<String, String> accounts = new HashMap<>();

    static {
        loadAccounts();
    }

    private static void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    accounts.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            // No accounts yet
        }
    }

    private static void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Map.Entry<String, String> entry : accounts.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static boolean register(String username, String password) {
        if (accounts.containsKey(username)) return false;
        accounts.put(username, password);
        saveAccounts();
        return true;
    }

    public static boolean login(String username, String password) {
        return accounts.containsKey(username) && accounts.get(username).equals(password);
    }
}
