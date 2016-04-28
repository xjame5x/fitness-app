package fitnessapp.jimsgym;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {

    EditText etEmail, etPassword;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        userLocalStore = new UserLocalStore(this);
    }

    public void LogIn (View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User(email, password);

        authenticate(user);
    }

    private void authenticate(User user) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        UserLocalStore localStore = new UserLocalStore(this);
        user = localStore.getLoggedInUser();

        if (user == null){
            showErrorMessage();
        }
        else if (user.email.equals(email) && user.password.equals(password)) {
            logUserIn(user);
        }
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LogIn.this);
        dialogBuilder.setMessage("incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, HomeScreen.class));
    }
}
