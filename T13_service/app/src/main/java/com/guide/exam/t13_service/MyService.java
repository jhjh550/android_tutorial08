package com.guide.exam.t13_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by oraclejava7 on 15. 8. 2..
 */
public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand");

        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyService", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("MyService", "onDestroy");
    }
}
