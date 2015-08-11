package com.codeu.alurt;

import android.content.Intent;
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

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int countryIndex = 1;
        // if US, countryIndex = 1
        // if UK, countryIndex = 3
        // if Canada, countryIndex = 5

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        // The detail Activity called via intent.  Inspect the intent for forecast data.
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String disasterStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.detail_text))
                    .setText(disasterStr +
                            "\n\n\n" + data.getDisasterData(disasterStr)[0] +
                            "\n\n\n" + data.getDisasterData(disasterStr)[countryIndex] +
                            "\n\n\n" + data.getDisasterData(disasterStr)[countryIndex+1]);
        }
        return rootView;
    }

    // code from sunshine to send a text message
//    private Intent createShareForecastIntent() {
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecast + FORECAST_SHARE_HASHTAG);
//        return shareIntent;
//    }
}
