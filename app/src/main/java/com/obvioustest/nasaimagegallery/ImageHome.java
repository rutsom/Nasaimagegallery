package com.obvioustest.nasaimagegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ImageHome extends AppCompatActivity {

    private View thumbnail = new View(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imahe_home);

    }
}
