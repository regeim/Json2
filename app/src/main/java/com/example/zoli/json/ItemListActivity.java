package com.example.zoli.json;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Zoli on 2015.03.03..
 */
public class ItemListActivity extends FragmentActivity {
    private String chosen_realm;
    private String name_of_char;
    private String url="http://eu.battle.net/api/wow/auction/data/outland";
    private ProcessJSON main_json;
    private HttpConnection main_Http;
    private String holder;
    private String auction_url;
    private byte[] array_holder;
    private String array_length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_activity);

        Intent intent=getIntent();
        chosen_realm=intent.getStringExtra("realm");
        name_of_char=intent.getStringExtra("name");
//
//        try {
//            main_Http=new HttpConnection(url,getApplicationContext());
//            main_Http.OpenStream();
//            main_Http.getBytes();
//
//            if (main_Http.getBuf()!=null){
//                holder = new String(main_Http.getBuf(),"UTF-8");
//                main_json=new ProcessJSON(holder,2);
//                auction_url=main_json.getUrl();
//
//            }
//            main_Http.CloseStream();
//
//
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (auction_url!=null){
//        try {
//            main_Http=new HttpConnection(auction_url,getApplicationContext());
//            main_Http.OpenStream();
//            main_Http.getBytes();
//
//            if (main_Http.getBuf()!=null){
//                array_holder=main_Http.getBuf();
//                array_length=Integer.toString(array_holder.length);
//
//
//            }
//            main_Http.CloseStream();
//
//
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        }
//
//
//        Toast.makeText(this.getApplicationContext(),array_length, Toast.LENGTH_LONG).show();


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
