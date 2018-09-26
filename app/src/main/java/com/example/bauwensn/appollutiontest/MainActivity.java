package com.example.bauwensn.appollutiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bauwensn.appollutiontest.Fragments.ResultFragment;
import com.example.bauwensn.appollutiontest.models.api.PollutionInfo;
import com.example.bauwensn.appollutiontest.tools.JSonConverter;
import com.example.bauwensn.appollutiontest.webapi.RequestAPI;
import com.xw.repo.BubbleSeekBar;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RequestAPI.IRequestEvent {

    private TextView progressTextView, bubbleTV;
    private BubbleSeekBar bubbleSeekBar;
    private Button buttonSearch;
    private CheckBox ckIsPark, ckIsPromenade, ckIsRunning;

    private RequestAPI requestAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();
        bubbleSeekbarListener();

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    public void initializeElements() {

        bubbleSeekBar = findViewById(R.id.bubble_seekbar);
        bubbleTV = findViewById(R.id.bubble_tv);
        buttonSearch = findViewById(R.id.search_btn);
    }

    public void bubbleSeekbarListener() {
        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                //bubbleTV.setText(String.format("On change %d", progress));
            }

            @Override
            public void getProgressOnActionUp(int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(int progress, float progressFloat) {

            }
        });
    }

    public void sendRequest() {
        //TODO GPS
        double lon = 50.798207;
        double lat = 4.330302;

        String url_base = "https://wt-33346583fc23566bc0b165c1fe714805-0.sandbox.auth0-extend.com/pollution";

        String url = url_base + "?long=" + lon + "&lat=" + lat;

        int kmChoice = bubbleSeekBar.getProgress() * 1000;
        url += "&range=" + kmChoice;

        ckIsPark = findViewById(R.id.parc_cb);
        if(ckIsPark.isChecked()) {
            url += "&isPark=true";
        }

        ckIsPromenade = findViewById(R.id.promenade_cb);
        if(ckIsPromenade.isChecked()) {
            url += "&isPromenade=true";
        }

        ckIsRunning = findViewById(R.id.running_cb);
        if(ckIsRunning.isChecked()) {
            url += "&isRunning=true";
        }

        requestAPI = new RequestAPI();
        requestAPI.setCallback(this);
        requestAPI.execute(url);
    }

    @Override
    public void onRequestResult(JSONObject data) {

        //Use converter
        List<PollutionInfo> pollutionInfos = JSonConverter.ToPollutionInfos(data);
        ResultFragment frag = new ResultFragment();
        frag.setResult(pollutionInfos);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, frag).commit();
        //Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
    }

}