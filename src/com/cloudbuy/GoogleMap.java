package com.cloudbuy;

import android.app.Activity;
import android.os.Bundle;


public class GoogleMap extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello Google Map");
        setContentView(R.layout.google_map);
    }
}


