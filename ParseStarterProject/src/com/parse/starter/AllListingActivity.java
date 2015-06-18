package com.parse.starter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class AllListingActivity extends ActionBarActivity implements OnItemClickListener {
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    private RecyclerView rv;
    private ArrayList<Properties> properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_listing);
        rv = (RecyclerView) findViewById(R.id.rv);
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
                Intent i = new Intent(AllListingActivity.this, UserHouSearchActivity.class);
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

    private void initializeadapter() {
        RVAdapter adapter;
        adapter = new RVAdapter(properties);
        rv.setAdapter(adapter);
    }

    private void initializeData() {
        properties = new ArrayList<>();
        properties.add(new Properties("Ksh 80 000", "3 bedroom Apartments / Flats to rent in Lavington Nairobi", R.drawable.img1));
        properties.add(new Properties("kirichwa heights", "5 bedrooms", R.drawable.img2));
        properties.add(new Properties("moringa heights", "4 bedrooms apartments", R.drawable.img3));
        properties.add(new  Properties("lare properties", "bedsitters", R.drawable.img4));
        properties.add(new Properties("wa mathu investments","3 bedrooms", R.drawable.img5));

    }
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        /*List<Storyitems> stories;*/
        List<Properties> properties;

        public class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView textView;
            TextView textView2;
            ImageView imageView;
            Button viewmore;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                textView = (TextView) itemView.findViewById(R.id.textView);
                textView2 = (TextView) itemView.findViewById(R.id.textView2);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                viewmore = (Button)itemView.findViewById(R.id.viewmore);


            }
        }


        /*RVAdapter(List<Storyitems> stories) {
            this.stories = stories;

        }*/
        RVAdapter(List<Properties> properties) {
            this.properties = properties;

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }


        @Override
        public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listings, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(RVAdapter.PersonViewHolder holder, int i) {

            /*holder.textView.setText(stories.get(i).title);
            holder.textView2.setText(stories.get(i).introduction);
            holder.imageView.setImageResource(stories.get(i).imageId);*/
            holder.textView.setText(properties.get(i).size);
            holder.textView2.setText(properties.get(i).location);
            holder.imageView.setImageResource(properties.get(i).imageId);
            final Context context = holder.cv.getContext();
            final String name = String.valueOf(i);
            holder.viewmore.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {

                    Intent i = new Intent(AllListingActivity.this, ListingDetailsActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();

                }
            });
        }


        @Override
        public int getItemCount() {
            return properties.size();
        }
    }


}