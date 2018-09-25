package com.example.bauwensn.appollutiontest.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bauwensn.appollutiontest.R;
import com.example.bauwensn.appollutiontest.webapi.RequestAPI;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements RequestAPI.IRequestEvent {

    private View vue;
    private Context context;

    private Button btnSearch;

    private RequestAPI requestAPI;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vue = inflater.inflate(R.layout.fragment_search, container, false);
        context = vue.getContext();

        initializeElements();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayResult();
                switchFragment(new ResultFragment());
            }
        });

        return vue;
    }

    public void initializeElements() {
        btnSearch = vue.findViewById(R.id.search_btn);
    }

    public void displayResult() {
        requestAPI = new RequestAPI();
        requestAPI.setCallback(this);
        requestAPI.execute();
    }

    @Override
    public void onRequestResult(JSONObject data) {

        Toast.makeText(vue.getContext(), data.toString(), Toast.LENGTH_LONG).show();
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_main, fragment).addToBackStack(null).commit();
    }

}
