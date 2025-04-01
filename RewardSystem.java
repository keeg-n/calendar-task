// RewardSystem.java
/**
 * Manages a user's reward points for completing tasks.
 */
public class RewardSystem {
    private int points;

    public RewardSystem() {
        this.points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void displayRewards() {
        System.out.println("Reward Points: " + points);
    }
}