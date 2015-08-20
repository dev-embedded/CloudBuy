package com.cloudbuy;

import java.util.ArrayList;
import com.domain.Order;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class DeliveryList extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delivery_list);
		
		
		//create ArrayList
		ArrayList<Order> orderList = new ArrayList<Order>();
		Intent intent = getIntent();
		
		orderList = intent.getParcelableArrayListExtra("com.cloudbuy.domain.order");
		
		//获取xml布局中的控件
		ListView listView = (ListView) findViewById(R.id.listview); 
		//listView.setText
		
		//create ArrayAdapter
		final ArrayAdapter<Order> arrayAdapter;
		arrayAdapter = new ArrayAdapter<Order>(this, 
					android.R.layout.simple_expandable_list_item_1,orderList);
		
		//bundle ListView to adapter
		listView.setAdapter(arrayAdapter);
		
		//setContentView(listView);
		
		//Bundle bundle = this.getIntent().getExtras();
		
				
		Button buttonLogout = (Button) findViewById(R.id.button_logout);

		Button buttonSelect = (Button) findViewById(R.id.button_select);

		Button buttonGetBarcode = (Button) findViewById(R.id.button_get_barcode);

		Button buttonReturn = (Button) findViewById(R.id.button_return);


						

		buttonReturn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(DeliveryList.this, Activity_temp.class);

				startActivity(intent);

				DeliveryList.this.finish();
		}
	});
		
		buttonLogout.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(DeliveryList.this, Activity_temp.class);

				startActivity(intent);

				DeliveryList.this.finish();
		}
	});
		
		buttonGetBarcode.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(DeliveryList.this, GetOrderByBarcode.class);

				startActivity(intent);

				DeliveryList.this.finish();
		}
	});
	}
	
	
	/*private ArrayList<Order> getData(){
		ArrayList<Order> arrayList =  new ArrayList<Order>();
		return arrayList;
	}*/
		
	}

