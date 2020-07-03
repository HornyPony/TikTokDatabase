package com.example.tiktokdatabase;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTikTokFragment extends Fragment {
    EditText descriptionEditText;
    EditText urlEditText;
    Button addPhotoButton;
    Button saveTikTokButton;
    private VideosAdapter videosAdapter;
    private List<Video> videos;
    private TextView newVideoTitle;
    private boolean isUpdate;
    private int position;
    private TikTokDatabase tikTokDatabase;


    public AddTikTokFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_tik_tok, container, false);
        Bundle bundle = this.getArguments();
        tikTokDatabase = Room.databaseBuilder(getActivity(), TikTokDatabase.class, "usefulVideos")
                .allowMainThreadQueries().build();
        videosAdapter = new VideosAdapter(videos);
        videos = tikTokDatabase.getTikTokDAO().getAllVideos();
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        urlEditText = view.findViewById(R.id.urlEditText);
        addPhotoButton = view.findViewById(R.id.addPhotoButton);
        saveTikTokButton = view.findViewById(R.id.saveTikTokButton);
        newVideoTitle = view.findViewById(R.id.newVideoTitle);
        if (bundle != null) {
            isUpdate = getArguments().getBoolean("isUpdate");
            position = getArguments().getInt("position");
        }
        if (isUpdate == true) {
            newVideoTitle.setText("Edit Tik Tok");
            saveTikTokButton.setText("Update Tik Tok");

        }


        saveTikTokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUpdate == false) {
                    tikTokDatabase.getTikTokDAO().addTikTok(new Video(0, descriptionEditText.getText().toString(), urlEditText.getText().toString()));
                } else {
                    updateVideo(descriptionEditText.getText().toString(), urlEditText.getText().toString(), position);
                }
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });
        return view;
    }

    private void updateVideo(String description, String url, int position) {
        Video video = videos.get(position);

        video.setDescription(description);
        video.setURL(url);

        tikTokDatabase.getTikTokDAO().updateTikTok(video);

        videos.set(position, video);

        videosAdapter.notifyDataSetChanged();

    }

}
