package com.cloudbuy;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class GoogleMapApp extends FragmentActivity {
	
	private GoogleMap map = null;
	static final LatLng MTL = new LatLng(45.5086699, -73.5539925);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello Google Map");
        setContentView(R.layout.google_map_app);              
        
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        Marker mk= map.addMarker(new MarkerOptions().position(MTL).title("Montreal").snippet("BBE Team"));
        
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MTL, 12));
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
				intent.setClass(GoogleMapApp.this, GetOrderByBarcode.class);
				/* 启动一个新的Activity */
				startActivity(intent);
				/* 关闭当前的Activity */
				GoogleMapApp.this.finish();
				break;
			case R.id.exit:
				GoogleMapApp.this.finish();
				break;
		}
		return true;
	}    
}


