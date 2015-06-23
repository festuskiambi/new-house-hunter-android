package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class SignupActvity  extends ActionBarActivity implements OnItemClickListener {
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    private Button register;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_actvity);
        register = (Button) findViewById(R.id.submit1);
        username = (EditText)findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword=(EditText) findViewById(R.id.confirmpassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          signUp();

            }


        });
        initToolbar();
        initMenuFragment();
        fragmentManager = getSupportFragmentManager();
    }

public void signUp(){

    String userName = username.getText().toString().trim();
    String emailAddress= email.getText().toString().trim();
    String passWord = password.getText().toString().trim();
    String confirmpassword = confirmPassword.getText().toString().trim();

    // Validating the sign up data
    boolean validationError = false;
    StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
    if (userName.length() == 0) {
        validationError = true;
        validationErrorMessage.append(getString(R.string.error_blank_userName));
    }

    if (emailAddress.length() == 0) {
        validationError = true;
        validationErrorMessage.append(getString(R.string.error_blank_email));
    }
    if (passWord.length() == 0) {
        if (validationError) {
            validationErrorMessage.append(getString(R.string.error_join));
        }
        validationError = true;
        validationErrorMessage.append(getString(R.string.error_blank_password));
    }
    if (!passWord.equals(confirmpassword)) {
        if (validationError) {
            validationErrorMessage.append(getString(R.string.error_join));
        }
        validationError = true;
        validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
    }
    validationErrorMessage.append(getString(R.string.error_end));
    // If there is a validation error, display the error
    if (validationError) {
        Toast.makeText(SignupActvity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                .show();
        return;
    }
// Set up a progress dialog to show signup progress
    final ProgressDialog dialog = new ProgressDialog(SignupActvity.this);
    dialog.setMessage(getString(R.string.progress_signup));
    dialog.show();

    // Set up a new Parse user
    ParseUser user = new ParseUser();
    user.setUsername(userName);
    user.setEmail(emailAddress);
    user.setPassword(passWord);


    // Call the Parse signup method
    user.signUpInBackground(new SignUpCallback()  {
        @Override
        public void done(ParseException e) {

            if (e == null) {
                // Start an intent for the dispatch activity
                Intent inten = new Intent(SignupActvity.this, LoginActivity.class);
                /*inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);*/
                startActivity(inten);
                finish();

            }
            else {
                // Show the error message
                Toast.makeText(SignupActvity.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }
        }
    });
}



    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
    }

    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();
        MenuObject close = new MenuObject();
        close.setResource(R.drawable.close);
        MenuObject profile = new MenuObject("Profile");
        profile.setResource(R.drawable.manageaccount);
        menuObjects.add(close);
        menuObjects.add(profile);
        return menuObjects;
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setNavigationIcon(R.drawable.backarrow);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActvity.this, AppInfoActivity.class);
                startActivity(i);
                finish();
            }
        });
        mToolBarTextView.setText("HouseHunter");
    }


    protected void addFragment(android.support.v4.app.Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}


