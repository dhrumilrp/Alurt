package com.codeu.alurt;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ArrayAdapter<String> mDisasterAdapter;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        final String[] data = {
                "ASSAULT",
                "RAPE",
                "THEFT",
                "FLOOD",
                "FIRE",
                "HEART ATTACK",
                "FALL",
                "SERIOUS INJURY",
                "HEADACHE",
                "STROKE"
        };
        final List<String> disasterList = new ArrayList<String>(Arrays.asList(data));

        // Now that we have some dummy disaster data, create an ArrayAdapter.
        // The ArrayAdapter will take data from a source (like our dummy forecast) and
        // use it to populate the ListView it's attached to.
        mDisasterAdapter = new ArrayAdapter<String>(
                getActivity(), // The current context (this activity)
                R.layout.list_item_disaster, // The name of the layout ID.
                R.id.list_item_disaster_textview, // The ID of the textview to populate.
                disasterList);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_disaster);
        listView.setAdapter(mDisasterAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String disaster = mDisasterAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, disaster);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
