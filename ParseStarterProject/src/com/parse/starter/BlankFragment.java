package com.parse.starter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    private RecyclerView rv;
    private ArrayList<Properties> properties;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();

        return fragment;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_blank, container, false);
        rv = (RecyclerView) rootview.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        initializeData();
        initializeAdapter();
        return rootview;
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
        properties.add(new Properties("wa mathu investments", "3 bedrooms", R.drawable.img5));



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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
