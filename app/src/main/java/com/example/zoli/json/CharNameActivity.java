package com.example.zoli.json;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Zoli on 2015.03.03..
 */
public class CharNameActivity extends Activity {
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_name_activity);
    }

    public void button_pressed(View v){
        switch (v.getId()){
            case R.id.char_name_back_button:
                Intent intent_back=new Intent(this, MainActivity.class);
                startActivity(intent_back);
            case R.id.char_name_next_button:
                Intent intent_next=new Intent(this, ItemListActivity.class);
                startActivity(intent_next);
        }

    }
}
