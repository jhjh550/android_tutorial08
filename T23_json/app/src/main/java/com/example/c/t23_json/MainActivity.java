package com.example.c.t23_json;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String str = "[ {'name':'kim', 'tel':'010-1111-2222', 'age':20}," +
            "{'name':'park', 'tel':'010-2222-3333', 'age':30}," +
            "{'name':'lee', 'tel':'010-3333-4444', 'age':40} ]";

    class GetContacs extends AsyncTask<Void, Void, Void>{
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pDialog.isShowing())
                pDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String strJson = getResponseString("http://api.androidhive.info/contacts/", GET, null);
            Log.d("JSON", strJson);

            try {
                JSONObject obj = new JSONObject(strJson);
                JSONArray contacts = obj.getJSONArray("contacts");
                for(int i=0; i<contacts.length(); i++){
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    String gender = c.getString("gender");
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getResponseString(String url, int method, List<NameValuePair> params){
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = null;
            HttpEntity httpEntity = null;
            String responseStr="";
            try {
                if (method == POST) {
                    HttpPost httpPost = new HttpPost(url);
                    if (params != null) {
                        httpPost.setEntity(new UrlEncodedFormEntity(params));
                    }
                    httpResponse = httpClient.execute(httpPost);

                } else if (method == GET) {
                    if(params != null){
                        String paramString = URLEncodedUtils.format(params, "utf-8");
                        url += "?"+paramString;
                    }
                    HttpGet httpGet = new HttpGet(url);
                    httpResponse = httpClient.execute(httpGet);

                }
                httpEntity = httpResponse.getEntity();
                responseStr = EntityUtils.toString(httpEntity);



            }catch (Exception e){
                e.printStackTrace();
            }
            return responseStr;
        }

    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONArray array = new JSONArray(str);
            for(int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("name");
                String tel = obj.getString("tel");
                int age = obj.getInt("age");

                Log.d("json", "name:"+name+" tel:"+tel+" age:"+age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //http://api.androidhive.info/contacts/

        GetContacs task = new GetContacs();
        task.execute();



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

    public static final int POST = 1;
    public static final int GET = 2;


}






