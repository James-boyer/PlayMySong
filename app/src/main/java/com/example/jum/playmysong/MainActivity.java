package com.example.jum.playmysong;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {
    Intent song;
    /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        //final Button button2 = (Button)findViewById(R.id.button2);
        //button2.setEnabled(false);
        song = new Intent(this,MyService.class);
        song.putExtra("Filename","/reich.mp3");
        song.putExtra("Foldername","/mySongs");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    copy2sd("/mySongs", "/reich.mp3");

                }catch(Exception e) {

                }

                startService(song);

                //button2.setEnabled(true);
            }
        });


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
    /*
     Copys the song into a file if that file doesnt already exist
     */
    public void copy2sd(String folder,String fn) throws IOException{

            final int songID = R.raw.reich;

            String path = Environment.getExternalStorageDirectory() + folder;
            File dir = new File(path);
            if (dir.mkdirs() || dir.isDirectory()) {
                path = path + fn;
                dir = new File(path);

                if(!dir.exists()){
                    InputStream in = getResources().openRawResource(songID);
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buff = new byte[1024];
                    int read = 0;
                    try {
                        while ((read = in.read(buff)) > 0) {
                            out.write(buff, 0, read);
                        }
                    } finally {
                        in.close();
                        out.close();
                    }
                }
            }


        }
    @Override
    public void onBackPressed(){
       // stopService(songPlayer);

    }

}
