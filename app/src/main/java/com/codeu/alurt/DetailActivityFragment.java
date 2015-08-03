package com.codeu.alurt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // details for each view, including description and phone number
        final String[] assaultDetails = {
                "To make a physical attack or to threaten bodily harm",
                "Call: local police station",
                "<police station phone number>"
        };
        final String[] rapeDetails = {
                "Forcing sexual relations on another individual without consent",
                "Call: local police station",
                "<police station phone number>"
        };
        final String[] theftDetails = {
                "Taking another individual's property without permission",
                "Call: local police station",
                "<phone number>"
        };
        final String[] floodDetails = {
                "An overflowing of a large amount of water beyond its normal confines",
                "Call: electrician, plumber, flood cleanup crew",
                "<phone number>"
        };
        final String[] fireDetails = {
                "Fire",
                "Call: local fire department",
                "<fire dept phone number>"
        };
        final String[] heartAttackDetails = {
                "Chest pain, shortness of breath, cold sweat, naseua, lightheadedness",
                "Call: local police station",
                "<phone number>"
        };
        final String[] fallDetails = {
                "Fall",
                "Call: local police station",
                "<phone number>"
        };
        final String[] seriousInjuryDetails = {
                "Strains, sprains, and fractures",
                "Call: local police station",
                "<phone number>"
        };
        final String[] headacheDetails = {
                "Severe migraines",
                "Call: local police station",
                "<phone number>"
        };
        final String[] strokeDetails = {
                "Face drooping, arm weakness, difficulty speaking",
                "Call: local police station",
                "<phone number>"
        };

        HashMap<String, String[]> disasterDetails = new HashMap<String, String[]>();

        disasterDetails.put("ASSAULT", assaultDetails);
        disasterDetails.put("RAPE", rapeDetails);
        disasterDetails.put("THEFT", theftDetails);
        disasterDetails.put("FLOOD", floodDetails);
        disasterDetails.put("FIRE", fireDetails);
        disasterDetails.put("HEART ATTACK", heartAttackDetails);
        disasterDetails.put("FALL", fallDetails);
        disasterDetails.put("SERIOUS INJURY", seriousInjuryDetails);
        disasterDetails.put("HEADACHE", headacheDetails);
        disasterDetails.put("STROKE", strokeDetails);

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        // The detail Activity called via intent.  Inspect the intent for forecast data.
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String disasterStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.detail_text))
                    .setText(disasterStr +
                            "\n\n\n" + disasterDetails.get(disasterStr)[0] +
                            "\n\n\n" + disasterDetails.get(disasterStr)[1] +
                            "\n\n\n" + disasterDetails.get(disasterStr)[2]);
        }
        return rootView;
    }
}
