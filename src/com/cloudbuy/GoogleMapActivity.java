package com.cloudbuy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.domain.User;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.maps.GeoPoint;
import com.google.gson.Gson;
import com.tools.ApacheHttpClient;
import com.tools.JsonTools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GoogleMapActivity extends Activity {
	
	//Map
	private GoogleMap mMap;
	private TextView txtOutput;
	private Marker markerMe;
	private List<Address> address;
	private String url;
	private EditText textAddress = null;
	private Button buttonSearch = null;
	private Button buttonMyLocation = null;
	
	//GPS
	private LocationManager locationMgr;
	private String provider;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			String result =  msg.obj.toString();
			System.out.println("result : " + result);
			
			
			try {
				JSONArray jsonArray = new org.json.JSONObject(result).getJSONArray("results");
				
				Object jsonObj = jsonArray.getJSONObject(0);
				
				JSONObject geometry =  ((JSONObject) jsonObj).getJSONObject("geometry");
				System.out.println("geometry : " + geometry);
				
				JSONObject location = geometry.getJSONObject("location");
				
				String strLat = location.getString("lat");
				String strLng = location.getString("lng");
				
				//double lat = 21.946567;
		        //double lng = 120.798713;
				double lat = Double.parseDouble(strLat);
				double lng = Double.parseDouble(strLng);
		        //float speed = location.getSpeed();
		        
		        MarkerOptions markerOpt = new MarkerOptions();
		        markerOpt.position(new LatLng(lat, lng));
		        markerOpt.title("I'm here !");
		        markerOpt.draggable(false);
		        markerOpt.anchor(0.5f, 0.5f);
		        markerOpt.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation));
		        mMap.addMarker(markerOpt);
		        
		        CameraPosition camPosition = new CameraPosition.Builder().target(new LatLng(lat, lng)).zoom(16).build();
		        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));
				
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello Google Map");
        setContentView(R.layout.google_map);
        
        textAddress = (EditText) findViewById(R.id.google_map_address);
        buttonSearch = (Button) findViewById(R.id.google_map_button);
    	buttonMyLocation = (Button) findViewById(R.id.myLocation_button);
        
        //location by latitude and altitude
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)){
        	provider = LocationManager.GPS_PROVIDER;
        	//provider = LocationManager.NETWORK_PROVIDER;
        }
        Location location = locationMgr.getLastKnownLocation(provider);
        if(location == null){
        	LocationListener locLis = new MyLocationListener(); 
        	locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locLis) ;
        }
        //double lat = location.getLatitude();
        //double lng = location.getLongitude();
        //System.out.println("-----location : latitude:" + lat + ", longitude : "+ lng);
        
        /*Analyst address */
        
        
        new Thread(new Runnable(){
			public void run(){
				url = "http://maps.googleapis.com/maps/api/geocode/json?address=3205+rue+de+verdun,+verdun,qc&sensor=false";
				
				//String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961352&sensor=false";
        
        HttpClient httpClient = new DefaultHttpClient();
        String responseData = "";
        try{
        	HttpResponse response = httpClient.execute(new HttpGet(url));
        	HttpEntity entity = response.getEntity(); 
        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
        	String line = "";
        	while ((line = bufferedReader.readLine()) != null){
        		responseData = responseData + line;
        	}
        	Message message = Message.obtain();
			message.obj = responseData;
			handler.sendMessage(message);
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        Gson gson = new Gson();
        TestResult testResult = gson.fromJson(responseData, TestResult.class);
        System.out.println("-----testResult:-----"+testResult);
			}
		}).start();	
        
        
        buttonSearch.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run(){
						url = "http://maps.googleapis.com/maps/api/geocode/json?address=";
						String strAddress = textAddress.getText().toString();
						url = url + strAddress.replace(" ", "+") + "&sensor=false";
						
						
						HttpClient httpClient = new DefaultHttpClient();
				        String responseData = "";
				        try{
				        	HttpResponse response = httpClient.execute(new HttpGet(url));
				        	HttpEntity entity = response.getEntity(); 
				        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
				        	String line = "";
				        	while ((line = bufferedReader.readLine()) != null){
				        		responseData = responseData + line;
				        	}
				        	Message message = Message.obtain();
							message.obj = responseData;
							handler.sendMessage(message);
				        }
				        catch (Exception e){
				        	e.printStackTrace();
				        }
				        Gson gson = new Gson();
				        TestResult testResult = gson.fromJson(responseData, TestResult.class);
				        System.out.println("-----testResult:-----"+testResult);
							}
						}).start();	
				
				
			}
		});
        
        
    }
    
    public class MyLocationListener implements LocationListener{
    	public void onLocationChanged(Location loc){
    		if(loc != null){
    			new GeoPoint((int) (loc.getLatitude() * 1E6), (int) (loc.getLongitude() * 1E6));
    			
    		}
    	}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
    }
    
    /*创建menu*/
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		//设置menu界面为res/menu/activity_menu.xml
		inflater.inflate(R.menu.activity_temp, menu);
		return true;
	}

	/*处理菜单事件*/
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//得到当前选中的MenuItem的ID,
		int item_id = item.getItemId();

		switch (item_id)
		{
			case R.id.go:
				/* 新建一个Intent对象 */
				Intent intent = new Intent();
				/* 指定intent要启动的类 */
				intent.setClass(GoogleMapActivity.this, GetOrderByBarcode.class);
				/* 启动一个新的Activity */
				startActivity(intent);
				/* 关闭当前的Activity */
				GoogleMapActivity.this.finish();
				break;
			case R.id.exit:
				GoogleMapActivity.this.finish();
				break;
		}
		return true;
	}    
    
    
}


