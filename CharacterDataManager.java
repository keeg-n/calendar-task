import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CharacterDataManager {
    private static final String FILE_PREFIX = "character_";

    public static String loadCharacter(String username) {
        File file = new File(FILE_PREFIX + username + ".txt");
        if (!file.exists()) return "Casual";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        } catch (IOException e) {
            return "Casual";
        }
    }

    public static void saveCharacter(String username, String character) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PREFIX + username + ".txt"))) {
            writer.write(character);
        } catch (IOException e) {
            System.out.println("Error saving character for " + username);
        }
    }
}
