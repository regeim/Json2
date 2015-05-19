package com.example.zoli.json.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zoli.json.Item;
import com.example.zoli.json.MyService;
import com.example.zoli.json.R;

import java.util.ArrayList;

/**
 * Created by Zoli on 2015.03.05..
 */
public class HeaderFragment extends Fragment implements View.OnClickListener {
    private String chosen_realm;
    private String name_of_char;
    private ArrayList<Item> item_list;
    private String toast;
    private static final String TAG = "ServicesDemo";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        chosen_realm= args.getString("realm");
        name_of_char= args.getString("name");

        View view=inflater.inflate(R.layout.header, container, false);
        if (chosen_realm!=null) {
        TextView myTextView = (TextView)view.findViewById(R.id.header_left_text);
        myTextView.setText(chosen_realm);}
        if (name_of_char!=null){
        TextView myTextViewRight = (TextView)view.findViewById(R.id.header_right_text);
        myTextViewRight.setText(name_of_char);}

        Button buttonStart = (Button)view.findViewById(R.id.start_button);
        Button buttonStop = (Button) view.findViewById(R.id.stop_button);
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_button:
                Log.d(TAG, "onClick: starting service");

                getActivity().startService(new Intent(getActivity(), MyService.class));
                break;
            case R.id.stop_button:
                Log.d(TAG, "onClick: stopping service");

                getActivity().stopService(new Intent(getActivity(), MyService.class));
                break;
        }
    }

    public void setItemList (ArrayList<Item> itemArrayList){
        if (item_list!=null) {
            item_list = itemArrayList;
            toast = item_list.get(0).getOwner();
            Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
        }
    }
}
