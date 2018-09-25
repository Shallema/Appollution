package com.example.bauwensn.appollutiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bauwensn.appollutiontest.Fragments.SearchFragment;
import com.example.bauwensn.appollutiontest.models.api.PollutionInfo;
import com.example.bauwensn.appollutiontest.webapi.RequestAPI;
import com.xw.repo.BubbleSeekBar;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements RequestAPI.IRequestEvent {

    private SeekBar seekbar;
    private TextView progressTextView, bubbleTV;
    private BubbleSeekBar bubbleSeekBar;

    private RequestAPI requestAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();
        //seekBarListener();
        bubbleSeekbarListener();
        launchFragments();

    }

    public void initializeElements() {

        bubbleSeekBar = findViewById(R.id.bubble_seekbar);

        bubbleTV = findViewById(R.id.bubble_tv);

    }

    public void seekBarListener() {
        //getting the progress value
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                progressTextView.setText("Distance: " + progressChangedValue + "km");
                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
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

    public void displayResult() {
        requestAPI = new RequestAPI();
        requestAPI.setCallback(this);
        requestAPI.execute();
    }

    @Override
    public void onRequestResult(JSONObject data) {

        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
    }

    private void launchFragments() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, new SearchFragment()).commit();

    }
}
