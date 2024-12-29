package models;

public class Goal {
    private String description;
    private double targetCalories;
    private double currentProgress;

    public Goal(String description, double targetCalories){
        this.description = description;
        this.targetCalories = targetCalories;
        this.currentProgress = 0;
    }

    public void updateProgress(double caloriesBurned){
        this.currentProgress += caloriesBurned;
    }

    public boolean isGoalAchieved(){
        return currentProgress >= targetCalories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(double targetCalories) {
        this.targetCalories = targetCalories;
    }

    public double getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(double currentProgress) {
        this.currentProgress = currentProgress;
    }
}
