package com.parse.starter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 */
public class AllListingFragment extends Fragment {
    private RecyclerView rv;
    private ArrayList<Properties> properties;
    public AllListingFragment(){


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_all_listing, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        initializeData();
        initializeAdapter();
        return rootView;
    }

    private void initializeAdapter() {
        RVAdapter adapter;
        adapter = new RVAdapter(properties);
        rv.setAdapter(adapter);
    }


    private void initializeData() {
        properties = new ArrayList<>();
        properties.add(new Properties("Tyson properties", "2 bedrooms", R.drawable.img1));
        properties.add(new Properties("kirichwa heights", "5 bedrooms", R.drawable.img2));
        properties.add(new Properties("moringa heihts", "4 bedrooms apartments", R.drawable.img3));
        properties.add(new Properties("lare properties", "bedsitters", R.drawable.img4));
        properties.add(new Properties("wa mathu investments","3 bedrooms", R.drawable.img5));



    }


    private class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
        List<Properties> properties;
        public  class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView textView;
            TextView textView2;
            ImageView imageView;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                textView = (TextView) itemView.findViewById(R.id.textView);
                textView2 = (TextView) itemView.findViewById(R.id.textView2);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);



            }
        }
        RVAdapter(ArrayList<Properties> properties) {
            this.properties= properties;

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

            holder.textView.setText(properties.get(i).size);
            holder.textView2.setText(properties.get(i).location);
            holder.imageView.setImageResource(properties.get(i).imageId);
        }

        @Override
        public int getItemCount() {
            return 0;
        }


    }
    public class Properties {

        String size;
        String location;
        int imageId;

        public Properties(String size, String location, int imageId) {
            this.size =size;
            this.location =location;
            this.imageId =imageId;

        }
    }

}
