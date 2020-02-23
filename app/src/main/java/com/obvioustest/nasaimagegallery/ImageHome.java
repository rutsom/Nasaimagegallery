package com.obvioustest.nasaimagegallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skydoves.elasticviews.ElasticAnimation;
import com.skydoves.elasticviews.ElasticFinishListener;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.ArrayList;
import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

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
