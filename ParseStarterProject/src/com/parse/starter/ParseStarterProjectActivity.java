package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class ParseStarterProjectActivity extends ActionBarActivity {
	/** Called when the activity is first created. */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState ==null) {

		}
		/*ParseAnalytics.trackAppOpenedInBackground(getIntent());
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();*/
	}
}
