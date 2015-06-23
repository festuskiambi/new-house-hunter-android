package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);
    Parse.initialize(this, "Z6kURWLQ9DCzfq6Sb5DUvUMMBWLcBb3DkLcdkg01", "yXPBOgvHJS4055p8Do1OUTwMLlXfvTBBZ5S8QGJG");


    // Add your initialization code here



    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    /*ParseACL.setDefaultACL(defaultACL, true);
    ParseObject testObject = new ParseObject("TestObject");
    testObject.put("foo", "bar");
    testObject.saveInBackground();*/
  }
}
