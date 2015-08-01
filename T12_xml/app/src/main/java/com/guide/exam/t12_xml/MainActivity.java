package com.guide.exam.t12_xml;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends ActionBarActivity {

    TextView textView;

    class MyXMLTask extends AsyncTask<String, Void, Document>{

        @Override
        protected Document doInBackground(String... params) {

            URL url;
            Document doc = null;

            try {
                url = new URL(params[0]);

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(url.openStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return doc;
        }

        private String getTagValue(Element dataElement, String tagName){
            NodeList nodeList = dataElement.getElementsByTagName(tagName);
            Element element = (Element)nodeList.item(0);// <day>0</day>
            NodeList textNodeList = element.getChildNodes();
            return textNodeList.item(0).getNodeValue();// 0
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);

            String str="";
            NodeList nodeList = document.getElementsByTagName("data");
            for(int i=0; i<nodeList.getLength(); i++){
                Element DataElement = (Element)nodeList.item(i);// <data>...</data>
                str += " day : " + getTagValue(DataElement, "day");
                str += " 시간 : " + getTagValue(DataElement, "hour");
                str += " 온도 : " + getTagValue(DataElement, "temp");
                str += " 날씨 : " + getTagValue(DataElement, "wfKor");





                str += "\n";
            }


            textView.setText(str);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        MyXMLTask task = new MyXMLTask();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159054000");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
