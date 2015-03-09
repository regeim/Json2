package com.example.zoli.json;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Message;

import com.google.gson.stream.JsonToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.stream.JsonReader;

/**
 * Created by Zoli on 2015.02.25..
 */
public class ProcessJSON{

    private String data_holder;
    private ArrayList<String> world_list;
    private ArrayList<String> item_list;
    private JSONObject root;
    private JSONArray root_array;
    private int switch_case;
    private String temp;
    private String url;
    boolean good;
    InputStream inputStream;


    public ProcessJSON (InputStream inputStream){
        this.inputStream=inputStream;
    }

    public ArrayList<String> getWorld_list() {
        return world_list;
    }

    public String getUrl() {
        return url;
    }




    public List createFromJSON() throws IOException {
        String temp2;
        List realm_names = new ArrayList();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        JsonReader jsonReader = null;

            inputStreamReader = new InputStreamReader(this.inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            jsonReader = new JsonReader(inputStreamReader);



        jsonReader.beginObject();
        while( jsonReader.hasNext() ){
            final String name = jsonReader.nextName();

            if( name.equals( "realms" ) && jsonReader.peek()!= JsonToken.NULL ) {

                jsonReader.beginArray();
                while( jsonReader.hasNext() ) {

                    jsonReader.beginObject();
                    while( jsonReader.hasNext() ) {

                        final String innerInnerName = jsonReader.nextName();
                        if( innerInnerName.equals( "name" )&& jsonReader.peek()!= JsonToken.NULL) {

                            temp2=(jsonReader.nextString());
                            realm_names.add(temp2);
                        }
                        else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                }
                jsonReader.endArray();
            }
            else
                jsonReader.skipValue();
        }
        jsonReader.endObject();
        return realm_names;
    }




}
