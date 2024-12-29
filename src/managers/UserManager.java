package managers;

import models.User;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users = new HashMap<>();

    public boolean addUser(String username, int age, double weight, double height){
        if(users.containsKey(username)){
            return false;
        }
        users.put(username, new User(username, age, weight, height));
        return true;
    }

    public User getUser(String username){
        return users.get(username);
    }
}
