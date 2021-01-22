package com.example.jizhang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jizhang.R;
import com.example.jizhang.adapter.VpBaoBiaoAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaoBiaoFragment extends Fragment {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> list;
    private VpBaoBiaoAdapter vpBaoBiaoAdapter;

    public BaoBiaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bao_biao, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab = view.findViewById(R.id.tab_baobiao);
        vp = view.findViewById(R.id.vp_baobiao);

        list = new ArrayList<>();
        list.add(new FenleiBaoFragment());
        list.add(new MeiYueBaoFragment());

        vpBaoBiaoAdapter = new VpBaoBiaoAdapter(getChildFragmentManager(), 0, list);
        vp.setAdapter(vpBaoBiaoAdapter);
        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setText("分类报");
        tab.getTabAt(1).setText("每月报");
    }
}
