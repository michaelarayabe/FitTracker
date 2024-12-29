package models;

public class User {
    private String username;
    private int age;
    private double weight;
    private double height;

    public User(String username, int age, double weight, double height){
        this.username = username;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public double calculateBMI(){
        return weight/Math.pow(height / 100, 2);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
