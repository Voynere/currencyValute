package com.example.currencyrates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
RecyclerView rv;
HashMap<Integer, ObjectValute> list = new HashMap<>();
Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getList();
        rv = findViewById(R.id.rv);


        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        adapter = new Adapter(list, getApplicationContext());
        rv.setAdapter(adapter);






    }




    public void getList() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get("https://www.cbr-xml-daily.ru/daily_json.js", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("tagmy",""+responseString);


               try {
                   JSONObject jsonObject = new JSONObject(responseString);
                  // String date = jsonObject.getString("Date");

            JSONObject jsonObject_Valute = jsonObject.getJSONObject("Valute");

            Iterator<String> key = jsonObject_Valute.keys();
            Integer i = -1;
            while ( (key.hasNext())) {
                String keys = key.next();
                i++;
             String title = jsonObject_Valute.getJSONObject(keys).getString("CharCode");
             String name = jsonObject_Valute.getJSONObject(keys).getString("Name");
             Double value = jsonObject_Valute.getJSONObject(keys).getDouble("Value");


             list.put(i, new ObjectValute(title, name, value));

            }

            Log.e("size","size"+list.size());


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });


               }catch (Exception e) {

               }

            }
        });

    }

}