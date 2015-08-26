package com.tools;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.domain.User;

public class JsonTools  {
	public static ArrayList<User> Analysis(String jsonStr) throws JSONException {
		JSONArray jsonArray = null;
		ArrayList<User> list = new ArrayList<User>();
		jsonArray = new JSONArray(jsonStr);
		
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			User userTemp = new User();
			userTemp.setUserNo(Integer.parseInt(jsonObj.getString("userNo")));
			userTemp.setPostalCode(jsonObj.getString("postalcode"));
			userTemp.setAddress(jsonObj.getString("address"));
			list.add(userTemp);
		}
		
		return list;
	}

}
