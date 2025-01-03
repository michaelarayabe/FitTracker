import analytics.Analytics;
import managers.ActivityManager;
import managers.GoalManager;
import managers.MealManager;
import managers.UserManager;
import models.Goal;
import models.User;
import persistence.DataStore;

import java.util.Scanner;

public class Application {
    private static UserManager userManager = new UserManager();
    private static ActivityManager activityManager = new ActivityManager();
    private static MealManager mealManager = new MealManager();
    private static GoalManager goalManager = new GoalManager();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Welcome to Fit tracker");
        loadUsers();

        while(true){
            System.out.println("\n1. Add User");
            System.out.println("2. Log Activity");
            System.out.println("3. Log Meal");
            System.out.println("4. View Progress");
            System.out.println("5. Exit");
            System.out.println("6. View Analytics");
            System.out.println("7. Set Goal");
            System.out.println("8. Check Goal");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> addUser();
                case 2 -> logActivity();
                case 3 -> logMeal();
                case 4 -> viewProgress();
                case 5 -> {
                    saveUsers();
                    System.out.println("Good bye!");
                    return;
                }
                case 6 -> viewAnalytics();
                case 7 -> setGoal();
                case 8 -> checkGoal();
                default -> System.out.println("Invalid choice. Try again");
            }
        }
    }

    private static void addUser(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = scanner.nextInt();

        System.out.println("Enter weight: ");
        double weight = scanner.nextDouble();

        System.out.println("Enter height: ");
        double height = scanner.nextDouble();

        if(userManager.addUser(username, age, weight, height)){
            System.out.println("User added successfully!");
        } else {
            System.out.println("User already exists!");
        }
    }

    private static void logActivity(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user = userManager.getUser(username);

        if(user == null){
            System.out.println("User not found");
            return;
        }

        System.out.println("Enter activity type (ex: Running): ");
        String type = scanner.nextLine();

        System.out.println("Enter duration (minutes): ");
        int duration = scanner.nextInt();

        System.out.println("Enter calories burned: ");
        double caloriesBurned = scanner.nextDouble();
        Goal goal = goalManager.getGoal(username);
        goal.updateProgress(caloriesBurned);
        activityManager.logActivity(type, duration, caloriesBurned);
    }

    private static void logMeal(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user = userManager.getUser(username);
        if(user == null){
            System.out.println("User not found");
            return;
        }
        System.out.println("Enter meal name: ");
        String name = scanner.nextLine();

        System.out.println("Enter calories: ");
        int calories = scanner.nextInt();

        System.out.println("Enter protein (g): ");
        double protein = scanner.nextDouble();

        System.out.println("Enter carbs (g): ");
        double carbs = scanner.nextDouble();

        System.out.println("Enter fats (g): ");
        double fats = scanner.nextDouble();

        mealManager.logMeal(name, calories, protein, carbs, fats);
        System.out.println("Meal logged successfully! ");
    }

    private static void viewProgress(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        User user = userManager.getUser(username);

        if(user == null){
            System.out.println("User not found!");
            return;
        }

        System.out.println("\nProgress for " + username + ":");
        System.out.println("BMI: " + user.calculateBMI());
        System.out.println("Meals: " + mealManager.getMeals().size());
    }

    private static void viewAnalytics(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user = userManager.getUser(username);
        if(user == null){
            System.out.println("User not found");
            return;
        }

        Analytics analytics = new Analytics(activityManager, mealManager);

        System.out.println("\nAnalytics for " + username + ":");
        System.out.println("Total calories burned: " + analytics.calculateTotalCaloriesBurned());
        System.out.println("Total calories consumed: " + analytics.calculateTotalCaloriesConsumed());
        System.out.println("Average calories burned per activity: " + analytics.calculateAvCaloriesBurnedPerActivity());
        System.out.println("Average calories consumed per meal: " + analytics.calculateAVCaloriesConsumedPerMeal());

        analytics.displayCaloriesGraph();
    }

    private static void setGoal(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        User user = userManager.getUser(username);
        if(user == null){
            System.out.println("User not found");
            return;
        }
        System.out.println("Enter goal description: ");
        String description = scanner.nextLine();

        System.out.println("Enter target calories: ");
        int targetCalories = scanner.nextInt();

        Goal goal = new Goal(description, targetCalories);
        goalManager.setGoals(username, goal);

        System.out.println("Goal is set successfully!");
    }

    private static void checkGoal(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user = userManager.getUser(username);
        if(user == null){
            System.out.println("User not found");
            return;
        }
        Goal goal = goalManager.getGoal(username);

        if(goal == null){
            System.out.println("No goal is set for this user.");
            return;
        }

        System.out.println("Goal: " + goal.getDescription());
        System.out.println("Progress: " + goal.getCurrentProgress() + " / " + goal.getTargetCalories());
        if(goal.isGoalAchieved()){
            System.out.println("Congrats " + username +". Goal has been achieved!");
        } else {
            System.out.println("Not yet! Need to burn " + (goal.getTargetCalories() - goal.getCurrentProgress()) + " Calories");
        }
    }

    private static void loadUsers(){
        userManager = new UserManager();
        userManager.getUsers().putAll(DataStore.loadData());
        System.out.println("Data loaded");
    }

    private static void saveUsers(){
        DataStore.saveData(userManager.getUsers());
        System.out.println("Data saved.");
    }

}
