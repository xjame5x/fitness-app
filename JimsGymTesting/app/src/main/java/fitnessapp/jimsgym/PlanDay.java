package fitnessapp.jimsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PlanDay extends AppCompatActivity {

    Button addPlan;
    MyListFragment list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_day);

        list = new MyListFragment();
        //ListView list = (ListView) findViewById(R.id.fragment1);
        addPlan = (Button) findViewById(R.id.btnAddPlan);
        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanDay.this, "test", Toast.LENGTH_SHORT).show();
                list.addToList("test");
            }
        });
    }
}
