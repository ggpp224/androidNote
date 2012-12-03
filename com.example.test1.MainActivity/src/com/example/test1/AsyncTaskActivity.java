package com.example.test1;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class AsyncTaskActivity extends Activity {
	private Button startBtn;
	private ProgressBar pBar;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);
		
		startBtn = (Button) findViewById(R.id.button1);
		pBar = (ProgressBar) findViewById(R.id.progressBar1);
		img = (ImageView) findViewById(R.id.imageView1);
		
		startBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new DownLoad().execute("http://csdnimg.cn/www/images/csdnindex_logo.gif");
				
			}
			
		});
	}
	
	class DownLoad extends AsyncTask<String, Integer, Bitmap >{

		@Override
		protected Bitmap doInBackground(String... arg) {
			publishProgress(0);
			HttpClient hc = new DefaultHttpClient();
			publishProgress(30);
			HttpGet hg = new HttpGet(arg[0]);
			Bitmap bm = null;
			HttpResponse resp;
			
			try {
				resp = hc.execute(hg);
				bm = BitmapFactory.decodeStream(resp.getEntity().getContent());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}
			
			return bm;
		}
		
		protected void onProgressUpdate(Integer... progress){
			pBar.setProgress(progress[0]);
		}
		
		protected void onPostExecute(Bitmap result){
			if(result != null){
				pBar.setProgress(100);
				Toast.makeText(AsyncTaskActivity.this, "成功获取图片", Toast.LENGTH_LONG).show();
				img.setImageBitmap(result);
			}else{
				Toast.makeText(AsyncTaskActivity.this, "失败 ", Toast.LENGTH_LONG).show();
			}
		}
		
		protected void onPreexecte(){
			img.setImageBitmap(null);
			pBar.setProgress(0);
		}
		
		protected void onCancelled(){
			pBar.setProgress(0);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_async_task, menu);
		return true;
	}

}
