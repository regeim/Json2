package com.example.zoli.json;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zoli on 2015.03.03..
 */
public class ItemListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_activity);


    }

//    public static class Header extends Fragment{
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View view=inflater.inflate(R.layout.header, container, false);
//            return view;
//        }
//    }
//    public static class ItemList extends Fragment{
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View view=inflater.inflate(R.layout.item_list_body, container, false);
//            return view;
//        }
//    }
}
