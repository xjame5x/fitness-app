package fitnessapp.jimsgym;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by james_000 on 10/03/2016.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userdetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.putString("dob", user.dob);
        spEditor.putInt("weight", user.weight);
        spEditor.putInt("height", user.height);
        spEditor.putInt("calories", user.calories);
        spEditor.putInt("exerciseTime", user.exerciseTime);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String name = userLocalDatabase.getString("name", "");
        String email = userLocalDatabase.getString("email", "");
        String password = userLocalDatabase.getString("password", "");
        String dob = userLocalDatabase.getString("dob", "");
        int weight = userLocalDatabase.getInt("weight", -1);
        int height = userLocalDatabase.getInt("height", -1);
        int calories = userLocalDatabase.getInt("calories", -1);
        int exerciseTime = userLocalDatabase.getInt("exerciseTime", -1);

        User storedUser = new User(name, dob, email, password, height, weight, calories, exerciseTime);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == true) {
            return true;
        } else {
            return false;
        }
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public String getEmail(){
        String email = userLocalDatabase.getString("email", "");
        return email;
    }
}
