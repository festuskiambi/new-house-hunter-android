package com.parse.starter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;


import com.parse.ParseUser;

public class CheckUserStatusActivity extends ActionBarActivity {
    public CheckUserStatusActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, UserHouSearchActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, AppInfoActivity.class));
        }

    }
}
