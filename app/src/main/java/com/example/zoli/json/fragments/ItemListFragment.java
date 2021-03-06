package com.example.zoli.json.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.devspark.progressfragment.ProgressFragment;
import com.example.zoli.json.HttpConnection;
import com.example.zoli.json.Item;
import com.example.zoli.json.ItemListActivity;
import com.example.zoli.json.ProcessJSON;
import com.example.zoli.json.ProcessJSON2;
import com.example.zoli.json.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoli on 2015.03.05..
 */
public class ItemListFragment extends Fragment {

    private String url="http://eu.battle.net/api/wow/auction/data/outland";
    private String realm_auction_url;
    private ProcessJSON main_json;
    private ProcessJSON2 main_json2;
    private HttpConnection main_Http;
    private String holder;
    private List<String> auction_url;
    private ArrayList<Item> item_list;
    private byte[] array_holder;
    private String array_length;
    private Spinner item_list_spinner;
    private ListView item_list_view;
    private Button setData_btn;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_list_body, container, false);

        setData_btn=(Button)view.findViewById(R.id.button);

        setData_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                setData();
            }
        });

        item_list_view = (ListView)view.findViewById(R.id.item_list_view);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setData (){
        InputStream inputStream;

        main_Http=new HttpConnection(url,getActivity());
        main_Http.OpenStream();


        if (main_Http.getStream()!=null){

            inputStream=main_Http.getStream();
            main_json=new ProcessJSON (inputStream);
            try {
                auction_url=main_json.createFromJSON("url");

            } catch (IOException e) {
                e.printStackTrace();
            }
            main_Http.CloseStream();

        }
        main_Http=new HttpConnection(auction_url.get(0).toString(),getActivity());
        main_Http.OpenStream();

        if (main_Http.getStream()!=null){
            inputStream=main_Http.getStream();
            main_json2=new ProcessJSON2(inputStream);
            try {
                item_list=main_json2.createFromJSON2();


            } catch (IOException e) {
                e.printStackTrace();
            }
            main_Http.CloseStream();


            if (item_list!=null){
                ArrayAdapter item_list_adapter = new ArrayAdapter(getActivity(),R.layout.spinner_layout,item_list);
                ArrayAdapter item_list_view_adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,item_list);
                item_list_adapter.setDropDownViewResource(R.layout.spinner_layout);

                item_list_view.setAdapter(item_list_view_adapter);
            }

        }


    }
}