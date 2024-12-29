package managers;

import models.Goal;

import java.util.HashMap;

public class GoalManager {
    private HashMap<String, Goal> goals = new HashMap<>();

    public void setGoals(String userName, Goal goal){
        goals.put(userName, goal);
    }

    public Goal getGoal(String username){
        return goals.get(username);
    }

    public boolean hasGoal(String username){
        return goals.containsKey(username);
    }
}
