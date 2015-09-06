package com.cloudbuy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import junit.framework.TestResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class GoogleMapActivity extends Activity {
	
	//Map
	private GoogleMap mMap;
	private TextView txtOutput;
	private Marker markerMe;
	
	//GPS
	private LocationManager locationMgr;
	private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello Google Map");
        setContentView(R.layout.google_map);
        
        //location by latitude and altitude
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
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
        
        /*Analyst address 
        String url = "http://maps.googleapis.com/maps/api/geocode/json?address=3205+rue+de+verdun,+verdun,qc&sensor=false";
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
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        Gson gson = new Gson();
        TestResult testResult = gson.fromJson(responseData, TestResult.class);
        System.out.println(testResult);
        
        */
        
        
        double lat = 21.946567;
        double lng = 120.798713;
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
    
    
}


