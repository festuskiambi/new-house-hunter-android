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



}