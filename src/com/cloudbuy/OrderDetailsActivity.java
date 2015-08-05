package com.cloudbuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_details);

		Button buttonPrevOrder = (Button) findViewById(R.id.button_prev_order);
		Button buttonNextOrder = (Button) findViewById(R.id.button_next_order);
		Button buttonReturnOrderList = (Button) findViewById(R.id.button_return_order_list);		
		Button buttonSignOrder = (Button) findViewById(R.id.button_sign_order);
		Button buttonOrderMap = (Button) findViewById(R.id.button_order_map);
	
		buttonPrevOrder.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				//intent.setClass(OrderDetailsActivity.this, xxxxxx.class);
				//startActivity(intent);
				//OrderDetailsActivity.this.finish();
			}
		});
		
		buttonNextOrder.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				//intent.setClass(OrderDetailsActivity.this, xxxxxx.class);
				//startActivity(intent);
				//OrderDetailsActivity.this.finish();
			}
		});

		buttonReturnOrderList.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(OrderDetailsActivity.this, Activity_temp.class);
				startActivity(intent);
				OrderDetailsActivity.this.finish();
			}
		});		
		
		buttonSignOrder.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				//intent.setClass(OrderDetailsActivity.this, xxxxxx.class);
				//startActivity(intent);
				//OrderDetailsActivity.this.finish();
			}
		});
		
		buttonOrderMap.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(OrderDetailsActivity.this, GoogleMap.class);
				startActivity(intent);
				OrderDetailsActivity.this.finish();
			}
		});
		
	}
}
