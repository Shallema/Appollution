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
    protected JSONObject doInBackground(String... url) {
        if(url.length != 1) {
            return  null;
        }
        //region --Connexion à l'API
        Connection conec = new Connection();
        String requestURL = url[0];
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

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject data) {
        if (callback != null) {
            callback.onRequestResult(data);
        }
    }
}
