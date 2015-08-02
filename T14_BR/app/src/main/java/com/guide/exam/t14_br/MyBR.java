package com.guide.exam.t14_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by oraclejava7 on 15. 8. 2..
 */
public class MyBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Object[] pdus = (Object[])bundle.get("pdus");

            String str = "";
            for(int i=0; i<pdus.length; i++){
                SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str += "SMS from "+msg.getOriginatingAddress();
                str += " : ";
                str += msg.getMessageBody() + "\n";
            }

            Log.d("MyBR", str);
        }
    }
}
