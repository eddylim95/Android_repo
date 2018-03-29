package com.example.Skynet.skynet;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.skynet.skynet.R;

import java.util.ArrayList;
import java.util.List;

import HotspotDatabase.AppDatabase;
import HotspotDatabase.AsyncStoreSQL;
import HotspotDatabase.AsycQuery;
import HotspotDatabase.Hotspot;
import HotspotDatabase.InternetConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To enable internet in Emulator
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (InternetConnection.getInternetStatus().getInternet() == true){
            try {
                Log.d("MainActivity", "Internet connected");
                new AsyncStoreSQL(AppDatabase.getInstance(getApplicationContext()),getApplicationContext()).execute();
            }
            catch (Exception e){
                Log.e("MainActivity", "Error, skipping data update", e);
            }
        }
        else {
            Log.d("MainActivity", "No Internet Connection, skipping Json retrieval");
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try {
                    Hotspot[] query = new AsycQuery(AppDatabase.getInstance(getApplicationContext())).execute().get();
                    Log.d("MainActivity", query[500].getNAME());

                }
                catch (Exception e) {
                    Log.e("MainActivity", "AsyncQuery Error", e);
                }
            }
        });
    }

}