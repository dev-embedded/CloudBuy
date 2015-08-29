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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DeliveryList extends Activity  {
	
	private ListView listview;
	private String orderNoSelected;
	private ListViewAdapter myAdapter;
	private int checkNum;

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
		ArrayList<Order> orderList = intent.getParcelableArrayListExtra("domain.order");
		
		System.out.println("DeliveryList.java:address:" + orderList.get(0).getAddress());

		if (!orderList.isEmpty()
				&& orderList.get(0).getOrderNo() != -1) {

			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

			for (Order order : orderList) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("orderNo", order.getOrderNo());
				item.put("orderSum", "$"+order.getOrderSum());
				item.put("address", order.getAddress());
				item.put("postalcode", order.getPostalcode());
				data.add(item);
				System.out.println("address:"+order.getAddress());
			}
			
			//********************
			//method 2
			SimpleAdapter adapter = new SimpleAdapter(this, data,
					R.layout.delivery_list_item, new String[] {"orderNo", "orderSum", "address",}, 
						new int[] {R.id.listViewOrderNo,R.id.listViewOrderPay,R.id.listViewAddress });
			//listview.setAdapter(adapter);

			//********************
			//method 1
			//String[] strs = new String[] {"first", "second", "third", "fourth", "fifth"};
			//listview.setAdapter(new ArrayAdapter<Order>(this,android.R.layout.simple_list_item_multiple_choice, orderList));
			//listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			
			//********************
			//method 3
			myAdapter = new ListViewAdapter(orderList,this);
			listview.setAdapter(myAdapter);
			
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),"order list is empty !",Toast.LENGTH_SHORT);
			toast.show();
		}
		
		listview.setOnItemClickListener(new OnItemClickListener(){   
			@Override   
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {  
				
				ViewHolder holder = (ViewHolder) view.getTag();
				holder.cbIsSelected.toggle();
				
				if (holder.cbIsSelected.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
				
	        	//TextView orderSelected = (TextView)view.findViewById(R.id.listViewOrderNo);
	            //Toast.makeText(getApplicationContext(),"you selected No."+position+"Item，orderNo:："+orderSelected.getText(),Toast.LENGTH_SHORT).show(); 
				Toast.makeText(getApplicationContext(),"you selected "+checkNum+"Items，",Toast.LENGTH_SHORT).show(); 
				
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
		
		
	}
	

}