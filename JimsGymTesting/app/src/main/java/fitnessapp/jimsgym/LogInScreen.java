package fitnessapp.jimsgym;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LogInScreen extends AppCompatActivity {

    EditText etName, etDob, etEmail, etPassword, etWeight, etHeight;
    UserLocalStore createUser;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        etName = (EditText) findViewById(R.id.etName);
        etDob = (EditText) findViewById(R.id.etDob);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight = (EditText) findViewById(R.id.etHeight);
        createUser = new UserLocalStore(this);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(LogInScreen.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etDob.setText(sdf.format(myCalendar.getTime()));
    }

    public void create(View view){
        String name = etName.getText().toString();
        String dob = etDob.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        int weight = Integer.parseInt(etWeight.getText().toString());
        int height = Integer.parseInt(etHeight.getText().toString());
        int calories = 0;
        int exerciseTime = 0;

        if (name.equals("")|| email.equals("")||password.equals("")|| weight == 0 || height == 0) {
            Toast.makeText(this, "One of the fields is not correct", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            User user = new User(name, dob, email, password, height, weight, calories, exerciseTime);
            createUser.storeUserData(user);
            createUser.setUserLoggedIn(true);

            Intent startNewActivity = new Intent(this, HomeScreen.class);
            startActivity(startNewActivity);
        }

    }
}
