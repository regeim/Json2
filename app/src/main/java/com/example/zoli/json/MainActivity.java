package com.example.zoli.json;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class MainActivity extends ActionBarActivity {
    private HttpConnection mainHttp;
    String selected_item;
    String url="http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    String holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        Button get_btn=(Button)findViewById(R.id.get_button_id);

        mainHttp=new HttpConnection(url,getApplicationContext());
        mainHttp.OpenStream();
        try {
            mainHttp.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mainHttp.getBuf()!=null){
            try {
                holder = new String(mainHttp.getBuf(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
                try {
            JSONObject root=new JSONObject(holder);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        mainHttp.CloseStream();


        final Spinner spinner = (Spinner) findViewById(R.id.realm_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.regions,R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);
        spinner.setSelection(4);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    spinner_item_selected(view,spinner);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void spinner_item_selected (View v, Spinner sp)throws JSONException, IOException{
        switch (v.getId()){
            case R.id.realm_spinner:
                selected_item=sp.getSelectedItem().toString();

                break;
        }

    }
//    public void button_pressed (View v) throws JSONException, IOException {
//
//    }
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
