package com.example.zoli.json;

import android.os.AsyncTask;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Zoli on 2015.02.25..
 */
public class ProcessJSON{

    String data_holder;
    ArrayList<String> world_list;
    JSONObject root;
    JSONArray root_array;


    public ProcessJSON (String s){
        this.data_holder=s;
        this.LoadInJSON();
    }

    public ArrayList<String> getWorld_list() {
        return world_list;
    }

    private void LoadInJSON () {

            world_list=new ArrayList<String>();
            String temp;
            try {
                root=new JSONObject(data_holder);
                root_array=root.getJSONArray("realms");
                for (int i = 0; i < root_array.length(); i++) {
                    root=root_array.getJSONObject(i);
                    temp=root.getString("name");
                    world_list.add(temp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


    }


}
