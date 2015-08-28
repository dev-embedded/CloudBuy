package com.cloudbuy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import com.domain.Order;
import com.tools.ApacheHttpClient;
import com.tools.JsonTools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DeliveryList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delivery_list);

		Button buttonLogout = (Button) findViewById(R.id.button_logout);
		Button buttonSelect = (Button) findViewById(R.id.button_select);
		Button buttonGetBarcode = (Button) findViewById(R.id.button_get_barcode);
		Button buttonReturn = (Button) findViewById(R.id.button_return);
		ListView listview = (ListView) this.findViewById(R.id.listView1);
		
		Intent intent = getIntent();
		ArrayList<Order> orderList = intent.getParcelableArrayListExtra("domain.order");

		if (!orderList.isEmpty()
				&& orderList.get(0).getOrderNo() != -1) {

			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

			for (Order order : orderList) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("orderNo", order.getOrderNo());
				item.put("barcode", order.getBarcode());
				item.put("userNo", order.getUserNo());
				item.put("orderSum", order.getOrderSum());
				data.add(item);
				System.out.println("barcode:"+order.getBarcode());
			}
			
			SimpleAdapter adapter = new SimpleAdapter(this, data,
					R.layout.delivery_list_item, new String[] {"orderNo", "barcode", "userNo","orderSum" }, 
						new int[] {R.id.listViewOrderNo,R.id.listViewBarcode,R.id.listViewUserNo,R.id.listViewOrderPay });

			listview.setAdapter(adapter);

			
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),"order list is empty !",Toast.LENGTH_SHORT);
			toast.show();
		}
		

		

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