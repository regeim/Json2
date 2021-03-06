package com.example.zoli.json.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.devspark.progressfragment.ProgressListFragment;
import com.example.zoli.json.HttpConnection;
import com.example.zoli.json.Item;
import com.example.zoli.json.ProcessJSON;
import com.example.zoli.json.ProcessJSON2;
import com.example.zoli.json.R;

/*import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;*/

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoli on 2015.03.05..
 */
public class ItemProgressListFragment extends ProgressListFragment {

    private String url="http://eu.battle.net/api/wow/auction/data/outland";
   /* private String url2="http://188.143.106.70:90/z/z.json";*/
    private String url2="http://regeim.ddns.net/z/z.json";
    /*private String url2="http://eu.battle.net/auction-data/ef92b9868d1316c1066e9fb5e6d979a0/auctions.json";*/
    private ProcessJSON url_json;
    private ProcessJSON2 main_json;



    private HttpConnection main_Http;
    private HttpConnection main_Http2;
    private List<String> auction_url;

    private ArrayList<Item> item_list;
    private byte[] array_holder;
    private String array_length;
    private Spinner item_list_spinner;

    private ListView item_list_view;
    private View mContentView;
    private View progressView;



    ItemListInterface activityCommander;

    public interface ItemListInterface {
        public void setItemList(ArrayList<Item> itemList);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCommander = (ItemListInterface)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }


    Handler json_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (item_list!=null){
                /*ArrayAdapter <Item> item_list_view_adapter=new ArrayAdapter<Item>(getActivity(),android.R.layout.simple_list_item_1,item_list);*/
                ItemListAdapter item_list_view_adapter=new ItemListAdapter(item_list);
                setListAdapter(item_list_view_adapter);
                activityCommander.setItemList(item_list);

            }
            setListShown(true);
        }
    };



    public void setData (){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                InputStream inputStream;

                /*HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet("http://regeim.ddns.net/z/z.json");

                try {
                    HttpResponse response = client.execute(request);
                    HttpEntity entity = response.getEntity();

                    //
                    // Read the contents of an entity and return it as a String.
                    //
                    String content = EntityUtils.toString(entity);
                    System.out.println(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                /*main_Http=new HttpConnection(url,getActivity());
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

                }*/
                main_Http2=new HttpConnection(url2,getActivity());
                main_Http2.OpenStream();
                inputStream=main_Http2.getStream();
                array_length=inputStream.toString();
                if (main_Http2.getStream()!=null){

                    main_json=new ProcessJSON2 (inputStream);
                    try {
                        item_list=main_json.createFromJSON2();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    main_Http2.CloseStream();}
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
       /* Log.d(TAG, i.getId() + " was clicked");*/
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
