package com.example.bauwensn.appollutiontest.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bauwensn.appollutiontest.R;
import com.example.bauwensn.appollutiontest.models.api.PollutionInfo;

import java.util.List;

public class PollutionAdapter extends ArrayAdapter<PollutionInfo> {

    private TextView tvTitle, tvItemOne, tvItemTwo;

    public PollutionAdapter(@NonNull Context context, @NonNull List<PollutionInfo> objects) {
        super(context,0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.item_list_result, parent, false);

        PollutionInfo element = getItem(position);

        tvTitle = v.findViewById(R.id.item_result_name);
        tvTitle.setText(element.getDeviceID());

        tvItemOne = v.findViewById(R.id.item_result_co);
        tvItemOne.setText(String.valueOf(element.getDioxCarb()));

        tvItemTwo = v.findViewById(R.id.item_result_tvoc);
        tvItemTwo.setText(String.valueOf(element.getTvoc()));

        if (element.getDioxCarb() > 400) {
            tvTitle.setTextColor(this.getContext().getColor(R.color.red));
            tvItemOne.setTypeface(null, Typeface.BOLD);
        } else {
            tvTitle.setTextColor(this.getContext().getColor(R.color.green));
        }

        return v;
    }
}
