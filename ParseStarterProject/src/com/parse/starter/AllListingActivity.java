package com.parse.starter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;

import java.util.ArrayList;
import java.util.List;


public class AllListingActivity extends ActionBarActivity implements MenuItem.OnMenuItemClickListener,View.OnLongClickListener {
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    private RecyclerView rv;
    private ArrayList<Properties> properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_listing);
        rv =(RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        initializeData();
        initializeadapter();
        initToolbar();
        initMenuFragment();
        fragmentManager = getSupportFragmentManager();
    }


    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.close);

       /* MenuObject yourAccount = new MenuObject("YOUR ACCOUNT");
        yourAccount.setResource(R.drawable.coins);

        MenuObject weeklyReport = new MenuObject("WEEKLY REPORT");
        weeklyReport.setResource(R.drawable.graphup);*/

        MenuObject profile = new MenuObject("Profile");
        profile.setResource(R.drawable.manageaccount);


       /* MenuObject homeWork = new MenuObject("HOME WORK");
        homeWork.setResource(R.drawable.book2);

        MenuObject yourTeacher = new MenuObject("YOUR TEACHER");
        yourTeacher.setResource(R.drawable.smile);
        MenuObject getKytabuHelp = new MenuObject("GET KYTABU HELP");
        getKytabuHelp.setResource(R.drawable.kytabuhelp);*/

        menuObjects.add(close);
        /*menuObjects.add(yourAccount);
        menuObjects.add(weeklyReport);
        menuObjects.add(homeWork);*/
        menuObjects.add(profile);
       /* menuObjects.add(yourTeacher);
        menuObjects.add(getKytabuHelp);*/
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
                onBackPressed();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void initializeadapter() {
        RVAdapter adapter;
        adapter = new RVAdapter(properties);
        rv.setAdapter(adapter);
    }

    private void initializeData() {
        properties = new ArrayList<>();
        properties.add(new Properties("Tyson properties", "2 bedrooms", R.drawable.img1));
        properties.add(new Properties("kirichwa heights", "5 bedrooms", R.drawable.img2));
        properties.add(new Properties("moringa heights", "4 bedrooms apartments", R.drawable.img3));
        properties.add(new  Properties("lare properties", "bedsitters", R.drawable.img4));
        properties.add(new Properties("wa mathu investments","3 bedrooms", R.drawable.img5));

    }


    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}