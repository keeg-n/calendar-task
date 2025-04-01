// ThemeManager.java
/**
 * Handles user theme preferences for customization.
 */
public class ThemeManager {
    private String theme;

    public ThemeManager(String theme) {
        this.theme = theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }
}