package com.cloudbuy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.google.android.gms.maps.model.PolylineOptions;
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
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GoogleMapActivity extends Activity {
	
	//Map
	private GoogleMap mMap;
	private TextView txtOutput;
	private Marker markerMe;
	private List<Address> address;
	private String url;
	private EditText textAddressEnd = null;
	private EditText textAddressBegin = null;
	private Button buttonSearch = null;
	private Button buttonMyLocation = null;
	private Button buttonNavigation = null;
	
	//GPS
	private LocationManager locationMgr;
	private String provider;
	
	private Double startLat;
	private Double startLng;
	private Double endLat;
	private Double endLng;
	
	
	public Double getStartLat() {
		return startLat;
	}

	public void setStartLat(Double startLat) {
		this.startLat = startLat;
	}

	public Double getStartLng() {
		return startLng;
	}

	public void setStartLng(Double startLng) {
		this.startLng = startLng;
	}

	public Double getEndLat() {
		return endLat;
	}

	public void setEndLat(Double endLat) {
		this.endLat = endLat;
	}

	public Double getEndLng() {
		return endLng;
	}

	public void setEndLng(Double endLng) {
		this.endLng = endLng;
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			String result =  msg.obj.toString();
			System.out.println("result : " + result);
			Toast toast = Toast.makeText(getApplicationContext(), "msg.what="+msg.what, Toast.LENGTH_SHORT);
			toast.show();
			
			switch(msg.what){
				case 1:
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
						break;
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				case 2:
					List<LatLng> points = getPoints(result);
					
					for(int i=0;i<points.size();i++){
						System.out.println("point["+i+"]-->"+points.get(i));
					}

					try {
						// Draw route info
						//mMap.addMarker(new MarkerOptions().position(points.get(0)).title("I'm here").visible(true));
						PolylineOptions lineOptions = new PolylineOptions();
						lineOptions.width(5);
						lineOptions.color(Color.BLUE);
						for (int i = 0; i < points.size() ; i++) {
							lineOptions.add(points.get(i));
						}
						mMap.addPolyline(lineOptions);
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				case 3:
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
				        setStartLat(lat);
				        setStartLng(lng);
				        System.out.println("startAddress:<---->"+getStartLat()+","+getStartLng());
						break;
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				case 4:
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
				        setEndLat(lat);
				        setEndLng(lng);
				        System.out.println("endAddress:<---->"+getEndLat()+","+getEndLng());
						break;
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
			
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello Google Map");
        setContentView(R.layout.google_map);
        
        textAddressEnd = (EditText) findViewById(R.id.google_map_address_end);
        textAddressBegin = (EditText) findViewById(R.id.google_map_address_begin);
        buttonSearch = (Button) findViewById(R.id.google_map_button);
    	buttonMyLocation = (Button) findViewById(R.id.myLocation_button);
    	buttonNavigation = (Button) findViewById(R.id.navigation_button);
    	
    	textAddressBegin.setText("3205 rue de verdun, verdun, qc");
    	
    	//get user address for destination taget.
    	Intent intent = getIntent();
		Bundle bundle=getIntent().getExtras();  
	    String userAddressForDestination=bundle.getString("user.address");
	    textAddressEnd.setText(userAddressForDestination);
        
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
        String startAddTemp = textAddressBegin.getText().toString();
		String endAddTemp = textAddressEnd.getText().toString();
		getLatLng(startAddTemp,3);
		System.out.println("startAddress:---->"+getStartLat()+","+getStartLng());
		getLatLng(endAddTemp,4);
		System.out.println("endAddress:---->"+getEndLat()+","+getEndLng());
        
        
        new Thread(new Runnable(){
			public void run(){
				url = "http://maps.googleapis.com/maps/api/geocode/json?address=3205+rue+de+verdun,+verdun,qc&sensor=false";
				
				//url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961352&sensor=false";
        
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
        	message.what = 1;
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
						String strAddress = textAddressEnd.getText().toString();
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
				        	message.what = 1;
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
        
        buttonNavigation.setOnClickListener(new Button.OnClickListener() {
        	
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						//url = "http://maps.google.com/maps/api/directions/xml?origin=45.469727,-73.569809&destination=45.466876,-73.6234319&sensor=false&mode=driving";
						url = "http://maps.google.com/maps/api/directions/xml?origin="+getStartLat()+","+getStartLng()+"&destination="+getEndLat()+","+getEndLng()+"&sensor=false&mode=driving";
						System.out.println("url for navigation : --->"+url);

						HttpClient httpClient = new DefaultHttpClient();
						String responseData = "";
						try {
							HttpResponse response = httpClient
									.execute(new HttpGet(url));
							HttpEntity entity = response.getEntity();
							BufferedReader bufferedReader = new BufferedReader(
									new InputStreamReader(entity.getContent()));
							String line = "";
							while ((line = bufferedReader.readLine()) != null) {
								responseData = responseData + line;
							}
							Message message = Message.obtain();
							message.what = 2;
							message.obj = responseData;
							handler.sendMessage(message);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
				
				
			}
		});
    }
    
    private void getLatLng(final String strAdd, final int flag){
    	ArrayList<Double> latLng = new ArrayList<Double>();
    	new Thread(new Runnable(){
			public void run(){
				url = "http://maps.googleapis.com/maps/api/geocode/json?address=";
				//String strAddress = textAddressEnd.getText().toString();
				url = url + strAdd.replace(" ", "+") + "&sensor=false";
				
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
		        	message.what = flag;
					message.obj = responseData;
					handler.sendMessage(message);
		        }
		        catch (Exception e){
		        	e.printStackTrace();
		        }
		        Gson gson = new Gson();
		        TestResult testResult = gson.fromJson(responseData, TestResult.class);
		        //System.out.println("-----testResult:-----"+testResult);
					}
				}).start();	
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
    
	private List<LatLng> getPoints(String strResult){
		List<LatLng> points=new ArrayList<LatLng>(); 
		 if (-1 == strResult.indexOf("<status>OK</status>")){  
		            return null;
		        } 
		        
		        int pos = strResult.indexOf("<overview_polyline>");  
		        pos = strResult.indexOf("<points>", pos + 1);  
		        int pos2 = strResult.indexOf("</points>", pos);  
		        strResult = strResult.substring(pos + 8, pos2);  
		          
		        List<GeoPoint> geoPoints = decodePoly(strResult); 
		     //   Log.i("tag", "geoPoints:"+geoPoints.toString());
		        LatLng ll;
		        Log.i("tag", "geopoints.size:"+geoPoints.size());
		        for(Iterator<GeoPoint> gpit=geoPoints.iterator();gpit.hasNext();){
		               GeoPoint gp=gpit.next();
		            double latitude=gp.getLatitudeE6();
		            latitude=latitude/1000000;
		     //       Log.i("tag", "latitude:"+latitude);
		            double longitude=gp.getLongitudeE6();
		            longitude=longitude/1000000;
		    //        Log.i("tag", "longitude:"+longitude);
		            ll=new LatLng(latitude, longitude);
		            points.add(ll);
		        }
		   //    Log.i("tag", "points:"+points.toString());

		        return points;
	}
	
	/** 
     * Parses the returned lines of code of XML overview_polyline 
     *  
     * @return List<GeoPoint>
     */  
    private List<GeoPoint> decodePoly(String encoded) {  
          
        List<GeoPoint> poly = new ArrayList<GeoPoint>();  
        int index = 0, len = encoded.length();  
        int lat = 0, lng = 0;  
  
        while (index < len) {  
            int b, shift = 0, result = 0;  
            do {  
                b = encoded.charAt(index++) - 63;  
                result |= (b & 0x1f) << shift;  
                shift += 5;  
            } while (b >= 0x20);  
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));  
            lat += dlat;  
  
            shift = 0;  
            result = 0;  
            do {  
                b = encoded.charAt(index++) - 63;  
                result |= (b & 0x1f) << shift;  
                shift += 5;  
            } while (b >= 0x20);  
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));  
            lng += dlng;  
  
            GeoPoint p = new GeoPoint((int) ((lat / 1E5) * 1E6),(int) ((lng / 1E5) * 1E6));  
            poly.add(p);  
        }  
  
        return poly;  
    }
}


