package com.obvioustest.nasaimagegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;

public class ImageHome extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imahe_home);
        GridView thumbnail = findViewById(R.id.thumbnail_view);
        GridAdapter adapter = new GridAdapter(this);
        thumbnail.setAdapter(adapter);
    }
}
