package managers;

import models.Meal;

import java.util.ArrayList;

public class MealManager {
    private ArrayList<Meal> meals = new ArrayList<>();

    public void logMeal(String name, int calories, double protein, double carbs, double fats){
        meals.add(new Meal(name,calories,protein,carbs,fats));
    }

    public ArrayList<Meal> getMeals(){
        return meals;
    }
}
