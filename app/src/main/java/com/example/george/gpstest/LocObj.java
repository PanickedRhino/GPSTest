package com.example.george.gpstest;

public class LocObj {
    public double lat;

    public LocObj(){

    }
    public LocObj(double lat, double longt, long time) {
        this.lat = lat;
        this.longt = longt;
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public double longt;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long time;
}
