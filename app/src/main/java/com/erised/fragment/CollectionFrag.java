package com.erised.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.erised.R;
import com.erised.adapter.GridAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CollectionFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CollectionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView bg;
    private TextView address;

    private GridAdapter adapter;
    private GridView gridView;

    public static final String BASE_URL = "http://erised.in/listing/khaital/?json=1";

    private OnFragmentInteractionListener mListener;

    private ArrayList<String> imageList = new ArrayList<String>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionFrag newInstance(String param1, String param2) {
        CollectionFrag fragment = new CollectionFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CollectionFrag() {
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

        View rootView = inflater.inflate(R.layout.fragment_collection, container, false);
        bg = (ImageView) rootView.findViewById(R.id.img_bg);

        gridView = (GridView) rootView.findViewById(R.id.grid_gallery);

        imageList.add(0, "http:\\/\\/magarmach.com\\/wp-content\\/uploads\\/2015\\/10\\/IMG_0926-150x150.jpg");
        imageList.add(1, "http:\\/\\/magarmach.com\\/wp-content\\/uploads\\/2015\\/10\\/IMG_0923-150x150.jpg");
        imageList.add(2, "http:\\/\\/magarmach.com\\/wp-content\\/uploads\\/2015\\/10\\/IMG_0926-150x150.jpg");
        imageList.add(3, "http:\\/\\/magarmach.com\\/wp-content\\/uploads\\/2015\\/10\\/IMG_0923-150x150.jpg");
        imageList.add(4, "http:\\/\\/magarmach.com\\/wp-content\\/uploads\\/2015\\/10\\/IMG_0926-150x150.jpg");
        imageList.add(5, "http:\\/\\/magarmach.com\\/wp-content\\/uploads\\/2015\\/10\\/IMG_0923-150x150.jpg");

        adapter = new GridAdapter(getActivity(), imageList);

        gridView.setAdapter(adapter);

        address = (TextView) rootView.findViewById(R.id.tx_add);

        getShopDetails();

        Picasso.with(getActivity()).load("http://erised.in//wp-content//uploads//2015//10//DSC_0383-600x600.jpg")
                .into(bg);

        address.setText("k.k marg \n" + "Mumbai - 400028\n" + "Maharashtra");

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCollectionFragmentInteraction(uri);
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

    private void getShopDetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


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
        public void onCollectionFragmentInteraction(Uri uri);
    }

}
