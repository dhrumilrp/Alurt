package com.codeu.alurt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        final String[] data = {
                "ASSAULT",
                "RAPE",
                "THEFT",
                "HOUSE DAMAGE",
                "FIRE",
                "HEART ATTACK",
                "POISON",
                "SERIOUS INJURY",
                "SUICIDE",
                "STROKE"
        };
        final List<String> disasterList = new ArrayList<String>(Arrays.asList(data));
        
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
