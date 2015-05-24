package com.parse.starter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by festus on 5/24/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    /*List<Storyitems> stories;*/
    List<Properties> properties;

    public class PersonViewHolder extends RecyclerView.ViewHolder {
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
        holder.cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                Toast.makeText(context, "you selected cardview at index:" + name, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return properties.size();
    }
}
/*
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>  {
    List<Properties> properties;

    public RVAdapter(ArrayList<Properties> properties) {
        this.properties=properties;
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

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textView;
        TextView textView2;
        ImageView imageView;
        public PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
*/
