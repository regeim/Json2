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
public class ProcessJSON2{


    InputStream inputStream;


    public ProcessJSON2 (InputStream inputStream){
        this.inputStream=inputStream;
    }




    public ArrayList<Item> createFromJSON2() throws IOException {
        String temp;

        String temp_integer;
        ArrayList<Item> list = new ArrayList<Item>();
        Item temp_Item=new Item();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        JsonReader jsonReader = null;
        inputStreamReader = new InputStreamReader(this.inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        jsonReader = new JsonReader(inputStreamReader);
        String char_name;

         jsonReader.beginObject();
                while( jsonReader.hasNext() ){
                    final String name = jsonReader.nextName();

                    if( name.equals( "auctions" ) && jsonReader.peek()!= JsonToken.NULL ) {

                        jsonReader.beginObject();
                        while( jsonReader.hasNext() ) {

                            final String innerName = jsonReader.nextName();
                            if( innerName.equals( "auctions" )&& jsonReader.peek()!= JsonToken.NULL) {

                                jsonReader.beginArray();
                                while( jsonReader.hasNext() ) {
                                    jsonReader.beginObject();
                                    while( jsonReader.hasNext() ) {
                                        final String innerInnerName = jsonReader.nextName();
                                        if( innerInnerName.equals( "item" )&& jsonReader.peek()!= JsonToken.NULL) {

                                            temp_integer=(jsonReader.nextString());
                                            jsonReader.nextName();
                                            char_name=(jsonReader.nextString());
                                            if (char_name.equals("Alchriz")){
                                                temp_Item.setAuction_number(temp_integer);
                                                temp_Item.setOwner(char_name);
                                                list.add(temp_Item);

                                            }
                                        }
                                        else {
                                            jsonReader.skipValue();
                                        }

                                    }jsonReader.endObject();

                                }jsonReader.endArray();
                            }
                            else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();

                    }
                    else
                        jsonReader.skipValue();
                }
                jsonReader.endObject();


        return list;}
}





