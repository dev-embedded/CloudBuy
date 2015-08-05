package com.cloudbuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GetOrderByBarcode extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_order_by_barcode);
		
		Button buttonSearch = (Button) findViewById(R.id.search);
		
		buttonSearch.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(GetOrderByBarcode.this, Activity_temp.class);

				startActivity(intent);

				GetOrderByBarcode.this.finish();
			}
		});
	}

}
