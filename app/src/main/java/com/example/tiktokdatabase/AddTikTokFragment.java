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


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTikTokFragment extends Fragment {
    EditText descriptionEditText;
    EditText urlEditText;
    Button addPhotoButton;
    Button saveTikTokButton;


    public AddTikTokFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_tik_tok, container, false);

        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        urlEditText = view.findViewById(R.id.urlEditText);
        addPhotoButton = view.findViewById(R.id.addPhotoButton);
        saveTikTokButton = view.findViewById(R.id.saveTikTokButton);

        final TikTokDatabase tikTokDatabase = Room.databaseBuilder(getActivity(), TikTokDatabase.class, "usefulVideos")
                .allowMainThreadQueries().build();

        saveTikTokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tikTokDatabase.getTikTokDAO().addTikTok(new Video(0, descriptionEditText.getText().toString(), urlEditText.getText().toString()));
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });
        return view;
    }

}
