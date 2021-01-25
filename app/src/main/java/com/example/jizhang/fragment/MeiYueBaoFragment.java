package com.example.jizhang.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jizhang.R;
import com.example.jizhang.bean.AddEntryBean;
import com.example.jizhang.utils.ContextUtils;
import com.example.jizhang.utils.RetrofitUtils;
import com.example.jizhang.utils.ZheXianView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiYueBaoFragment extends Fragment {

    private Button bt;

    public MeiYueBaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mei_yue_bao, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        bt = view.findViewById(R.id.bt_qingqiu);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDate();
//                ZheXianView zheXianView = new ZheXianView(getActivity());
//                Map<String ,Float> map=new LinkedHashMap<>() ;//一定要用有序的Map
//                map.put("周一",8.0f);
//                map.put("周二",19.0f);
//                map.put("周三",10.0f);
//                map.put("周四",30.0f);
//                map.put("周五",15.0f);
//                String[] a=new String[]{"10","20","30","40","50"};
//                zheXianView.startDraw(map,a,"m/s",40,16);
//                zheXianView.initView();
            }
        });
    }

    private void initDate() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        map.put("from_y",2020);
        map.put("from_m",12);
        map.put("to_y",2021);
        map.put("to_m",1);

        RetrofitUtils.getInstance()
                .getMonthlyTrend(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddEntryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddEntryBean addEntryBean) {
                        List<AddEntryBean.DetailBean> detail = addEntryBean.getDetail();
                        for (int i = 0; i < detail.size(); i++) {
                            AddEntryBean.DetailBean bean = detail.get(i);
                            String msg = bean.getMsg();
                            Toast.makeText(getContext(), msg.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
