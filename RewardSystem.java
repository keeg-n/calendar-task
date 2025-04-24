public class RewardSystem {
    private int points;
    private int level;
    private int streak;

    public RewardSystem() {
        this.points = 0;
        this.level = 1;
        this.streak = 0;
    }

    public void completeTask(Task task) {
        if (!task.isCompleted()) {
            return;
        }
        streak++;
        int bonus = (streak % 5 == 0) ? 10 : 0;
        addPoints(10 + bonus);
        level = points / 50 + 1;
    }

    public void addPoints(int pts) {
        points += pts;
    }

    public void displayProgress() {
        int nextLevelPoints = (level) * 50;
        int currentLevelStart = (level - 1) * 50;
        int currentProgress = points - currentLevelStart;
        int levelProgressTotal = nextLevelPoints - currentLevelStart;
        double percent = (double) currentProgress / levelProgressTotal * 100;

        System.out.println("\n🏆 Progress Report:");
        System.out.println("Level: " + level);
        System.out.println("Points: " + points + " (" + currentProgress + "/" + levelProgressTotal + ")");
        System.out.printf("Progress to next level: %.2f%%\n", percent);
        System.out.println("🔥 Current Streak: " + streak + " task(s) in a row!\n");
    }

    public int getPoints() {
        return points;
    }

    public int getLevel() {
        return level;
    }

    public int getStreak() {
        return streak;
    }
}
