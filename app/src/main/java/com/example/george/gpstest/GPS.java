package com.example.george.gpstest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class GPS extends Activity implements LocationListener {
    public TextView out, save, bear;
    public LocationManager loc;
    public String provider;
    public Criteria c = new Criteria();
    public boolean isNet = false;
    public Location res, saved;
    public Context co =this;

    public void AlertSettings(View v){
        new AlertDialog.Builder(co)
                .setTitle("GPS Disabled")
                .setMessage("GPS is currently disabled. Would you like to enable GPS?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        co.startActivity(myIntent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        out = (TextView) findViewById(R.id.textView);
        save = (TextView) findViewById(R.id.textView2);
        bear = (TextView) findViewById(R.id.textView3);
        loc = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_g, menu);
        return true;
    }

    public void onButtonClick(View v)throws SecurityException{
        if (!loc.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertSettings(v);
        }
            else{
                c.setBearingAccuracy(Criteria.ACCURACY_HIGH);
                provider = LocationManager.GPS_PROVIDER;
                loc.requestLocationUpdates(provider, 1000, 0, this, null);
                res = loc.getLastKnownLocation(provider);
                if (res != null) {
                    onLocationChanged(res);
                } else if (!isNet) {
                    out.setText("Attempting to obtain fix");
                }
            }
    }

    public void onSaveClick(View v) throws SecurityException{
        res = loc.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (res != null){
            saved = res;
            save.setText("Saved location: " + saved.getLatitude() + " " + saved.getLongitude());
        }
        else{
            out.setText("Location not found");
        }
        onLocationChanged(res);
    }

    public void onStopClick(View v) throws SecurityException{
      loc.removeUpdates(this);
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
        if(location != null) {
            out.setText(location.getLatitude() + " " + location.getLongitude());
        }
        if (saved != null){
            bear.setText("Distance: " + location.distanceTo(saved) + " Bearing: " + location.bearingTo(saved));
        }
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
