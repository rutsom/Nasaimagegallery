package com.obvioustest.nasaimagegallery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageHome extends AppCompatActivity {

    private int pos;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        //new ImageGetter(getApplicationContext()).execute();
        setContentView(R.layout.activity_imahe_home);
        final GridView thumbnail = findViewById(R.id.thumbnail_view);
        final GridAdapter adapter = new GridAdapter(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < (new DataHandler(getApplicationContext()).getRawData().size()); i++) {
                    new ImageGetter(getApplicationContext(), activity, adapter).execute(i);
                }


            }
        }).start();

        thumbnail.setAdapter(adapter);


        thumbnail.setFriction(0.3f);

    }


}
