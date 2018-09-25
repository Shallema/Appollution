package com.example.bauwensn.appollutiontest.models.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class PollutionInfo {

    private String message;
    private double longitude, latitude;
    private int dioxCarb, tvoc, nbResult, deviceID;
    private Date date;

    //region Getters & Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public int getNbResult() {
        return nbResult;
    }

    public void setNbResult(int nbResult) {
        this.nbResult = nbResult;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
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



    public PollutionInfo(String message, double longitude, double latitude, int dioxCarb, int tvoc, int nbResult, int deviceID, Date date) {
        this.message = message;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dioxCarb = dioxCarb;
        this.tvoc = tvoc;
        this.nbResult = nbResult;
        this.deviceID = deviceID;
        this.date = date;
    }

    //endregion

    //region Parsing JSON

    public PollutionInfo(JSONObject json) throws JSONException {
        JSONArray results = json.getJSONArray("results");
        JSONObject one = results.getJSONObject(0);
        this.deviceID = one.getInt("deviceId");

        this.date = new Date(one.getLong("date") * 1000L);

        this.dioxCarb = one.getInt("co2");
        this.tvoc = one.getInt("TVOC");

        this.latitude = one.getDouble("lat");
        this.longitude = one.getDouble("long");
    }

    //endregion
}
