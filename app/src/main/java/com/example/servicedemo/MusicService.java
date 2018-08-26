package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service{

   public MusicService(){

   }
    private final IBinder mBinder = new LocalBinder();
    MediaPlayer mediaPlayer;

    public class LocalBinder extends Binder {
        MusicService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startMusic();
        return START_STICKY;
    }

    public void startMusic(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sandstorm);
        mediaPlayer.start();
    }

    public void stopService(){
        mediaPlayer.stop();
    }
}
