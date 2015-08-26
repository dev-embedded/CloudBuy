
package com.cloudbuy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.domain.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DeliveryList extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delivery_list);

		Button buttonLogout = (Button) findViewById(R.id.button_logout);

		Button buttonSelect = (Button) findViewById(R.id.button_select);

		Button buttonGetBarcode = (Button) findViewById(R.id.button_get_barcode);

		Button buttonReturn = (Button) findViewById(R.id.button_return);
		
		ArrayList<User> userList = new ArrayList<User>();
		
		Intent intent = getIntent();
		
		//orderList = intent.getParcelableArrayListExtra("com.cloudbuy.domain.order");
		userList = intent.getParcelableArrayListExtra("domain.user");
		
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		
		for(User user : userList){
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("id", user.getUserNo());
			item.put("postalcode", user.getPostalCode());
			item.put("address", user.getAddress());
			data.add(item);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.delivery_list_item,
				new String[]{"id", "postalcode", "address"},
				new int[]{R.id.listViewOrderNo, R.id.listViewOrderPay, R.id.listViewOrderAddress});
		
		ListView listview = (ListView) this.findViewById(R.id.listView1);
		
		listview.setAdapter(adapter);

			
		
		buttonReturn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(DeliveryList.this, Activity_temp.class);

				startActivity(intent);

				DeliveryList.this.finish();
		}
	});
	}
}