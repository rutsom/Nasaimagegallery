package com.obvioustest.nasaimagegallery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

public class GridAdapter extends BaseAdapter {
    private final android.content.Context context;
    private final ArrayList<HashMap<String, String>> imageList;

    // Step 1
    public GridAdapter(Context context) {
        this.context = context;
        this.imageList = (new DataHandler(context).getRawData());
    }


    // Step 2
    @Override
    public int getCount() {
        return this.imageList.size();
    }

    // Step 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Step 4
    @Override
    public String getItem(int position) {
        return imageList.get(position).get("url");
    }

    // Step 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // For single column
        View gridView = inflater.inflate(R.layout.thumbnail, null);

        String singleImageurl = getItem(position);
        Log.d("thumbnail", singleImageurl);
        // Set adopter element into variable
        GifImageView imageView = (GifImageView) gridView.findViewById(R.id.img_thumbnail);
        new ImageGetter(imageView).execute(singleImageurl);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, imageList.get(position).get("title"), Toast.LENGTH_LONG).show();
            }
        });
        return gridView;
    }

}
