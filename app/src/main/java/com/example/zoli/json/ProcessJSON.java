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


    public ProcessJSON (String s,Integer integer){
        this.data_holder=s;
        this.switch_case=integer;
//        this.LoadInJSON();

    }

    public ProcessJSON (InputStream inputStream){
        this.inputStream=inputStream;
    }

    public ArrayList<String> getWorld_list() {
        return world_list;
    }

    public String getUrl() {
        return url;
    }

//    public List readJsonStream() throws IOException {
//        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
//        try {
//
//            return readMessagesArray(reader);}
//            finally {
//                reader.close();
//            }
//        }
//
//    public List readMessagesArray(JsonReader reader) throws IOException {
//        List messages = new ArrayList();
//
//        reader.beginArray();
//        while (reader.hasNext()) {
//            messages.add(readMessage(reader));
//        }
//        reader.endArray();
//        return messages;
//    }
//
//    public Boolean readMessage(JsonReader reader) throws IOException {
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String name = reader.nextName();
//            if (name.equals("realms")) {
//               good=true;
//            }  else {
//               good=false;
//            }
//        }
//
//        return good;
//    }



    public List createFromJSON() throws IOException {
        String temp2;
        List realm_names = new ArrayList();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        JsonReader jsonReader = null;

            inputStreamReader = new InputStreamReader(this.inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            jsonReader = new JsonReader(inputStreamReader);



//        jsonReader.beginObject();
//        while( jsonReader.hasNext() ){
//            final String name = jsonReader.nextName();
//
//            if( name.equals( "parse" ) && jsonReader.peek()!= JsonToken.NULL ) {
//                jsonReader.beginObject();
//                while( jsonReader.hasNext() ) {
//                    final String innerName = jsonReader.nextName();
//
//                    if( innerName.equals( "links" ) && jsonReader.peek()!= JsonToken.NULL) {
//                        jsonReader.beginArray();
//                        while( jsonReader.hasNext() ) {
//                            jsonReader.beginObject();
//                            while( jsonReader.hasNext() ) {
//                                final String innerInnerName = jsonReader.nextName();
//
//                                if( innerInnerName.equals( "*" )&& jsonReader.peek()!= JsonToken.NULL) {
//                                    temp=(jsonReader.nextString());
//
//
//                                }
//                                else {
//                                    jsonReader.skipValue();
//                                }
//                            }
//                            jsonReader.endObject();
//                        }
//                        jsonReader.endArray();
//                    }
//                    else jsonReader.skipValue();
//                }
//                jsonReader.endObject();
//            }
//            else
//            jsonReader.skipValue();
//        }
//        jsonReader.endObject();
//    }
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



//    private void LoadInJSON () {
//        switch (switch_case){
//            case 1:
//            world_list=new ArrayList<String>();
//            try {
//                root=new JSONObject(data_holder);
//                root_array=root.getJSONArray("realms");
//                for (int i = 0; i < root_array.length(); i++) {
//                    root=root_array.getJSONObject(i);
//                    temp=root.getString("name");
//                    world_list.add(temp);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            case 2:
//                try {
//                    root=new JSONObject(data_holder);
//                    root_array=root.getJSONArray("files");
//                    url=root_array.getJSONObject(0).getString("url");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            case 3:
//                item_list=new ArrayList<String>();
//                try {
//                    root=new JSONObject(data_holder);
//                    root_array=root.getJSONArray("auctions");
//                    for (int i = 0; i < root_array.length(); i++) {
//                        root=root_array.getJSONObject(i);
//                        temp=root.getString("auc");
//                        item_list.add(temp);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//        }
//
//
//    }


}
