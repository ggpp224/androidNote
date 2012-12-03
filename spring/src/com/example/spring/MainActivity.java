package com.example.spring;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.spring.vo.Event;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn;
	private Button listBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listBtn = (Button) findViewById(R.id.listBtn);
		listBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ListActivity.class);
				startActivity(intent);
			}
			
		});
		
		
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				/*String url = "http://10.10.6.78/test.php?q={query}";
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
				Log.e("AAA", "bbbb");
				String result = restTemplate.getForObject(url, String.class,"SpringSource");
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
				Log.e("AAA", result);*/
				
				
				String url = "http://10.10.6.78/aa.php";
				HttpHeaders requestHeaders = new HttpHeaders();
				requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
				HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

				// Create a new RestTemplate instance
				RestTemplate restTemplate = new RestTemplate();

				// Add the Gson message converter
				restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

				// Make the HTTP GET request, marshaling the response from JSON to an array of Events
				ResponseEntity<Event[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Event[].class);
				Event[] events = responseEntity.getBody();
				Toast.makeText(MainActivity.this, events[0].getName(), Toast.LENGTH_LONG).show();
				
			}
			
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
