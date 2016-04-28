package fitnessapp.jimsgym;

/**
 * Created by james_000 on 10/03/2016.
 */
public class User {

    String name, dob, email, password;
    int weight, height, calories, exerciseTime;

    public User(String name, String dob, String email, String password, int height, int weight, int calories, int excerciseTime) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.calories = calories;
        this.exerciseTime = excerciseTime;
    }

    public User(String email, String password) {
        this.name = "";
        this.dob = "";
        this.email = email;
        this.password = password;
        this.height = -1;
        this.weight = -1;
    }
}