package com.example.vedio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
   VideoView videoView;
   int rQ=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(VideoView)findViewById(R.id.videoView);
    }



    public void btn_camera(View view){
        Intent takeVideo =new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideo.resolveActivity(getPackageManager())!=null){
              startActivityForResult(takeVideo,rQ);

        }
}
   protected void onActivityResult(int requestCode, int resultCode,Intent data){

        if (requestCode==rQ&&resultCode==RESULT_OK){

            Uri videoUri =data.getData();
            videoView.setVideoURI(videoUri);
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus ();
            videoView.start();
        }
   }
}
