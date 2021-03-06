package com.example.tiktokdatabase;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setHasOptionsMenu(true);
        Bundle bundle = this.getArguments();
        tikTokDatabase = Room.databaseBuilder(getActivity(), TikTokDatabase.class, "usefulVideos")
                .allowMainThreadQueries().build(); //база данных, allowMainThreadQuerries временно, потом AsyncTask сделаю
        videosAdapter = new VideosAdapter(videos);
        videos = tikTokDatabase.getTikTokDAO().getAllVideos();
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        urlEditText = view.findViewById(R.id.urlEditText);
        addPhotoButton = view.findViewById(R.id.addPhotoButton);
        saveTikTokButton = view.findViewById(R.id.saveTikTokButton);
        newVideoTitle = view.findViewById(R.id.newVideoTitle);
        //получение из MainActivity данных о позиции айтема(для обновления элемента) и определение, происходит
        //обновление элемента или новый элемент добавляется
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
                    //добавление нового элемента
                    tikTokDatabase.getTikTokDAO().addTikTok(new Video(0, descriptionEditText.getText().toString(), urlEditText.getText().toString()));
                } else {
                    updateVideo(descriptionEditText.getText().toString(), urlEditText.getText().toString(), position);
                }
                //возвращение в MainActivity
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });
        return view;
    }

    //метод для обновления элемента
    private void updateVideo(String description, String url, int position) {
        Video video = videos.get(position);

        video.setDescription(description);
        video.setURL(url);

        tikTokDatabase.getTikTokDAO().updateTikTok(video);

        videos.set(position, video);

        videosAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_tiktok_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_tiktok:
                return true;
            case  R.id.delete_tiktok:
                return true;
                //TODO: При нажатии на кнопку назад, должно происходить возращение в MainActivity
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return  true;
        }

        return super.onOptionsItemSelected(item);
    }


}
