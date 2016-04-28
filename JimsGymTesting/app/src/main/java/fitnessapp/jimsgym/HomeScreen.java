package fitnessapp.jimsgym;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    String[] navArray = {"Home", "Profile", "Add Food",
                         "Add Exercise", "Log out"};
    UserLocalStore userLocalStore;
    User user;
    ProgressTracker progressTracker;
    TextView userName;
    TextView calories;
    TextView exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Home");

        setDate();

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();
        progressTracker = new ProgressTracker();

        String localUserName = user.name;
        int localCalories = user.calories;
        int localExercise = user.exerciseTime;
        String capName = localUserName.substring(0, 1).toUpperCase() + localUserName.substring(1);
        String welcome = "Welcome back, ";
        String userN = welcome + capName;

        userName = (TextView) findViewById(R.id.tvTest);
        calories = (TextView) findViewById(R.id.tvCalories);
        exercise = (TextView) findViewById(R.id.tvExercise);

        userName.setText(userN);
        calories.setText(calories.getText() + " " + Integer.toString(localCalories));
        exercise.setText(exercise.getText() + " " + Integer.toString(localExercise));

        if (user.calories >= 2500){
            calories.setTextColor(Color.RED);
        }
    }

    //Sets the nav. menu content
    private void addDrawerItems() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), HomeScreen.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), Profile.class);
                    startActivity(intent);
                }
                if (position == 2){
                    startActivity(new Intent(view.getContext(), AddFood.class));
                }
                if(position == 3){
                    startActivity(new Intent(view.getContext(), AddExercise.class));
                }
                if (position == 4) {
                    //userLocalStore.clearUserData();
                    userLocalStore.setUserLoggedIn(false);
                    startActivity(new Intent(view.getContext(), CreateProfileScreen.class));
                }
            }
        });
    }

    //Configs the toolbar depending on whether its open or closed
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu(
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("Home");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu(
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    //handles button clicks (not relevant)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //adds icons to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //syncs the toolbar with the nav. menu
    //makes it run smoother
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void planDay(View view){
        Intent intent = new Intent(this, PlanDay.class);
        startActivity(intent);
    }

    public void addFood(View view){
        startActivity(new Intent(this, AddFood.class));
    }
    public void addExercise(View view){
        startActivity(new Intent(this, AddExercise.class));
    }
    public void storeProgress(View view){
        progressTracker.setTotalCalories(user.calories - user.exerciseTime);
        user.calories = 0;
        user.exerciseTime = 0;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    private void setDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        TextView dayOfTheWeek = (TextView) findViewById(R.id.tvDay);
        dayOfTheWeek.setText(sdf.format(d));
    }
}
