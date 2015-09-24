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
import com.tools.ListViewAdapter;
import com.tools.ViewHolder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DeliveryList extends Activity {

	private ListView listview;
	private ListViewAdapter myAdapter;
	private int checkNum;
	private ArrayList<String> checkItem;
	//private String baseURL = "http://192.168.208.1:8080/CloudBuyPractice/GetOrderDetailForDelivery";
	private String baseURL = "http://198.100.155.40:8080/CloudBuyPractice/GetOrderDetailForDelivery";

	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// userPasswordText.setText((CharSequence) msg.obj.toString());
			String result = msg.obj.toString();
			System.out.println("result : " + result);

			try {
				if (result != "-1") {
					Intent intent = new Intent();
					ArrayList<Order> orderDetailList = JsonTools
							.AnalysisOrderDetailList(result);
					// System.out.println("UserLogin.java:address:" +
					// orderList.get(0).getAddress());
					System.out
							.println("--------DeliveryList.java:orderDetailList.size(): "
									+ orderDetailList.get(0).getOrderDetail()
											.size());
					// intent.putParcelableArrayListExtra("domain.orderDetail",orderDetailList);

					Bundle bundle = new Bundle();
					bundle.putString("domain.orderDetail", result);
					intent.putExtras(bundle);

					intent.setClass(DeliveryList.this,
							OrderDetailsActivity.class);
					startActivity(intent);
					DeliveryList.this.finish();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"System error : order detail didn't be found !",
							Toast.LENGTH_SHORT);
					toast.show();

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delivery_list);

		Button buttonLogout = (Button) findViewById(R.id.button_logout);
		Button buttonSelect = (Button) findViewById(R.id.button_select);
		Button buttonGetBarcode = (Button) findViewById(R.id.button_get_barcode);
		Button buttonReturn = (Button) findViewById(R.id.button_return);
		listview = (ListView) this.findViewById(R.id.listView1);

		Intent intent = getIntent();
		ArrayList<Order> orderList = intent
				.getParcelableArrayListExtra("domain.order");

		System.out.println("DeliveryList.java:address:"
				+ orderList.get(0).getAddress());

		if (!orderList.isEmpty() && orderList.get(0).getOrderNo() != -1) {

			// ********************
			// method 2

			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

			for (Order order : orderList) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("orderNo", order.getOrderNo());
				item.put("orderSum", "$" + order.getOrderSum());
				item.put("address", order.getAddress());
				item.put("postalcode", order.getPostalcode());
				data.add(item);
				System.out.println("address:" + order.getAddress());
			}

			SimpleAdapter adapter = new SimpleAdapter(this, data,
					R.layout.delivery_list_item, new String[] { "orderNo",
							"orderSum", "address", }, new int[] {
							R.id.listViewOrderNo, R.id.listViewOrderPay,
							R.id.listViewAddress });
			// listview.setAdapter(adapter);

			// ********************
			// method 1
			// String[] strs = new String[] {"first", "second", "third",
			// "fourth", "fifth"};
			// listview.setAdapter(new
			// ArrayAdapter<Order>(this,android.R.layout.simple_list_item_multiple_choice,
			// orderList));
			// listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

			// ********************
			// method 3
			myAdapter = new ListViewAdapter(orderList, this);
			listview.setAdapter(myAdapter);

		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"order list is empty !", Toast.LENGTH_SHORT);
			toast.show();
		}

		checkItem = new ArrayList<String>();
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ViewHolder holder = (ViewHolder) view.getTag();
				holder.cbIsSelected.toggle();

				TextView orderSelected = (TextView) view
						.findViewById(R.id.listViewOrderNo);
				if (holder.cbIsSelected.isChecked() == true) {
					checkNum++;
					checkItem.add(orderSelected.getText().toString());
					System.out.println("DeliveryList.java:checkItem size: "
							+ checkItem.size());
					// System.out.println("DeliveryList.java:cbIsSelected is true: "
					// + orderSelected.getText());
				} else {
					checkNum--;
					checkItem.remove(orderSelected.getText().toString());
					System.out.println("DeliveryList.java:checkItem size: "
							+ checkItem.size());
					// System.out.println("DeliveryList.java:cbIsSelected is false: "
					// + orderSelected.getText());
				}

				// TextView orderSelected =
				// (TextView)view.findViewById(R.id.listViewOrderNo);
				// Toast.makeText(getApplicationContext(),"you selected No."+position+"Item，orderNo:："+orderSelected.getText(),Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(),
						"you selected " + checkNum + "Items，",
						Toast.LENGTH_SHORT).show();

			}

		});

		buttonReturn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DeliveryList.this, Activity_temp.class);
				startActivity(intent);
				DeliveryList.this.finish();
			}
		});

		buttonSelect.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (checkNum != 0) {
					new Thread(new Runnable() {
						public void run() {
							String res = null;
							List<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("orderCount",
									String.valueOf(checkItem.size())));
							for (int i = 0; i < checkItem.size(); i++) {
								params.add(new BasicNameValuePair(String
										.valueOf(i), checkItem.get(i)
										.toString()));
							}

							ApacheHttpClient httpClient = new ApacheHttpClient();

							try {
								res = httpClient.httpPost(baseURL, params);
								System.out.println("reponse for POST :" + res);
								if (res != null) {
									Message message = Message.obtain();
									message.obj = res;
									handler.sendMessage(message);
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}).start();
				} else {
					Toast toast = Toast
							.makeText(getApplicationContext(),
									"selected order list is null !",
									Toast.LENGTH_SHORT);
					toast.show();
				}

			}
		});

	}

}
