package com.example.zoli.json;

/**
 * Created by Zoli on 2015.05.18..
 */

        import android.app.Service;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.IBinder;
        import android.os.Message;
        import android.util.Log;
        import android.widget.Toast;
        import android.os.Handler;

        import java.util.logging.LogRecord;

public class MyService extends Service {
    private static final String TAG = "MyService";
    MediaPlayer player;
    Handler handler;

    Handler my_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "Really started");
            player.start();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");




        player = MediaPlayer.create(this, R.raw.youking);
        player.setLooping(false); // Set looping
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");

            delay();


    }

    public void delay () {

        //thread, do not repeat
       /* final Runnable r = new Runnable() {
            public void run() {
                my_handler.sendEmptyMessageDelayed(0,12000);
            }
        };

        Thread json_thread= new Thread(r);
        json_thread.start();*/
        //

        //repeat
        handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                player.start();
                Log.d(TAG, "Really started");
                handler.postDelayed(this, 12000);
            }
        };

        handler.postDelayed(r, 12000);
        //



    }


}