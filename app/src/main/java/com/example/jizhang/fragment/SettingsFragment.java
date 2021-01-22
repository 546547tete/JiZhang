package com.example.jizhang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jizhang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private RecyclerView rcy;
    private EditText et_add;
    private Button bt_add;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_add = view.findViewById(R.id.et_add_settings);
        bt_add = view.findViewById(R.id.bt_add_settings);
        rcy = view.findViewById(R.id.rcy_settings);

        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));


    }
}
