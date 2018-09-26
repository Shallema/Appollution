package com.example.bauwensn.appollutiontest.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bauwensn.appollutiontest.R;
import com.example.bauwensn.appollutiontest.models.api.PollutionInfo;
import com.example.bauwensn.appollutiontest.tools.JSonConverter;
import com.example.bauwensn.appollutiontest.webapi.RequestAPI;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment implements  RequestAPI.IRequestEvent  {

    private View vue;
    private Context context;

    private ListView resultsLV;

    private RequestAPI requestAPI;


    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vue = inflater.inflate(R.layout.fragment_result, container, false);

        resultsLV = vue.findViewById(R.id.lv_results);

        return vue;
    }

    public void displayResult() {
        requestAPI = new RequestAPI();
        requestAPI.setCallback(this);
        requestAPI.execute();
    }

    @Override
    public void onRequestResult(JSONObject data) {

        Log.i("EVENT", "Finish");

        //Use converter
        List<PollutionInfo> pollutionInfos = JSonConverter.ToPollutionInfos(data);

        List<HashMap<String, String>> adapterSource = new ArrayList<>();
        for (PollutionInfo pollutionData : pollutionInfos) {
            HashMap<String, String> element = new HashMap<>();
            element.put("co2", pollutionData.getDioxCarb() + "");
            element.put("TVOC", pollutionData.getTvoc() +"");
            adapterSource.add(element);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this.getContext(),
                adapterSource,
                android.R.layout.simple_list_item_2,
                new String[]{"co2", "TVOC"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        resultsLV.setAdapter(adapter);
    }



}
