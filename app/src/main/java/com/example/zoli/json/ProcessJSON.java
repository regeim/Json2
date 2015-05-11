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


    InputStream inputStream;


    public ProcessJSON (InputStream inputStream){
        this.inputStream=inputStream;
    }




    public List createFromJSON(String s) throws IOException {
        String temp;

        String temp_integer;
        ArrayList list = new ArrayList ();

        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        JsonReader jsonReader = null;
        inputStreamReader = new InputStreamReader(this.inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        jsonReader = new JsonReader(inputStreamReader);
        jsonReader.setLenient(true);
        String char_name;

        switch (s){

        case "realm":
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

                                temp=(jsonReader.nextString());
                                list.add(temp);
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
            break;
            case "url":
                jsonReader.beginObject();
                while( jsonReader.hasNext() ){
                    final String name = jsonReader.nextName();

                    if( name.equals( "files" ) && jsonReader.peek()!= JsonToken.NULL ) {

                        jsonReader.beginArray();
                        while( jsonReader.hasNext() ) {

                            jsonReader.beginObject();
                            while( jsonReader.hasNext() ) {

                                final String innerInnerName = jsonReader.nextName();
                                if( innerInnerName.equals( "url" )&& jsonReader.peek()!= JsonToken.NULL) {

                                    temp=(jsonReader.nextString());
                                    list.add(temp);
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
                break;



        }
        return list;
    }




}
