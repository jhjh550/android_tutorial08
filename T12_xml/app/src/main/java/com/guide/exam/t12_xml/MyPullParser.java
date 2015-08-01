package com.guide.exam.t12_xml;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

/**
 * Created by oraclejava7 on 15. 8. 1..
 */
public class MyPullParser extends AsyncTask<String, Void, Void>{
    private String str = "";
    public String getStr(){
        return str;
    }


    @Override
    protected Void doInBackground(String... params) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            URL url = new URL(params[0]);
            xpp.setInput(url.openStream(), "UTF-8");

            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                //
                //
                //
                eventType = xpp.next();
            }


        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}
