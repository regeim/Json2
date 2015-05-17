package com.example.zoli.json.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

import com.devspark.progressfragment.ProgressListFragment;
import com.example.zoli.json.HttpConnection;
import com.example.zoli.json.Item;
import com.example.zoli.json.ProcessJSON;
import com.example.zoli.json.ProcessJSON2;
import com.example.zoli.json.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

/**
 * Created by Zoli on 2015.03.05..
 */
public class ItemProgressListFragment extends ProgressListFragment {

    private String url="http://eu.battle.net/api/wow/auction/data/outland";

    private ProcessJSON url_json;
    private ProcessJSON2 main_json;



    private HttpConnection main_Http;
    private List<String> auction_url;

    private ArrayList<Item> item_list;
    private byte[] array_holder;
    private String array_length;
    private Spinner item_list_spinner;

    private ListView item_list_view;
    private View mContentView;
    private View progressView;

    private static final String TAG ="ItemProgressListFragment";


    Handler json_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (item_list!=null){
                /*ArrayAdapter <Item> item_list_view_adapter=new ArrayAdapter<Item>(getActivity(),android.R.layout.simple_list_item_1,item_list);*/
                ItemListAdapter item_list_view_adapter=new ItemListAdapter(item_list);
                setListAdapter(item_list_view_adapter);
            }
            setListShown(true);
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
                    url_json=new ProcessJSON (inputStream);
                    try {
                        auction_url=url_json.createFromJSON("url");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    main_Http.CloseStream();

                }
                main_Http=new HttpConnection(auction_url.get(0).toString(),getActivity());
                main_Http.OpenStream();

                if (main_Http.getStream()!=null){
                    inputStream=main_Http.getStream();
                    main_json=new ProcessJSON2 (inputStream);
                    try {
                        item_list=main_json.createFromJSON2();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    main_Http.CloseStream();}
                json_handler.sendEmptyMessage(0);


            }};

        setListShown(false);
        Thread json_thread= new Thread(runnable);
        json_thread.start();

    }



    public static ItemProgressListFragment newInstance() {
        ItemProgressListFragment fragment = new ItemProgressListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText(R.string.empty);
        setData();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Item i = (Item)(getListAdapter()).getItem(position);
        Log.d(TAG, i.getId() + " was clicked");
    }
    private class ItemListAdapter extends ArrayAdapter <Item> {

        public ItemListAdapter (ArrayList<Item> items) {
            super(getActivity(), 0, items);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.item_list, null);
            }

            // Configure the view for this Crime
            Item i = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.item_text);
            titleTextView.setText(i.getAuction_number());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.owner_text);
            dateTextView.setText(i.getOwner());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.item_list_checkBox);
            solvedCheckBox.setChecked(i.getIsSelected());

            return convertView;
        }



    }
}
