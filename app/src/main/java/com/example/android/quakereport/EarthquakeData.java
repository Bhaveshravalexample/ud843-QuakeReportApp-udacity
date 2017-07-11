package com.example.android.quakereport;

/**
 * Created by DIPANSH KHANDELWAL on 08-05-2017.
 */

public class EarthquakeData {

    Double scale;
    String place;
    Long date;
    String url;

    public EarthquakeData(){
    }

    public EarthquakeData(Double scale, String place,Long date, String url) {
        this.scale = scale;
        this.date = date;
        this.place = place;
        this.url = url;
    }

    public String getPlace() {
        return place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
