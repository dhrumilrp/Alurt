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
                "Call: (800) 799-SAFE",
                "http://www.thehotline.org/",
                "Call: 0808 2000 247",
                "http://www.nationaldomesticviolencehelpline.org.uk/",
                "Call: Canada #",
                "http://endabuseinlanark.ca/"
        };
        final String[] rapeDetails = {
                "Forcing sexual relations on another individual without consent",
                "Call: (800) 656-HOPE",
                "https://www.rainn.org/",
                "Call: UK phone #",
                "http://rapecrisis.org.uk/",
                "Call: Canada #",
                "http://www.sexassault.ca/"
        };
        final String[] theftDetails = {
                "Taking another individual's property without permission",
                "Call: 330 376-0040",
                "http://www.crimedoctor.com/home.htm",
                "Call: UK phone #",
                "https://www.police.uk/crime-prevention-advice/burglary/",
                "Call: Canada #",
                "canada website"
        };
        final String[] houseDamageDetails = {
                "Home disaster, flood, or gas leak",
                "Call: 1-800-427-2200",
                "https://www.socalgas.com/safety/emergency.shtml",
                "Call: UK phone #",
                "UK website",
                "Call: Canada #",
                "canada website"
        };
        final String[] fireDetails = {
                "Fire",
                "Call: 1-800-FIRE-LINE",
                "http://www.nvfc.org/",
                "Call: 020 8555 1200",
                "http://www.london-fire.gov.uk/",
                "Call: Canada #",
                "http://www.firefightingincanada.com/"
        };
        final String[] heartAttackDetails = {
                "Chest pain, shortness of breath, cold sweat, naseua, lightheadedness",
                "Call: 1-800-242-8721",
                "http://www.heart.org",
                "Call: UK phone #",
                "https://www.bhf.org.uk/heart-health/how-we-can-help/heart-helpline",
                "Call: Canada #",
                "http://www.redcross.ca/training-and-certification/first-aid-tips-and-resources-/first-aid-tips/signs---symptoms-of-a-heart-attack"
        };
        final String[] poisonDetails = {
                "Poison inhaled, consumed, or exposed",
                "Call: (800) 222-1222",
                "http://www.aapcc.org/",
                "Call: 111",
                "http://www.npis.org/",
                "Call: Canada #",
                "http://www.capcc.ca/c"
        };
        final String[] seriousInjuryDetails = {
                "Strains, sprains, and fractures",
                "Call: 800.424.2725",
                "<US website>",
                "Call: UK phone #",
                "www.seriousinjuryhelpline.co.uk/",
                "Call: Canada #",
                "<Canada website>"
        };
        final String[] suicideDetails = {
                "Suicide attempt or severe depression",
                "Call: (800) 273-TALK",
                "http://www.suicidepreventionlifeline.org/getinvolved/locator",
                "Call: 08457 90 90 90",
                "http://www.samaritans.org/",
                "Call: 416-486-2242",
                "http://www.dcontario.org/"
        };
        final String[] strokeDetails = {
                "Face drooping, arm weakness, difficulty speaking",
                "Call: 1-800-STROKES",
                "http://www.stroke.org/",
                "UK phone #",
                "http://www.nhs.uk/conditions/Stroke/Pages/Introduction.aspx",
                "Call: Canada #",
                "https://www.marchofdimes.ca/EN/programs/src/Pages/src.aspx"
        };

        HashMap<String, String[]> disasterDetails = new HashMap<String, String[]>();

        disasterDetails.put("ASSAULT", assaultDetails);
        disasterDetails.put("RAPE", rapeDetails);
        disasterDetails.put("THEFT", theftDetails);
        disasterDetails.put("HOUSE DAMAGE", houseDamageDetails);
        disasterDetails.put("FIRE", fireDetails);
        disasterDetails.put("HEART ATTACK", heartAttackDetails);
        disasterDetails.put("POISON", poisonDetails);
        disasterDetails.put("SERIOUS INJURY", seriousInjuryDetails);
        disasterDetails.put("SUICIDE", suicideDetails);
        disasterDetails.put("STROKE", strokeDetails);


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
                            "\n\n\n" + disasterDetails.get(disasterStr)[0] +
                            "\n\n\n" + disasterDetails.get(disasterStr)[countryIndex] +
                            "\n\n\n" + disasterDetails.get(disasterStr)[countryIndex+1]);
        }
        return rootView;
    }
}
