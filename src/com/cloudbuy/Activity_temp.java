package com.cloudbuy;

import java.util.ArrayList;

import com.domain.Order;
import com.domain.OrderDetail;
import com.domain.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_temp extends Activity {
	
	private ArrayList<Order> orderList;
	private ArrayList<User> userList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_temp);

		Button buttonLogin = (Button) findViewById(R.id.button_login);

		Button buttonOrderList = (Button) findViewById(R.id.button_order_list);

		Button buttonBarcode = (Button) findViewById(R.id.button_barcode);

		Button buttonOrderDetails = (Button) findViewById(R.id.button_order_details);

		Button buttonDeliveryMap = (Button) findViewById(R.id.button_delivery_map);
		
		//create two users : user1 and user2
		userList = new ArrayList<User>();
		
		//the first user
		User user1 = new User(1);
		user1.setAddress("6177 sherbrook ouest montreal");
		user1.setPostalCode("H4B 1L9");
		
		//the second user
		User user2 = new User(2);
		user2.setAddress("3205 rue de verdun");
		user2.setPostalCode("H4G 1j9");
		
		userList.add(user1);
		userList.add(user2);
		
		
		
		//create 2 order lists : orderList1 and orderList2
		orderList = new ArrayList<Order>();
		
		//begin of the first order and order detail 
		
		Order order1 = new Order();
		
		order1.setOrderNo(1);
		order1.setUserNo(1);
		order1.setOrderSum(70.99);
		order1.setFlagPay("0");  //not pay
		order1.setFlagDel("1");  //not delete
		
		OrderDetail orderDetail11 = new OrderDetail(order1.getOrderNo());
		orderDetail11.setProductNo(1);
		orderDetail11.setQuantity(5);
		
		OrderDetail orderDetail12 = new OrderDetail(order1.getOrderNo());
		orderDetail12.setProductNo(2);
		orderDetail12.setQuantity(10);
		
		ArrayList <OrderDetail> orderDetailList1 = new ArrayList <OrderDetail>();
		orderDetailList1.add(orderDetail11);
		orderDetailList1.add(orderDetail12);
		
		order1.setOrderDetail(orderDetailList1);
		//end of the first order and order detail
		
		//begin of the second order and order detail 
		
		Order order2 = new Order();
		
		order2.setOrderNo(2);
		order2.setUserNo(2);
		order2.setOrderSum(36.99);
		order2.setFlagPay("0");  //not pay
		order2.setFlagDel("1");  //not delete
		
		OrderDetail orderDetail21 = new OrderDetail(order1.getOrderNo());
		orderDetail11.setProductNo(3);
		orderDetail11.setQuantity(2);
		
		OrderDetail orderDetail22 = new OrderDetail(order1.getOrderNo());
		orderDetail22.setProductNo(4);
		orderDetail22.setQuantity(20);
		
		ArrayList <OrderDetail> orderDetailList2 = new ArrayList <OrderDetail>();
		orderDetailList1.add(orderDetail21);
		orderDetailList1.add(orderDetail22);
		
		order1.setOrderDetail(orderDetailList2);
		//end of the second order and order detail
		
		System.out.println("-----user list-----");
		System.out.println("userNo" + "user Address");
		System.out.println(userList.get(0).getUserNo() + "          " + userList.get(0).getAddress());
		System.out.println(userList.get(1).getUserNo() + "          " + userList.get(1).getAddress());
		
		System.out.println("-----order list-----");
		System.out.println(userList.get(0).getUserNo() + "          " + userList.get(0).getAddress());
		
		
		/*button listener for layout01:user login 
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */

		buttonLogin.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(Activity_temp.this, UserLogin.class);

				startActivity(intent);

				Activity_temp.this.finish();
			}
		});

		
		/*button listener for layout02:order list for delivery 
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */
		buttonOrderList.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				
				intent.putParcelableArrayListExtra("com.cloudbuy.domain.user", userList);

				intent.setClass(Activity_temp.this, DeliveryList.class);

				startActivity(intent);

				Activity_temp.this.finish();
			}
		});

		/*button listener for layout03:get order by barcode
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */
		buttonBarcode.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				//intent.putParcelableArrayListExtra("com.cloudbuy.domain.order", orderList);
				intent.putParcelableArrayListExtra("com.cloudbuy.domain.user", userList);
				
				intent.setClass(Activity_temp.this, GetOrderByBarcode.class);

				startActivity(intent);

				Activity_temp.this.finish();
			}
		});
		
		/*button listener for layout04:order detail for delivery 
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */
		buttonOrderDetails.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(Activity_temp.this, OrderDetailsActivity.class);

				startActivity(intent);

				Activity_temp.this.finish();
			}
		});
		
		/*
		 * button listenner for layout05:delivery map for order 
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */
		buttonDeliveryMap.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				/* 新建一个Intent对象 */
				Intent intent = new Intent();
				/* 指定intent要启动的类 */
				intent.setClass(Activity_temp.this, GoogleMap.class);
				/* 启动一个新的Activity */
				startActivity(intent);
				/* 关闭当前的Activity */
				Activity_temp.this.finish();
			}
		}
		);
	}
}
