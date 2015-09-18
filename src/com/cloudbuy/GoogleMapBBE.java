package com.cloudbuy;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class GoogleMapBBE extends FragmentActivity implements OnMapReadyCallback {
	
	private GoogleMap map_bbe = null;
	static final LatLng home = new LatLng(45.4712270, -73.5641420);	
	static final LatLng MTL = new LatLng(45.5086699, -73.5539925);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello Google Map");
        setContentView(R.layout.google_map_bbe);
        
        MapFragment mapFragment = (MapFragment) getFragmentManager()
        	    .findFragmentById(R.id.map_fragment);      
        mapFragment.getMapAsync(this);        

        // Assign mapFragment to map_bbe, so we can operate indirectly mapFragment through map_bbe. 
        map_bbe = mapFragment.getMap();
        //map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map_bbe.addMarker(new MarkerOptions().position(MTL).title("Canada").snippet("BBE Team"));
        map_bbe.moveCamera(CameraUpdateFactory.newLatLngZoom(MTL, 12));
        
        MarkerOptions markerHome = new MarkerOptions();
        markerHome.position(home);
        markerHome.title("LINGdong's home");
        markerHome.draggable(true);
        map_bbe.addMarker(markerHome);
        
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
				intent.setClass(GoogleMapBBE.this, GetOrderByBarcode.class);
				/* 启动一个新的Activity */
				startActivity(intent);
				/* 关闭当前的Activity */
				GoogleMapBBE.this.finish();
				break;
			case R.id.exit:
				GoogleMapBBE.this.finish();
				break;
		}
		return true;
	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		// TODO Auto-generated method stub
		
	}    
}


