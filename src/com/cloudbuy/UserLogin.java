package com.cloudbuy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import tools.ApacheHttpClient;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class UserLogin extends Activity {
	
	//private String TAG = "http";
	private Spinner	userTypeNameText = null;
	private EditText userEmailText = null;
	private EditText userPasswordText = null;
	private Button buttonCommit = null;
	private Button buttonCancel = null;
	private TextView mResult = null;
	
	private String baseURL = "http://192.168.1.106:8080/CloudBuyPractice/AppLogin";
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			userPasswordText.setText((CharSequence) msg.obj);
			boolean result =  msg.getData().getBoolean("result");
			System.out.println("result : " + result);
			if(!result){
				Intent intent = new Intent();
				intent.setClass(UserLogin.this, DeliveryList.class);
				startActivity(intent);
				UserLogin.this.finish();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login);
		
		userTypeNameText = (Spinner) findViewById(R.id.user_type_name);
		userEmailText = (EditText) findViewById(R.id.user_email_value);
		userPasswordText = (EditText) findViewById(R.id.user_pass_value);
		buttonCommit = (Button) findViewById(R.id.login_submit);
		buttonCancel = (Button) findViewById(R.id.login_cancel);
		
		
		buttonCommit.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run(){
						String userType = userTypeNameText.getSelectedItem().toString();
						String userEmail = userEmailText.getText().toString();
						String userPassword = userPasswordText.getText().toString();
						String res = null;
						
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("flagUser", userType));
						params.add(new BasicNameValuePair("email", userEmail));
						params.add(new BasicNameValuePair("password", userPassword));
						
						System.out.println("userEmail : " + userEmail);
						//userPasswordText.setText(userEmail);
						
						ApacheHttpClient httpClient = new ApacheHttpClient();
						
						try
						{
							res = httpClient.httpPost(baseURL, params);
							System.out.println("reponse for POST :" + res);
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}

						Message message = Message.obtain();
						message.obj = res;
						handler.sendMessage(message);
					}
				}).start();	
				
				
			}
		});
		
		
		
		
		buttonCancel.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(UserLogin.this, Activity_temp.class);

				startActivity(intent);

				UserLogin.this.finish();
			}
		});
	}
	
	@SuppressWarnings("unused")
	private String getResponse(InputStream inStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = -1;
		byte[] buffer = new byte[1024];
		while ((len = inStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}

		byte[] data = outputStream.toByteArray();
		return new String(data);

	};

}
