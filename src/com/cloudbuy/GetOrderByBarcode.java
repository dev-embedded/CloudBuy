package com.cloudbuy;

import java.util.ArrayList;

import com.domain.Order;
import com.domain.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetOrderByBarcode extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_order_by_barcode);
		
		//ArrayList<Order> orderList = new ArrayList<Order>();
		ArrayList<User> userList = new ArrayList<User>();
		
		Intent intent = getIntent();
		
		//orderList = intent.getParcelableArrayListExtra("com.cloudbuy.domain.order");
		userList = intent.getParcelableArrayListExtra("com.cloudbuy.domain.user");
		
		//System.out.println("--------order1 : " + orderList.get(0).getOrderSum());
		System.out.println("--------user1 : " + userList.get(0).getAddress());
		
		
		EditText barcodeValue = null;
		barcodeValue = (EditText) findViewById(R.id.barcode_value);
		barcodeValue.setText(userList.get(0).getAddress());
		
		Button buttonSearch = (Button) findViewById(R.id.search);
		
		buttonSearch.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				
				intent.setClass(GetOrderByBarcode.this, Activity_temp.class);

				startActivity(intent);
				
				GetOrderByBarcode.this.finish();
			}
		});
	}

}
