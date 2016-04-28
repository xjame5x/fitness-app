package fitnessapp.jimsgym;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class CreateProfileScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_screen);
    }

    public void  newAccount(View view) {
        Intent startNewActivity = new Intent(this, LogInScreen.class);
        startActivity(startNewActivity);
    }

    public void LogIn(View view) {
        Intent startNewActivity = new Intent(this, LogIn.class);
        startActivity(startNewActivity);
    }
}