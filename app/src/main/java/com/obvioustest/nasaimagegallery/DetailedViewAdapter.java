package com.obvioustest.nasaimagegallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

class DetailedViewAdapter extends RecyclerView.Adapter<DetailedViewAdapter.viewHolder> {
    private ArrayList<HashMap<String, String>> dataList;
    private Context context;

    //Adapter Constructor
    DetailedViewAdapter(Context context) {
        dataList = (new DataHandler(context)).getRawData();
        this.context = context;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detailed_layout, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        //Assignment of Data
        holder.title.setText(dataList.get(position).get("title"));
        holder.copyright.setText(((dataList.get(position).get("copyright") != null ?
                (context.getText(R.string.copyright_symbol) + " " + dataList.get(position).get("copyright") + "    ") : " ") +
                dataList.get(position).get("date")));
        holder.explanation.setText(dataList.get(position).get("explanation"));
        holder.explanation.setMovementMethod(new ScrollingMovementMethod());
        if (position <= ImageGetter.image.size() - 1) {
            holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.image.setImageBitmap(ImageGetter.image.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //Item Holder
    class viewHolder extends RecyclerView.ViewHolder {
        TextView title, explanation, copyright;
        ImageView image;
        CardView cardView;
        viewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.detailed_card);
            title = itemView.findViewById(R.id.detailed_title);
            explanation = itemView.findViewById(R.id.detailed_explanation);
            copyright = itemView.findViewById(R.id.detailed_copyright);
            image = itemView.findViewById(R.id.detailed_image);
        }
    }
}
