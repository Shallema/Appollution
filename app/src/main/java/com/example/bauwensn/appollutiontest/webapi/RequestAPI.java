package com.example.bauwensn.appollutiontest.webapi;

import android.os.AsyncTask;

import com.example.bauwensn.appollutiontest.models.api.Connection;
import com.example.bauwensn.appollutiontest.models.api.PollutionInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class RequestAPI extends AsyncTask<String, Void, JSONObject> {

    //region --Callback

    public interface IRequestEvent {
        void onRequestResult(JSONObject data);
    }

    private IRequestEvent callback;

    public void setCallback(IRequestEvent callback) {
        this.callback = callback;
    }

    //endregion



    @Override
    protected JSONObject doInBackground(String... params) {

        final String URL_BASE = "https://wt-33346583fc23566bc0b165c1fe714805-0.sandbox.auth0-extend.com/pollution"; //uccle
        final String URL;

        //region --Connexion à l'API
        Connection conec = new Connection();
        String requestURL = URL_BASE;
        String requestResult = conec.connection(requestURL);

        JSONObject json = null;

        if (requestResult != null) {


            try {
                json = new JSONObject(requestResult);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //endregion

        for (int i = 0; i < 15; i++) {

            JSONArray results = json.getJSONArray("results");
            JSONObject one = results.getJSONObject(0);
            int deviceID = one.getInt("deviceId");

            Date date = new Date(one.getLong("date") * 1000L);

            int dioxCarb = one.getInt("co2");
            int tvoc = one.getInt("TVOC");

            Double latitude = one.getDouble("lat");
            Double longitude = one.getDouble("long");
        }

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject data) {
        if (callback != null) {
            callback.onRequestResult(data);
        }
    }
}
