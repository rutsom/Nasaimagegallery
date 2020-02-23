package com.obvioustest.nasaimagegallery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageHome extends AppCompatActivity {

    private Activity activity;
    private static Thread imageGetterThread;
    private GridView thumbnail;
    private GridAdapter adapter;

    //sharing thread status
    public static boolean isImageListReady() {
        return imageGetterThread.isAlive();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_image_home);
        //Defining Property
        thumbnail = findViewById(R.id.thumbnail_view);
        adapter = new GridAdapter(this);
        //Assignment of properties
        thumbnail.setAdapter(adapter);
        thumbnail.setFriction(0.3f);
        //Check for Data changes
        checkForDataChange(adapter);

    }

    private void checkForDataChange(final GridAdapter adapter) {
        imageGetterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < (new DataHandler(getApplicationContext()).getRawData().size()); i++) {
                    new ImageGetter(getApplicationContext(), activity, adapter).execute(i);
                }
            }
        });
        imageGetterThread.start();
    }
}
