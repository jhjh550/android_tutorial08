package com.example.c.t22_service2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by c on 2015-08-08.
 */
public class MyBoundService extends Service {

    public class MyBinder extends Binder{
        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    private IBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Random random = new Random();
    public int getRandomNumber(){
        return random.nextInt(100);
    }


}
