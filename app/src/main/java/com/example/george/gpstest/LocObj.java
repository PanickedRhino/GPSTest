package com.example.george.gpstest;

import android.location.Location;

/**
 * Created by Student-Admin on 10/14/2015.
 */
public class LocObj {
    Location loc;
    double lat;
    double longt;

    public LocObj(Location l){
        lat = l.getLatitude();
        longt = l.getLongitude();
    }

    public double getLat(){
        return lat;
    }

    public double getLongt(){
        return longt;
    }

    public void setLat(double l){
        lat = l;
    }

    public void setLongt(double l){
        longt = l;
    }

}
