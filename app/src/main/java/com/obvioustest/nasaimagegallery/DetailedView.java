package com.obvioustest.nasaimagegallery;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class DetailedView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int calledPagenumber = getIntent().getExtras().getInt("item");

        setContentView(R.layout.activity_detailed_view);
        RecyclerView recyclerView = findViewById(R.id.detailed_view);
        DetailedViewAdapter detailedViewAdapter = new DetailedViewAdapter(getApplicationContext());
        recyclerView.setAdapter(detailedViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        linearLayoutManager.scrollToPosition(calledPagenumber);


    }
}
