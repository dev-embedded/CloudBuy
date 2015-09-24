package com.cloudbuy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;

import com.domain.Order;
import com.domain.OrderDetail;
import com.tools.JsonTools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class OrderDetailsActivity extends Activity {

	private int cursor;
	private ArrayList<Order> orderList;
	private ArrayList<OrderDetail> orderDetailList;
	private SimpleAdapter adapter;
	private List<HashMap<String, Object>> data;

	private Button buttonPrevOrder;
	private Button buttonNextOrder;
	private Button buttonReturnOrderList;
	private Button buttonSignOrder;
	private Button buttonOrderMap;

	private EditText etOrderNo;
	private EditText etOrderSum;
	private EditText etUserName;
	private EditText etPostalcode;
	private EditText etAddress;
	private EditText etProductNo;
	private EditText etProductName;
	private EditText etQuantity;

	private ListView listview;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_details);

		buttonPrevOrder = (Button) findViewById(R.id.button_prev_order);
		buttonNextOrder = (Button) findViewById(R.id.button_next_order);
		buttonReturnOrderList = (Button) findViewById(R.id.button_return_order_list);
		buttonSignOrder = (Button) findViewById(R.id.button_sign_order);
		buttonOrderMap = (Button) findViewById(R.id.button_order_map);

		etOrderNo = (EditText) findViewById(R.id.editText2);
		etOrderSum = (EditText) findViewById(R.id.editText18);
		etUserName = (EditText) findViewById(R.id.editText4);
		etPostalcode = (EditText) findViewById(R.id.editText19);
		etAddress = (EditText) findViewById(R.id.editText6);

		listview = (ListView) this.findViewById(R.id.listViewDetail);

		Intent intent = getIntent();
		//ArrayList<Order> orderList = intent.getParcelableArrayListExtra("domain.orderDetail");
		Bundle bundle=getIntent().getExtras();  
	    String sOrderList=bundle.getString("domain.orderDetail");
	    
	    
	    
		try {
			orderList = JsonTools.AnalysisOrderDetailList(sOrderList);
			System.out.println("--------OrderDetailActivity.java:orderDetailList.size(): "+orderList.get(0).getOrderDetail().size());
			System.out.println("--------OrderDetailActivity:getFirstName: "+orderList.get(0).getFirstName());
			etOrderNo.setText(String.valueOf(orderList.get(0).getOrderNo()));
			etOrderSum.setText(String.valueOf("$"+orderList.get(0).getOrderSum()));
			etUserName.setText(orderList.get(0).getFirstName());
			etPostalcode.setText(orderList.get(0).getPostalcode());
			etAddress.setText(orderList.get(0).getAddress());
			
			
			// ********************
			// method 2

			data = new ArrayList<HashMap<String, Object>>();
			orderDetailList = orderList.get(0).getOrderDetail();
			System.out.println("--------OrderDetailActivity.java:orderDetailList.size(): "+orderDetailList.size());

			for (OrderDetail orderDetail : orderDetailList){
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("productNo", orderDetail.getProductNo());
				item.put("productName", orderDetail.getProductName());
				item.put("quantity", orderDetail.getQuantity());
				data.add(item);
			}

			adapter = new SimpleAdapter(this, data,
					R.layout.order_details_item, new String[] { "productNo",
							"productName", "quantity", }, new int[] {
							R.id.listViewProductNo, R.id.listViewProductName,
							R.id.listViewQuantity });
			listview.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		

		buttonPrevOrder.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(cursor>0){
					cursor--;
					
					etOrderNo.setText(String.valueOf(orderList.get(cursor).getOrderNo()));
					etOrderSum.setText("$"+String.valueOf(orderList.get(cursor).getOrderSum()));
					etUserName.setText(orderList.get(cursor).getFirstName());
					etPostalcode.setText(orderList.get(cursor).getPostalcode());
					etAddress.setText(orderList.get(cursor).getAddress());
					
					// ********************
					// method 2
					orderDetailList = orderList.get(cursor).getOrderDetail();
					//System.out.println("--------OrderDetailActivity.java:orderDetailList.size(): "+orderDetailList.size());

					data.clear();
					for (OrderDetail orderDetail : orderDetailList){
						HashMap<String, Object> item = new HashMap<String, Object>();
						item.put("productNo", orderDetail.getProductNo());
						item.put("productName", orderDetail.getProductName());
						item.put("quantity", orderDetail.getQuantity());
						data.add(item);
					}
					adapter.notifyDataSetChanged();

				}
			}
		});

		buttonNextOrder.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				
				if(cursor<orderList.size()-1){
					cursor++;
					
					etOrderNo.setText(String.valueOf(orderList.get(cursor).getOrderNo()));
					etOrderSum.setText("$"+String.valueOf(orderList.get(cursor).getOrderSum()));
					etUserName.setText(orderList.get(cursor).getFirstName());
					etPostalcode.setText(orderList.get(cursor).getPostalcode());
					etAddress.setText(orderList.get(cursor).getAddress());
					
					// ********************
					// method 2
					orderDetailList = orderList.get(cursor).getOrderDetail();
					//System.out.println("--------OrderDetailActivity.java:orderDetailList.size(): "+orderDetailList.size());

					data.clear();
					for (OrderDetail orderDetail : orderDetailList){
						HashMap<String, Object> item = new HashMap<String, Object>();
						item.put("productNo", orderDetail.getProductNo());
						item.put("productName", orderDetail.getProductName());
						item.put("quantity", orderDetail.getQuantity());
						data.add(item);
					}
					adapter.notifyDataSetChanged();
					

				}
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
				// intent.setClass(OrderDetailsActivity.this, xxxxxx.class);
				// startActivity(intent);
				// OrderDetailsActivity.this.finish();
			}
		});

		buttonOrderMap.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("user.address", etAddress.getText().toString().trim());
				intent.putExtras(bundle);
				
				intent.setClass(OrderDetailsActivity.this, GoogleMapActivity.class);
				startActivity(intent);
				OrderDetailsActivity.this.finish();
			}
		});
		
	}
}
