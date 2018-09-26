package com.example.bauwensn.appollutiontest.tools;

import com.example.bauwensn.appollutiontest.models.api.PollutionInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class JSonConverter {

    //region Parsing JSON

    public static List<PollutionInfo> ToPollutionInfos(JSONObject json) {
        //TODO Gerer la liste complete
        ArrayList<PollutionInfo> pollutionInfos = new ArrayList<>();

        try {
            JSONArray results = json.getJSONArray("results");

            JSONObject one = results.getJSONObject(0);
            String deviceID = one.getString("deviceId");

            Date date = new Date(one.getLong("date") * 1000L);

            int dioxCarb = one.getInt("co2");
            int tvoc = one.getInt("TVOC");

            double latitude = one.getDouble("lat");
            double longitude = one.getDouble("long");

            PollutionInfo pollutionInfo = new PollutionInfo(
                    longitude,
                    latitude,
                    dioxCarb,
                    tvoc,
                    deviceID,
                    date);
            pollutionInfos.add(pollutionInfo);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pollutionInfos;
    }

    //endregion
}
