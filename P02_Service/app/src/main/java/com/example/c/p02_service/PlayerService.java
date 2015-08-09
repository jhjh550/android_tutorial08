package com.example.c.p02_service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by c on 2015-08-08.
 */
public class PlayerService extends Service {

    private boolean headsetConnected = false;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.hasExtra("state")){
                if(headsetConnected == true && intent.getIntExtra("state", 0)==0){
                    headsetConnected = false;
                    if(mp != null){
                        if(mp.isPlaying()){
                            mp.pause();
                            Toast.makeText(context, "plug disconnected!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }else if (headsetConnected == false && intent.getIntExtra("state",0)==1){
                    headsetConnected = true;
                }
            }
        }
    };

    public class MyBinder extends Binder {
        public PlayerService getService(){
            return PlayerService.this;
        }
    }

    private IBinder binder = new MyBinder();
    private MediaPlayer mp = null;

    public void play(){
        String path = Environment.getExternalStorageDirectory().toString();
        path += "/Music/MyStory.wav";
        mp = new MediaPlayer();
        try {
            mp.setDataSource(path);
            mp.prepare();
        }catch (Exception e){
            e.printStackTrace();;
        }
        mp.start();
    }

    public void pause(){
        if(mp != null){
            if(mp.isPlaying())
                mp.pause();
            else
                mp.start();
        }
    }

    public void stop(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("PlayerService", "onStartCommand");
        return START_STICKY;
    }

    public int getCurrentPosition() {
        int progress = 0;
        if(mp != null){
            progress = (int) (((float) mp.getCurrentPosition()) / ((float) mp.getDuration()) * 100);
        }
        return progress;
    }



}
