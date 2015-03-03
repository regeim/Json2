package com.example.zoli.json;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private HttpConnection main_Http;
    String selected_item;
    String url="http://eu.battle.net/api/wow/realm/status";
    String holder;
    ProcessJSON main_json;
    ArrayList<String> realm_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //to have internet access on Android  4.2+
        if (android.os.Build.VERSION.SDK_INT > 9) {
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
                    spinner_item_selected(view,region_spinner);
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
//        switch (v.getId()){
//            case R.id.region_spinner:
        selected_item=sp.getSelectedItem().toString();
        try {
            main_Http=new HttpConnection(url,getApplicationContext());
            main_Http.OpenStream();
            main_Http.getBytes();

            if (main_Http.getBuf()!=null){
                holder = new String(main_Http.getBuf(),"UTF-8");
                main_json=new ProcessJSON(holder);
                realm_list=main_json.getWorld_list();
            }
            main_Http.CloseStream();

        }catch (IOException e) {
            e.printStackTrace();
        }
        final Spinner realm_spinner=(Spinner)findViewById(R.id.realm_spinner);
        if (realm_list!=null){
            ArrayAdapter<String> realm_adapter=new ArrayAdapter(this,R.layout.spinner_layout,realm_list);
            realm_spinner.setAdapter(realm_adapter);
        }
//                break;
//        }

    }
//    public void button_pressed (View v) throws JSONException, IOException {
//
//   }
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
