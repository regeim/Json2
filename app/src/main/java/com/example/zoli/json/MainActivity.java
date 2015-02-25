package com.example.zoli.json;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.client.HttpClient;


public class MainActivity extends ActionBarActivity {
    private HttpConnection mainHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button get_btn=(Button)findViewById(R.id.get_button_id);

    }

    public void buttonpressed (View v){
        String url="http://api.openweathermap.org/data/2.5/weather?q=Budapest&mode=xml";
//        Toast.makeText(getApplicationContext(), "hello world",Toast.LENGTH_SHORT).show();
        mainHttp=new HttpConnection(url,getApplicationContext());
        mainHttp.OpenStream();
        mainHttp.CloseStream();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
