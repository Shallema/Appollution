package com.example.bauwensn.appollutiontest.models.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class PollutionInfo {

    private String deviceID;
    private double longitude, latitude;
    private int dioxCarb, tvoc;
    private Date date;

    //region Getters & Setters

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getDioxCarb() {
        return dioxCarb;
    }

    public void setDioxCarb(int dioxCarb) {
        this.dioxCarb = dioxCarb;
    }

    public int getTvoc() {
        return tvoc;
    }

    public void setTvoc(int tvoc) {
        this.tvoc = tvoc;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //endregion

    //region Ctor



    public PollutionInfo(double longitude, double latitude, int dioxCarb, int tvoc, String deviceID, Date date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.dioxCarb = dioxCarb;
        this.tvoc = tvoc;
        this.deviceID = deviceID;
        this.date = date;
    }

    //endregion


}
