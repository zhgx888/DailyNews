package edu.feicui.news.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.feicui.news.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuRight extends Fragment {


    public FragmentMenuRight() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu_right, container, false);
        // Inflate the layout for this fragment
        return view;
    }

}
