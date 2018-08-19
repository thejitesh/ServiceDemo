package com.example.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static com.example.servicedemo.ServiceActivityCommunication.BROADCAST_ACTION_BAZ;

public class MyService extends IntentService
{


    long prevTime;
    int counter = 0;

    public MyService() {
        super("MyService");
        prevTime =  System.currentTimeMillis();

    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        print("Service start");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        print("Service Destroy");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        while (true){

           long currentTime =  System.currentTimeMillis();

           if(currentTime - prevTime > 4000){

               print(""+ ++counter);

               Intent intent1 = new Intent(BROADCAST_ACTION_BAZ);
               intent1.putExtra("key", ""+counter);
               LocalBroadcastManager bm = LocalBroadcastManager.getInstance(this);
               bm.sendBroadcast(intent1);

               prevTime = currentTime;
           }



        }



    }


    public void print(String string){

        Log.d("##LOG##",string);

    }
}
