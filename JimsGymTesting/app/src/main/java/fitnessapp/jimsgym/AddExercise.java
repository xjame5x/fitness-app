package fitnessapp.jimsgym;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class AddExercise extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    String[] navArray = {"Home", "Profile", "Add Food",
            "Add Exercise", "Log out"};
    User user;
    UserLocalStore userLocalStore;
    Exercise exercise1, exercise2, exercise3, exercise4;
    Exercise exercise5, exercise6, exercise7, exercise8;

    Button btn1, btn2, btn3, btn4;
    Button btn5, btn6, btn7, btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Add Exercise");

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        addExercise();
        setButtonText();
    }

    private void setButtonText() {
        btn1 = (Button) findViewById(R.id.btnexercise1);
        btn1.setText(exercise1.name);
        btn2 = (Button) findViewById(R.id.btnexercise2);
        btn2.setText(exercise2.name);
        btn3 = (Button) findViewById(R.id.btnexercise3);
        btn3.setText(exercise3.name);
        btn4 = (Button) findViewById(R.id.btnexercise4);
        btn4.setText(exercise4.name);
        btn5 = (Button) findViewById(R.id.btnexercise5);
        btn5.setText(exercise5.name);
        btn6 = (Button) findViewById(R.id.btnexercise6);
        btn6.setText(exercise6.name);
        btn7 = (Button) findViewById(R.id.btnexercise7);
        btn7.setText(exercise7.name);
        btn8 = (Button) findViewById(R.id.btnexercise8);
        btn8.setText(exercise8.name);
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
                getSupportActionBar().setTitle("Add Food");
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

    public void addExercise(){
        exercise1 = new Exercise("PUSH-UP", (538/60)*5);
        exercise2 = new Exercise("RUNNING",(544/60)*5);
        exercise3 = new Exercise("Swimming", (476/60)*5);
        exercise4 = new Exercise("SIT_UP", (538/60)*5);
        exercise5 = new Exercise("PULL-UP", (517/60)*5);
        exercise6 = new Exercise("ROWING", (476/60)*5);
        exercise7 = new Exercise("BOXING", (408/60)*5);
        exercise8 = new Exercise("CYCLING", (476/60)*5);
    }

    public void exbtn1(View view) {
        user.exerciseTime += exercise1.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void exbtn2(View view) {
        user.exerciseTime += exercise2.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void exbtn3(View view) {
        user.exerciseTime += exercise3.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void exbtn4(View view) {
        user.exerciseTime += exercise4.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void exbtn5(View view) {
        user.exerciseTime += exercise5.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void exbtn6(View view) {
        user.exerciseTime += exercise6.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void exbtn7(View view) {
        user.exerciseTime += exercise7.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void exbtn8(View view) {
        user.exerciseTime += exercise8.cph;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }
}
