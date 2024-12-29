package persistence;

import models.User;

import java.io.*;
import java.util.HashMap;

public class DataStore {
    private static final String DATA_FILE = "data.dat";

    public static void saveData(HashMap<String, User> users){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))){
            oos.writeObject(users);
        } catch (IOException e){
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static HashMap<String, User> loadData(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))){
            return (HashMap<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading data: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
