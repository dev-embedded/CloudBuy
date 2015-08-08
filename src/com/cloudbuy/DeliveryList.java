
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
import java.io.Serializable;


public class DeliveryList extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delivery_list);
		ListView listView;
		
		listView = (ListView) findViewById(R.id.listview); 
		
		Bundle bundle = this.getIntent().getExtras();
		
		ArrayList<Order> orderList = (ArrayList<Order>)bundle.getSerializable ("orderList");
		
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
	
	
	
		
	}

