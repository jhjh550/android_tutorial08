package com.guide.exam.t16_br3;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by oraclejava7 on 15. 8. 2..
 */
public class MyCutomBR extends BroadcastReceiver {
    NotificationManager manager;
    @Override
    public void onReceive(Context context, Intent intent) {
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

    }
}
