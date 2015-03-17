package com.example.zoli.json.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.devspark.progressfragment.ProgressFragment;
import com.example.zoli.json.HttpConnection;
import com.example.zoli.json.ItemListActivity;
import com.example.zoli.json.ProcessJSON;
import com.example.zoli.json.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Zoli on 2015.03.05..
 */
public class ItemListFragment extends Fragment {

    private String url="http://eu.battle.net/api/wow/auction/data/outland";
    private String realm_auction_url;
    private ProcessJSON main_json;
    private HttpConnection main_Http;
    private String holder;
    private List<String> auction_url;
    private List item_list;
    private byte[] array_holder;
    private String array_length;
    private Spinner item_list_spinner;
    private ListView item_list_view;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_list_body, container, false);
        item_list_spinner = (Spinner)view.findViewById(R.id.item_list_spinner);
        item_list_view = (ListView)view.findViewById(R.id.item_list_view);
        if (item_list!=null){
            ArrayAdapter item_list_adapter = new ArrayAdapter(getActivity(),R.layout.spinner_layout,item_list);
            ArrayAdapter item_list_view_adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,item_list);
            item_list_adapter.setDropDownViewResource(R.layout.spinner_layout);
            item_list_spinner.setAdapter(item_list_adapter);
            item_list_view.setAdapter(item_list_view_adapter);
        }
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);








    }
}
