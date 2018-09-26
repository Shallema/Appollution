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
            int nbResult = json.getInt("nbrResults");

            for (int i = 0; i < nbResult; i++) {

                JSONObject pollutiondata = results.getJSONObject(i);
                String deviceID = pollutiondata.getString("deviceId");

                Date date = new Date(pollutiondata.getLong("date") * 1000L);

                int dioxCarb = pollutiondata.getInt("co2");
                int tvoc = pollutiondata.getInt("TVOC");

                double latitude = pollutiondata.getDouble("lat");
                double longitude = pollutiondata.getDouble("longi");

                PollutionInfo pollutionInfo = new PollutionInfo(
                        longitude,
                        latitude,
                        dioxCarb,
                        tvoc,
                        deviceID,
                        date);
                pollutionInfos.add(pollutionInfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pollutionInfos;
    }

    //endregion
}
