package com.example.zoli.json;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.zoli.json.fragments.HeaderFragment;
import com.example.zoli.json.fragments.ItemProgressListFragment;

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

        ItemProgressListFragment fragment1= new ItemProgressListFragment();
        fragmentTransaction.add(R.id.item_list_container,fragment1);

        fragmentTransaction.commit();






    }


}
