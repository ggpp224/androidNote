package com.example.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.spring.vo.Event;

public class ListActivity extends Activity {
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		list = (ListView) findViewById(R.id.listView1);
		new GetList().execute("");
		//Ìí¼Óµã»÷  
        list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int pos,
					long arg3) {
				HashMap rec = (HashMap) adapter.getItemAtPosition(pos);
				Toast.makeText(ListActivity.this, rec.get("url").toString(), Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(ListActivity.this, VideoActivity.class);
				intent.putExtra("url", rec.get("url").toString());
				startActivity(intent);
				
			}
        	
        });
		
	}
	
	class GetList extends AsyncTask<String,String,List>{

		@Override
		protected List doInBackground(String... arg0) {
			String url = "http://10.10.6.78/list.php";
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

			// Make the HTTP GET request, marshaling the response from JSON to an array of Events
			ResponseEntity<Event[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Event[].class);
			Event[] events = responseEntity.getBody();
			return Arrays.asList(events);
		}
		
		protected void onPostExecute(List result){
			List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
			for(int i=0;i<result.size();i++){
				Event e=(Event)result.get(i);
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("id",e.getId());
				item.put("name",e.getName());
				Log.e("AA", e.getName());
				item.put("sex",e.getSex());
				item.put("url", e.getUrl());
				listItems.add(item);
			}
			SimpleAdapter adapter = new SimpleAdapter(ListActivity.this,
					listItems,
					R.layout.list,
					new String[]{"name"},
					new int[]{R.id.listItemText}
			);
			list.setAdapter(adapter);
		}
		
	}

}
