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

import java.util.ArrayList;
import java.util.List;

public class AddFood extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    String[] navArray = {"Home", "Profile", "Add Food",
            "Add Exercise", "Log out"};

    Food food1, food2, food3, food4, food5;
    Food food6, food7, food8, food9, food10;
    Button btn1, btn2, btn3, btn4, btn5;
    Button btn6, btn7, btn8, btn9, btn10;

    User user;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Add Food");

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        addFood();
        setButtonText();

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

    public void addFood(){
        food1 = new Food("SPICY MEATBALLS WITH CHILI BLACK BEAN",376);
        food2 = new Food("COCONUT & SQUASH DHANSAK",320);
        food3 = new Food("LIGHT CHICKEN KORMA",376);
        food4 = new Food("WINTER VEGETABLE PIE",388);
        food5 = new Food("OVEN-BAKED FISH AND CHIPS",366);
        food6 = new Food("MEDITERRANEAN FISH GRATINS",372);
        food7 = new Food("CAULIFLOWER, PANEER & PEA CURRY",321);
        food8 = new Food("PORK & BULGHAR-STUFFED PEPPERS",185);
        food9 = new Food("CHICKEN, BROCCOLI & BEETROOT SALAD WITH AVOCADO PESTO", 320);
        food10 = new Food("MEXICAN BEAN BURGERS WITH LIME YOGURT & SALSA",195);
    }

    public void setButtonText(){
        btn1 = (Button) findViewById(R.id.btnFood1);
        btn1.setText(food1.name);
        btn2 = (Button) findViewById(R.id.btnFood2);
        btn2.setText(food2.name);
        btn3 = (Button) findViewById(R.id.btnFood3);
        btn3.setText(food3.name);
        btn4 = (Button) findViewById(R.id.btnFood4);
        btn4.setText(food4.name);
        btn5 = (Button) findViewById(R.id.btnFood5);
        btn5.setText(food5.name);
        btn6 = (Button) findViewById(R.id.btnFood6);
        btn6.setText(food6.name);
        btn7 = (Button) findViewById(R.id.btnFood7);
        btn7.setText(food7.name);
        btn8 = (Button) findViewById(R.id.btnFood8);
        btn8.setText(food8.name);
        btn9 = (Button) findViewById(R.id.btnFood9);
        btn9.setText(food9.name);
        btn10 = (Button) findViewById(R.id.btnFood10);
        btn10.setText(food10.name);
    }

    public void food1Btn(View view){
        user.calories += food1.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void food2Btn(View view){
        user.calories += food2.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void food3Btn(View view){
        user.calories += food3.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }
    public void food4Btn(View view){
        user.calories += food4.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }
    public void food5Btn(View view){
        user.calories += food5.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void food6Btn(View view){
        user.calories += food6.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void food7Btn(View view){
        user.calories += food7.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void food8Btn(View view){
        user.calories += food8.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }
    public void food9Btn(View view){
        user.calories += food9.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }
    public void food10Btn(View view){
        user.calories += food10.cal;
        userLocalStore.storeUserData(user);
        startActivity(new Intent(this, HomeScreen.class));
    }
}
