package com.example.jizhang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jizhang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiZhouFragment extends Fragment {

    public MeiZhouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mei_zhou, container, false);
    }
}