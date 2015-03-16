package com.example.zoli.json;


import android.content.Intent;
import android.os.StrictMode;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private HttpConnection main_Http;
    private String selected_item;
//    private String url="http://en.wikipedia.org/w/api.php?action=parse&page=Mango&format=json&prop=links";
    private String url="http://eu.battle.net/api/wow/realm/status";
    private String holder;
    private ProcessJSON main_json;
    private List realm_list;
    private String chosen_realm;
    InputStream inputStream;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) { //to have internet access on Android  4.2+
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

//        Button get_btn=(Button)findViewById(R.id.get_button_id);

        final Spinner region_spinner = (Spinner) findViewById(R.id.region_spinner);
        ArrayAdapter<CharSequence> region_adapter = ArrayAdapter.createFromResource(this,R.array.regions,R.layout.spinner_layout);
        region_adapter.setDropDownViewResource(R.layout.spinner_layout);
        region_spinner.setAdapter(region_adapter);
        region_spinner.setSelection(4);

        region_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                   spinner_item_selected(view,region_spinner,"region_spinner");
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


    public void spinner_item_selected (View v, Spinner sp, String s)throws JSONException, IOException{
        switch (s){
        case "region_spinner":
            selected_item=sp.getSelectedItem().toString();

                    main_Http=new HttpConnection(url,getApplicationContext());
                    main_Http.OpenStream();


                    if (main_Http.getStream()!=null){

                       inputStream=main_Http.getStream();
                        main_json=new ProcessJSON (inputStream);
                        realm_list=main_json.createFromJSON("realm");

                    }
                    main_Http.CloseStream();


                final Spinner realm_spinner=(Spinner)findViewById(R.id.realm_spinner);
                if (realm_list!=null){
                    ArrayAdapter<String> realm_adapter=new ArrayAdapter(this,R.layout.spinner_layout,realm_list);
                    realm_spinner.setAdapter(realm_adapter);
                    realm_spinner.setSelection(0);
                    realm_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                spinner_item_selected(view,realm_spinner,"realm_spinner");
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
                break;
        case "realm_spinner":
            chosen_realm=sp.getSelectedItem().toString();
            break;
        }



    }
    public void button_pressed (View v) throws JSONException, IOException {
        switch (v.getId()){
                case R.id.next_button:
                    Intent intent=new Intent(this, CharNameActivity.class);
                    intent.putExtra("realm",chosen_realm);
                    startActivity(intent);
                    break;
        }

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
