package com.example.zoli.json;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zoli on 2015.02.25..
 */
public class HttpConnection{
String url;
InputStream stream;
Context context;
boolean isconnect;

    public HttpConnection (String url, Context context){
        this.url=url;
        this.context=context;
        this.isconnect=false;
    }
public void OpenStream (){

        try {
        URL url = new URL(this.url);
        HttpURLConnection conn = (HttpURLConnection)
                url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream ostream = conn.getInputStream();
        this.stream=ostream;
        this.isconnect=true;
        Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public void CloseStream() {
if (isconnect) {
    try {
        this.stream.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}else{
    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
}
}
}
