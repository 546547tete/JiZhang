package com.example.jizhang;

import android.os.Bundle;

import com.example.httplibrary.utils.JsonUtils;
import com.example.jizhang.adapter.Vpadapter;
import com.example.jizhang.bean.DataBean;
import com.example.jizhang.fragment.BaoBiaoFragment;
import com.example.jizhang.fragment.JiZhangFragment;
import com.example.jizhang.fragment.SettingsFragment;
import com.example.jizhang.utils.ApiService;
import com.example.jizhang.utils.RetrofitUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> list;
    private Vpadapter vpadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);

        list = new ArrayList<>();
        list.add(new JiZhangFragment());
        list.add(new BaoBiaoFragment());
        list.add(new SettingsFragment());

        vpadapter = new Vpadapter(getSupportFragmentManager(), 0, list);
        vp.setAdapter(vpadapter);
        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setText("记账").setIcon(R.drawable.book);
        tab.getTabAt(1).setText("报表").setIcon(R.drawable.baobiao);
        tab.getTabAt(2).setText("设置").setIcon(R.drawable.settings);

//        HashMap<String, String> hashMap = new HashMap<>();
//        RetrofitUtils.getInstance()
//                .getDataBean("", hashMap)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<JsonElement>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(JsonElement jsonElement) {
//                        DataBean dataBean = JsonUtils.jsonToClass(jsonElement, DataBean.class);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
