package com.tools;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.domain.Order;
import com.domain.OrderDetail;
import com.domain.User;

public class JsonTools {

	public static ArrayList<User> AnalysisUserList(String jsonStr)
			throws JSONException {
		JSONArray jsonArray = null;
		ArrayList<User> list = new ArrayList<User>();
		jsonArray = new JSONArray(jsonStr);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			User userTemp = new User();
			userTemp.setUserNo(Integer.parseInt(jsonObj.getString("userNo")));
			userTemp.setPostalCode(jsonObj.getString("postalcode"));
			userTemp.setAddress(jsonObj.getString("address"));
			list.add(userTemp);
		}
		return list;
	}
	
	public static ArrayList<Order> AnalysisOrderList(String jsonStr)
			throws JSONException {
		JSONArray jsonArray = new JSONArray(jsonStr);
		ArrayList<Order> list = new ArrayList<Order>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			Order orderTemp = new Order();
			orderTemp.setOrderNo(Integer.parseInt(jsonObj.getString("orderNo")));
			orderTemp.setOrderSum(Double.parseDouble(jsonObj.getString("orderSum")));
			orderTemp.setAddress(jsonObj.getString("address"));
			orderTemp.setPostalcode(jsonObj.getString("postalcode"));
			list.add(orderTemp);
			System.out.println("JsonTools.java:address:" + orderTemp.getAddress());
		}
		return list;
	}
	
	public static ArrayList<Order> AnalysisOrderDetailList(String jsonStr)
			throws JSONException {
		JSONArray jsonArray = null;
		ArrayList<Order> list = new ArrayList<Order>();
		jsonArray = new JSONArray(jsonStr);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			Order orderTemp = new Order();
			orderTemp.setOrderNo(Integer.parseInt(jsonObj.getString("orderNo")));
			orderTemp.setOrderSum(Double.parseDouble(jsonObj.getString("orderSum")));
			orderTemp.setFirstName(jsonObj.getString("firstName"));
			orderTemp.setAddress(jsonObj.getString("address"));
			orderTemp.setPostalcode(jsonObj.getString("postalcode"));
			
			JSONArray jsonArrayTemp = new JSONArray(jsonObj.getString("orderDetail"));
			System.out.println("-----JsonTools.java:jsonArrayTemp(orderDetail): "+jsonArrayTemp.toString());
			ArrayList<OrderDetail> detailListTemp = new ArrayList<OrderDetail>();
			for(int j=0;j<jsonArrayTemp.length();j++){
				JSONObject jsonObjTemp = jsonArrayTemp.getJSONObject(j);
				OrderDetail orderDetailTemp = new OrderDetail();
				orderDetailTemp.setProductNo(Integer.parseInt(jsonObjTemp.getString("productNo")));
				orderDetailTemp.setProductName(jsonObjTemp.getString("productName"));
				orderDetailTemp.setQuantity(Integer.parseInt(jsonObjTemp.getString("quantity")));
				
				detailListTemp.add(orderDetailTemp);
			}
			
			orderTemp.setOrderDetail(detailListTemp);
			
			list.add(orderTemp);
			System.out.println("JsonTools.java:orderDetail.productName:" + orderTemp.getOrderDetail().get(0).getProductName());
		}
		return list;
	}

}
