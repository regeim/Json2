package com.example.zoli.json;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Zoli on 2015.03.03..
 */
public class CharNameActivity extends Activity {
    private String chosen_realm;
    private EditText mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_name_activity);

        Intent intent=getIntent();
        chosen_realm=intent.getStringExtra("realm");

        mText=(EditText)findViewById(R.id.name_text);



//        Toast.makeText(this.getApplicationContext(), chosen_realm, Toast.LENGTH_SHORT).show();
    }

    public void button_pressed(View v){
        switch (v.getId()){
            case R.id.char_name_back_button:
                Intent intent_back=new Intent(this, MainActivity.class);
                startActivity(intent_back);
                break;
            case R.id.char_name_next_button:
                Intent intent_next=new Intent(this, ItemListActivity.class);
                intent_next.putExtra("realm",chosen_realm);
                intent_next.putExtra("name",mText.getText().toString());
                startActivity(intent_next);

                break;
        }

    }
}
