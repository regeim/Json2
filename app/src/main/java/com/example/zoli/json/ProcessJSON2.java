package com.example.zoli.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Zoli on 2015.02.25..
 */
public class ProcessJSON2{


    InputStream inputStream;


    public ProcessJSON2 (InputStream inputStream){
        this.inputStream=inputStream;
    }


    /*public ArrayList<Item> createFromJSON2() throws IOException {
        return null;
    }
*/
    public ArrayList<Item> createFromJSON2() throws IOException {
        String temp;

        String temp_item_number;
        String temp_id;
        ArrayList<Item> list = new ArrayList<Item>();
        Item temp_Item;
        InputStreamReader inputStreamReader = null;

        JsonReader jsonReader = null;
        inputStreamReader = new InputStreamReader(this.inputStream);

        jsonReader = new JsonReader(inputStreamReader);
       /* StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        String theString = writer.toString();*/

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
                                        if (innerInnerName.equals("auc")&& jsonReader.peek()!= JsonToken.NULL){
                                            temp_id=(jsonReader.nextString());
                                            jsonReader.nextName();
                                            temp_item_number=(jsonReader.nextString());
                                            jsonReader.nextName();
                                            char_name=(jsonReader.nextString());
                                            if (char_name.equals("Mahuizoh")){
                                                temp_Item=new Item();
                                                temp_Item.setId(temp_id);
                                                temp_Item.setAuction_number(temp_item_number);
                                                temp_Item.setOwner(char_name);
                                                list.add(temp_Item);

                                            }


                                        } else {
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





