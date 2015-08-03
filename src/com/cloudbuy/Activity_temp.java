package com.cloudbuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_temp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_temp);

		Button buttonLogin = (Button) findViewById(R.id.button_login);

		Button buttonOrderList = (Button) findViewById(R.id.button_order_list);

		Button buttonBarcode = (Button) findViewById(R.id.button_barcode);

		Button buttonOrderDetails = (Button) findViewById(R.id.button_order_details);

		Button buttonDeliveryMap = (Button) findViewById(R.id.button_delivery_map);
		
		
		
		/*button listener for layout01:user login 
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */

		buttonLogin.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				//intent.setClass(Activity_temp.this, xxxxxx.class);

				//startActivity(intent);

				//Activity_temp.this.finish();
			}
		});

		
		/*button listener for layout02:order list for delivery 
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */
		buttonOrderList.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				//intent.setClass(Activity_temp.this, xxxxxx.class);

				//startActivity(intent);

				//Activity_temp.this.finish();
			}
		});

		/*button listener for layout03:get order by barcode
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */
		buttonBarcode.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				//intent.setClass(Activity_temp.this, xxxxxx.class);

				//startActivity(intent);

				//Activity_temp.this.finish();
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
		
		/*button listener for layout05:delivery map for order 
		 * to change "xxxxxx" to your activity name and delete every annotation symbols "//" in this method.
		 */
		buttonDeliveryMap.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();

				//intent.setClass(Activity_temp.this, xxxxxx.class);

				//startActivity(intent);

				//Activity_temp.this.finish();
			}
		});
	}
}
