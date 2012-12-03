package com.example.spring;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.app.Activity;
import android.content.Intent;

public class VideoActivity extends Activity {
	private VideoView videoView;
	private MediaController mediaController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		Intent intent = getIntent();
		String url =intent.getStringExtra("url");
		
		videoView = (VideoView) findViewById(R.id.videoView1);
		mediaController = new MediaController(this);
		videoView.setVideoURI(Uri.parse(url));
		videoView.setMediaController(mediaController);
		mediaController.setMediaPlayer(videoView);
		//videoView.requestFocus(); 
		videoView.start();
	}

	

}
