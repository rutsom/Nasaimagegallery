package com.obvioustest.nasaimagegallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.skydoves.elasticviews.ElasticAnimation;
import com.skydoves.elasticviews.ElasticFinishListener;

import java.util.ArrayList;
import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

public class GridAdapter extends BaseAdapter {
    private static ArrayList<Bitmap> imgList;
    private android.content.Context context;
    private ArrayList<HashMap<String, String>> DataList;

    // Adapter Constructor
    public GridAdapter(Context context) {
        this.context = context;
        this.DataList = (new DataHandler(context).getRawData());
        imgList = new ArrayList<>();
    }

    static void setImglist(ArrayList<Bitmap> bitmaps) {
        imgList = bitmaps;
    }

    @Override
    public int getCount() {
            //for Showing a basic skeleton
        if (imgList.size() < 4) {
            return 4;
        } else {
            //populate card one by one
            return imgList.size();
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public String getItem(int position) {
        return DataList.get(position).get("url");
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.thumbnail_layout, null);
        }
        CardView cardView = convertView.findViewById(R.id.thumbnail_card);
        ((TextView) cardView.findViewById(R.id.thumbnail_title)).setText(DataList.get(position).get("title"));

        //load image only if image in downloaded from the cloud
        if (position <= imgList.size() - 1) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ElasticAnimation.Builder().setView(v).setScaleX(0.98f)
                            .setScaleY(0.98f).setDuration(500)
                            .setOnFinishListener(new ElasticFinishListener() {
                                @Override
                                public void onFinished() {
                                    Bundle parameterBundle = new Bundle();
                                    parameterBundle.putInt("item", position);
                                    Intent DetailedIntent = new Intent(context, DetailedView.class);
                                    DetailedIntent.putExtra("item", position);
                                    context.startActivity(DetailedIntent);
                                }
                            }).doAction();

                }
            });
            GifImageView imageView = cardView.findViewById(R.id.img_thumbnail);
            imageView.setImageBitmap(imgList.get(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }


        return convertView;
    }


}
