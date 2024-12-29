package managers;

import models.Activity;

import java.util.ArrayList;

public class ActivityManager {
    private ArrayList<Activity> activities = new ArrayList<>();

    public void logActivity(String type, int duration, double caloriesBurned){
        activities.add(new Activity(type, duration, caloriesBurned));
    }

    public ArrayList<Activity> getActivities(){
        return activities;
    }
}
