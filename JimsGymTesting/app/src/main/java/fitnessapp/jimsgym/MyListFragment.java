package fitnessapp.jimsgym;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by james_000 on 14/03/2016.
 */
public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    //Button btnAddPlan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "item" + position, Toast.LENGTH_SHORT).show();
    }

    public void addToList(String text){
        list.add(text);
        adapter.notifyDataSetChanged();
    }
}
