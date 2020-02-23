package com.obvioustest.nasaimagegallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

class DetailedViewAdapter extends RecyclerView.Adapter<DetailedViewAdapter.viewHolder> {
    private ArrayList<HashMap<String, String>> dataList;
    private Context context;

    public DetailedViewAdapter(Context context) {
        dataList = (new DataHandler(context)).getRawData();
        this.context = context;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detailedlayout, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(dataList.get(position).get("title"));
        holder.copyright.setText("Copyright: " + (dataList.get(position).get("copyright") != null ? dataList.get(position).get("copyright") : ""));
        // Toast.makeText(context,dataList.get(position).get("explanation"),Toast.LENGTH_LONG).show();
        holder.explanation.setText(dataList.get(position).get("explanation"));
        holder.image.setImageBitmap(ImageGetter.image.get(position));
    }

    @Override
    public int getItemCount() {
        return ImageGetter.image.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView title, explanation, copyright;
        public ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.detailed_title);
            explanation = itemView.findViewById(R.id.detailed_explanation);
            copyright = itemView.findViewById(R.id.detailed_copyright);
            image = itemView.findViewById(R.id.detailed_image);
        }
    }
}
