package com.example.zoli.json.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.zoli.json.ItemListActivity;
import com.example.zoli.json.ProcessJSON;
import com.example.zoli.json.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Zoli on 2015.03.05..
 */
public class ItemListFragment extends ProgressFragment {

    private String url="http://eu.battle.net/api/wow/auction/data/outland";
    private ProcessJSON main_json;
    private HttpConnection main_Http;
    private List<String> auction_url;
    private List item_list;
    private ListView item_list_view;
    private View mContentView;
    private View progressView;


    Handler json_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (item_list!=null){
                ArrayAdapter item_list_adapter = new ArrayAdapter(getActivity(),R.layout.spinner_layout,item_list);
                ArrayAdapter item_list_view_adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,item_list);
                item_list_adapter.setDropDownViewResource(R.layout.spinner_layout);

                item_list_view.setAdapter(item_list_view_adapter);
            }
            setContentShown(true);
        }
    };



    public void setData (){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

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
                    main_json=new ProcessJSON (inputStream);
                    try {
                        item_list=main_json.createFromJSON("item_list");


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    main_Http.CloseStream();




                }
            json_handler.sendEmptyMessage(0);

            }
        };

        Thread json_thread= new Thread(runnable);
        json_thread.start();

    }

    public static ProgressFragment newInstance() {
        ProgressFragment fragment = new ProgressFragment();

        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContentView = inflater.inflate(R.layout.item_list_body, container, false);

        progressView = inflater.inflate(R.layout.fragment_custom_progress, container, false);

        return progressView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        item_list_view = (ListView)mContentView.findViewById(R.id.item_list_view);
        setData();
        setContentView(mContentView);

     }

}
