package com.example.jum.playmysong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jum on 31/03/15.
 */
public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent song = new Intent(context,MyService.class);
        song.putExtra("Filename","/reich.mp3");
        song.putExtra("Foldername","/mySongs");

        context.startService(song);

    }

}
