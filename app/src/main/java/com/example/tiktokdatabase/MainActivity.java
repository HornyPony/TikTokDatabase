package com.example.tiktokdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private Boolean isUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TikTokDatabase tikTokDatabase = Room.databaseBuilder(getApplicationContext(), TikTokDatabase.class, "usefulVideos")
                .allowMainThreadQueries().build();
        List<Video> videos = tikTokDatabase.getTikTokDAO().getAllVideos();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideosAdapter(this, videos, MainActivity.this);
        recyclerView.setAdapter(adapter);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addAndEditVideos(false, null, -1);

            }
        });
    }

    public void addAndEditVideos(final boolean isUpdate, final Video video, final int position) {
        recyclerView.setVisibility(View.GONE);
        floatingActionButton.setVisibility(View.GONE);
        if (isUpdate == true) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            AddTikTokFragment addTikTokFragment = new AddTikTokFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isUpdate", isUpdate);
            bundle.putInt("position", position);
            addTikTokFragment.setArguments(bundle);
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, addTikTokFragment).commit();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            AddTikTokFragment addTikTokFragment = new AddTikTokFragment();
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, addTikTokFragment).commit();
        }
    }


}



