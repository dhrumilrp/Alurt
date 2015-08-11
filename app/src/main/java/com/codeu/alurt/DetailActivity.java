package com.codeu.alurt;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class DetailActivity extends ActionBarActivity implements LocationListener {

    boolean gps_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailActivityFragment())
                    .commit();
        }

        curLocation = getLastKnownLocation();

    }

    Location curLocation = new Location("");


    public Location getLastKnownLocation(){
        LocationManager mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);

        gps_enabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(gps_enabled) Toast.makeText(this, "location not null", Toast.LENGTH_SHORT).show();

        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
            Log.d("Provider", provider);
            Log.d("Accuracy", Float.toString(l.getAccuracy()));
        }

        return bestLocation;
    }


    @Override
    public void onLocationChanged(Location loc) {
        Log.d("Updated location", loc.toString());
        curLocation = loc;
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}


    public void panicAlert(View view){
        startActivity(DetailActivityFragment.createShareAlertIntent(curLocation));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
