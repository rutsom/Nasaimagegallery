package com.obvioustest.nasaimagegallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import pl.droidsonroids.gif.GifImageView;

class ImageGetter extends AsyncTask<String, Void, Bitmap> {
    GifImageView imageView;

    public ImageGetter(GifImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlOfImage = urls[0];
        Bitmap img = null;
        try {
            InputStream is = new URL(urlOfImage).openStream();

            img = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}

