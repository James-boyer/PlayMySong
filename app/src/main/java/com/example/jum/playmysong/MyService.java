package com.example.jum.playmysong;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by jum on 27/03/15.
 */
public class MyService extends IntentService {
    MediaPlayer player = new MediaPlayer();
    public MyService(){
        super("MyService");
    }
    @Override
    protected void onHandleIntent(Intent intent){

        String folder = intent.getStringExtra("Foldername");
        String fn = intent.getStringExtra("Filename");
        Uri myUri = Uri.parse("file:///sdcard"+folder+fn);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(getApplicationContext(), myUri);
            player.prepare();
            player.start();
        }catch(Exception e){

        }
    }

}
