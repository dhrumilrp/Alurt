package com.codeu.alurt;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    DataStorage data = new DataStorage();
    String countryName = "usa";
    String disasterStr;
    int countryIndex = 1; // default to usa
    static String active_disaster;

    public DetailActivityFragment() {
    }

    public static Intent createShareAlertIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi this is a test");

        //sample phone number for testing purposes
        shareIntent.putExtra("address", "999-999-9999");
        return shareIntent;
    }

    public static Intent createShareAlertIntent(Location myLocation) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");

        String location_url;

        if(myLocation == null){
            location_url = "Location NOT FOUND";
        }
        else{
            location_url =
                    "https://www.google.com/maps/@" + myLocation.getLatitude()
                            + ","  + myLocation.getLongitude();
        }


        shareIntent.putExtra(Intent.EXTRA_TEXT, active_disaster + " " +
                location_url);

        //sample phone number for testing purposes
        shareIntent.putExtra("address", "999-999-9999;555-555-5555;333-333-3333");
        return shareIntent;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            disasterStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            active_disaster = disasterStr;
            ((TextView) rootView.findViewById(R.id.detail_text))
                    .setText(disasterStr +
                            "\n\n\n" + data.getDisasterData(disasterStr)[0] +
                            "\n\n\n" + data.getDisasterData(disasterStr)[countryIndex] +
                            "\n\n\n" + data.getDisasterData(disasterStr)[countryIndex+1]);
        }

        return rootView;
    }

    @Override
    public void onResume() {

        super.onResume();

        countryName = SettingsActivity.getCountry();

        if (countryName.equals("usa")) {
            countryIndex = 1;
        }
        else if (countryName.equals("uk")) {
            countryIndex = 3;
        }
        else if (countryName.equals("can")) {
            countryIndex = 5;
        } else {
            countryIndex = 0; // should never occur
        }

        View rootView = getView();
        ((TextView) rootView.findViewById(R.id.detail_text))
                .setText(disasterStr +
                        "\n\n\n" + data.getDisasterData(disasterStr)[0] +
                        "\n\n\n" + data.getDisasterData(disasterStr)[countryIndex] +
                        "\n\n\n" + data.getDisasterData(disasterStr)[countryIndex + 1]);
    }

}