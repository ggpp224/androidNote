package com.example.test1;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	private VideoView videoView;
	private MediaController mediaController;
	private String rootPath = android.os.Environment.getExternalStorageDirectory().getPath();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		videoView = (VideoView) findViewById(R.id.videoView1);
		mediaController = new MediaController(this);
		/*File videoFile = new File(rootPath + "/aa.mp4");
		if(videoFile.exists()){
			videoView.setVideoPath(videoFile.getAbsolutePath());
			videoView.setMediaController(mediaController);
			mediaController.setMediaPlayer(videoView);
			videoView.requestFocus(); 
		}
		
		videoView.setVideoURI(Uri.parse("rtsp://122.192.35.80:554/live/tv01"));
		videoView.setMediaController(mediaController);
		mediaController.setMediaPlayer(videoView);*/
		
		videoView.setVideoURI(Uri.parse("http://10.10.6.78/test.mp4"));
		videoView.setMediaController(mediaController);
		mediaController.setMediaPlayer(videoView);
		videoView.requestFocus(); 
		videoView.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_video, menu);
		return true;
	}

}
