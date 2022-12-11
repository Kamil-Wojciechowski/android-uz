package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class About extends AppCompatActivity {
    Button bt_aboutPlay;
    VideoView videoPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initialize();

    }
    private void initialize(){
        bt_aboutPlay = findViewById(R.id.bt_aboutPlay);
        videoPlay = findViewById(R.id.videoView);
    }
    public void playVid(View v){
        String videoPath = "android.resource://com.example.foodcount/"+R.raw.jessi;
        Uri uri = Uri.parse(videoPath);
        videoPlay.setVideoURI(uri);
        videoPlay.start();
    }
}