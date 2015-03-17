package com.example.zoli.json;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zoli.json.fragments.HeaderFragment;
import com.example.zoli.json.fragments.ItemListFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Zoli on 2015.03.03..
 */
public class ItemListActivity extends FragmentActivity {
    private String chosen_realm;
    private String name_of_char;
    private String url="http://eu.battle.net/api/wow/auction/data/outland";
    private String realm_auction_url;
    private ProcessJSON main_json;
    private HttpConnection main_Http;
    private String holder;
    private List<String> auction_url;
    private List item_list;
    private byte[] array_holder;
    private String array_length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_activity);


        Intent intent=getIntent();
        chosen_realm=intent.getStringExtra("realm");
        name_of_char=intent.getStringExtra("name");


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        HeaderFragment fragment = new HeaderFragment();
        fragmentTransaction.add(R.id.header_container, fragment);

        ItemListFragment fragment1= new ItemListFragment();
        fragmentTransaction.add(R.id.item_list_container,fragment1);

        fragmentTransaction.commit();

        InputStream inputStream;

        main_Http=new HttpConnection(url,this);
        main_Http.OpenStream();


        if (main_Http.getStream()!=null){

            inputStream=main_Http.getStream();
            main_json=new ProcessJSON (inputStream);
            try {
                auction_url=main_json.createFromJSON("url");

            } catch (IOException e) {
                e.printStackTrace();
            }
            main_Http.CloseStream();

        }
        main_Http=new HttpConnection(auction_url.get(0).toString(),this);
        main_Http.OpenStream();

        if (main_Http.getStream()!=null){
            inputStream=main_Http.getStream();
            main_json=new ProcessJSON (inputStream);
            try {
                item_list=main_json.createFromJSON("item_list");


            } catch (IOException e) {
                e.printStackTrace();
            }
            main_Http.CloseStream();


        }





    }


}
