package analytics;

import managers.ActivityManager;
import managers.MealManager;
import models.Activity;
import models.Meal;

import java.util.List;

public class Analytics {
    private ActivityManager activityManager;
    private MealManager mealManager;

    public Analytics(ActivityManager activityManager, MealManager mealManager){
        this.activityManager = activityManager;
        this.mealManager = mealManager;
    }

    public double calculateTotalCaloriesBurned(){
        List<Activity> activities = activityManager.getActivities();
        return activities.stream()
                .mapToDouble(Activity::getCaloriesBurned)
                .sum();
    }

    public double calculateTotalCaloriesConsumed(){
        List<Meal> meals = mealManager.getMeals();
        return meals.stream()
                .mapToDouble(Meal::getCalories)
                .sum();
    }

    public double calculateAvCaloriesBurnedPerActivity(){
        List<Activity> activities = activityManager.getActivities();
        if(activities.isEmpty()){
            return 0;
        }
        return calculateTotalCaloriesBurned() / activities.size();
    }

    public double calculateAVCaloriesConsumedPerMeal(){
        List<Meal> meals = mealManager.getMeals();
        if(meals.isEmpty()){
            return 0;
        }
        return calculateTotalCaloriesConsumed() / meals.size();
    }

    public void displayCaloriesGraph(){
        double burned = calculateTotalCaloriesBurned();
        double consumed = calculateTotalCaloriesConsumed();

        System.out.println("Calories graph: ");
        System.out.println("Burned: ");
        for(int i = 0; i < burned/10; i++){
            System.out.print("|");
        }
            System.out.println(" " + burned);

        System.out.println("Consumed: ");
        for(int i = 0; i < consumed / 10; i++){
            System.out.print("|");
        }
        System.out.println(" " + consumed);
    }
}
