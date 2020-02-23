package com.obvioustest.nasaimagegallery;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class DetailedView extends AppCompatActivity {
    RecyclerView recyclerView;
    DetailedViewAdapter detailedViewAdapter;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int calledPageNumber = getIntent().getExtras().getInt("item");
        setContentView(R.layout.activity_detailed_view);
        //Deceleration of variables
        activity = this;
        recyclerView = findViewById(R.id.detailed_view);
        detailedViewAdapter = new DetailedViewAdapter(getApplicationContext());
        //Assignment of properties
        recyclerView.setAdapter(detailedViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.scrollToPosition(calledPageNumber);
        //Check for Data changes
        checkForDataChange();


    }

    private void checkForDataChange() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //check if all images are loaded
                    while (ImageHome.isImageListReady()) {
                        Thread.sleep(500);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                detailedViewAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
