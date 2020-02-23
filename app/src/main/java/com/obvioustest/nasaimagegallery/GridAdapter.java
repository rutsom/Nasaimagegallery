package com.obvioustest.nasaimagegallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.skydoves.elasticviews.ElasticAnimation;
import com.skydoves.elasticviews.ElasticFinishListener;
import com.skydoves.elasticviews.ElasticLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

public class GridAdapter extends BaseAdapter {
    private static ArrayList<Bitmap> imgList;
    private android.content.Context context;
    private ArrayList<HashMap<String, String>> imageList;

    // Step 1
    public GridAdapter(Context context) {
        this.context = context;
        this.imageList = (new DataHandler(context).getRawData());
        imgList = new ArrayList<>();
    }

    static void setImglist(ArrayList<Bitmap> bitmaps) {
        imgList = bitmaps;
    }

    // Step 2
    @Override
    public int getCount() {

        if (imgList.size() < 4) {
            return 4;
        } else {
            return imgList.size();
        }

    }

    // Step 3
    @Override
    public long getItemId(int position) {
        // Log.e("id",String.valueOf(this.imageList.size()));
        return position;
    }

    // Step 4
    @Override
    public String getItem(int position) {
        //  Log.e("item",String.valueOf(position));

        return imageList.get(position).get("url");
    }

    // Step 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.thumbnail, null);

        }

        //imgList.add(position,i.image);
        String singleImageurl = getItem(position);
        Log.d("thumbnail", singleImageurl);
        holder = new Holder();
        // Set adopter element into variable
        ElasticLayout elasticLayout = (ElasticLayout) convertView.findViewById(R.id.elasticlayout);
        CardView cardView = convertView.findViewById(R.id.thumbnail_card);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ElasticAnimation.Builder().setView(v).setScaleX(0.98f)
                        .setScaleY(0.98f).setDuration(500)
                        .setOnFinishListener(new ElasticFinishListener() {
                            @Override
                            public void onFinished() {
                                Toast.makeText(context, imageList.get(position).get("title"), Toast.LENGTH_LONG).show();
                            }
                        }).doAction();

            }
        });
        ((TextView) cardView.findViewById(R.id.thumbnail_title)).setText(imageList.get(position).get("title"));

        if (position <= imgList.size() - 1) {
            GifImageView imageView = (GifImageView) cardView.findViewById(R.id.img_thumbnail);
            imageView.setImageBitmap(imgList.get(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.imageView = imageView;
        }
        convertView.setTag(holder);


        return convertView;
    }


    class Holder extends Object {
        GifImageView imageView;
    }

}
