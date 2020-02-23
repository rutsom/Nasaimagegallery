package com.obvioustest.nasaimagegallery;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


class ImageGetter extends AsyncTask<Integer, Bitmap, Bitmap> {
    public static ArrayList<Bitmap> image = new ArrayList<>();
    public static boolean isReady = false;
    private ArrayList<HashMap<String, String>> data;
    private GridAdapter gridAdapter;

    private Activity activity;

    public ImageGetter(Context context, Activity activity, GridAdapter adapter) {
        this.activity = activity;
        data = (new DataHandler(context).getRawData());
        gridAdapter = adapter;
    }


    @Override
    protected Bitmap doInBackground(Integer... integers) {
        String urlOfImage = data.get(integers[0]).get("url");
        Bitmap img = null;
        try {
            InputStream is = new URL(urlOfImage).openStream();
            img = BitmapFactory.decodeStream(is);
            image.add(integers[0], img);
            Log.e("url", String.valueOf(integers[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    protected void onPostExecute(Bitmap result) {
        GridAdapter.setImglist((ImageGetter.image));
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gridAdapter.notifyDataSetChanged();
            }
        });

    }
}


