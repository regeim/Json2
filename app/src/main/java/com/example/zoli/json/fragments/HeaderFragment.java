package com.example.zoli.json.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zoli.json.R;

/**
 * Created by Zoli on 2015.03.05..
 */
public class HeaderFragment extends Fragment{
    private String chosen_realm;
    private String name_of_char;

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

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }

}
