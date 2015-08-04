package com.cloudbuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class GoogleMap extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* 设置显示google_map.xml布局 */
		setContentView(R.layout.google_map);
		/* findViewById(R.id.google_map_button1)取得布局google_map.xml中的google_map_button1 */
		Button button = (Button) findViewById(R.id.google_map_button1);
		/* 监听button的事件信�? */
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				/* 新建�?个Intent对象 */
				//Intent intent = new Intent();
				/* 指定intent要启动的�? */
				//intent.setClass(GoogleMap.this, AddressEnter.class);
				/* 启动�?个新的Activity */
				//startActivity(intent);
				/* 关闭当前的Activity */
				GoogleMap.this.finish();
			}
		});
	
	}
}

