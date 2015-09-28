package com.example.george.gpstest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//Further testing git
public class GPS extends Activity implements LocationListener {

    public TextView out;
    public LocationManager loc;
    public String provider;
    public Criteria c = new Criteria();
    public boolean isNet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_g, menu);
        return true;
    }

    public void onButtonClick(View v)throws SecurityException{
        out = (TextView) findViewById(R.id.textView);
        loc = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        c.setBearingAccuracy(Criteria.ACCURACY_HIGH);
        provider = LocationManager.GPS_PROVIDER;
        loc.requestLocationUpdates(provider, 1000, 0, this, null);
        Location res = loc.getLastKnownLocation(provider);
        if(res != null){
            onLocationChanged(res);
        }
        else if (!isNet){
            out.setText("GPS not working");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.exit(1);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onLocationChanged(Location location) {
        out.setText(location.toString() + location.getElapsedRealtimeNanos());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        out.setText(out.getText() + "\nStatus changed");
    }

    @Override
    public void onProviderEnabled(String provider) {
        out.setText(out.getText() + "\nProvider enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        out.setText(out.getText() + "\nProvider disabled");
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

}
