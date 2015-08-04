package com.cloudbuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class UserLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login);
		
		Button buttonCancel = (Button) findViewById(R.id.login_cancel);
		
		buttonCancel.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(UserLogin.this, Activity_temp.class);

				startActivity(intent);

				UserLogin.this.finish();
			}
		});
	}

}
