package com.example.tiktokdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {

    private Context context;
    private List<Video> videos;
    private MainActivity mainActivity;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView tikTokImageView;
        public TextView descriptionTextView;
        public TextView URLTextView;


        public MyViewHolder(View view) {
            super(view);
            tikTokImageView = view.findViewById(R.id.tikTokImageView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            URLTextView = view.findViewById(R.id.urlTextView);
        }
    }


    public VideosAdapter(Context context, List<Video> videos, MainActivity mainActivity) {

        this.context = context;
        this.videos = videos;
        this.mainActivity = mainActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tiktok_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Video video = videos.get(position);

        holder.descriptionTextView.setText(video.getDescription());
        holder.URLTextView.setText(video.getURL());



        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                mainActivity.addAndEditVideos(true, video, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return videos.size();
    }


}
