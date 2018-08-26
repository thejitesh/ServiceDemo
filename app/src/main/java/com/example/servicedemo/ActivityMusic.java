package com.example.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityMusic extends AppCompatActivity {

    MusicService mService;
    boolean mBound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        findViewById(R.id.tvStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.startMusic();
            }
        });


        findViewById(R.id.tvStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.stopService();
            }
        });

    }

    @Override
    protected void onStart() {

        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        ContextCompat.startForegroundService(this , intent);
        //bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
       // unbindService(mConnection);
       // mBound = false;
    }



    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
