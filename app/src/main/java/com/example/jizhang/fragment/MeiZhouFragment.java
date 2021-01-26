package com.example.jizhang.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jizhang.R;
import com.example.jizhang.bean.AddEntryBean;
import com.example.jizhang.bean.CategoriesPieBean;
import com.example.jizhang.bean.PieChartBean;
import com.example.jizhang.utils.ApiService;
import com.example.jizhang.utils.ContextUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiZhouFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MeiZhouFragment";
    private TextView mTimeNewTv;
    private TextView mTimeOldTv;
    private Button mAddEntryBtn;
    private Calendar instance;

    public MeiZhouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_mei_zhou, container, false);
        initView(inflate);

        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mTimeNewTv = (TextView) itemView.findViewById(R.id.tv_time_new);
        mTimeNewTv.setOnClickListener(this);
        mTimeOldTv = (TextView) itemView.findViewById(R.id.tv_time_old);
        mTimeOldTv.setOnClickListener(this);
        mAddEntryBtn = (Button) itemView.findViewById(R.id.btn_AddEntry);
        mAddEntryBtn.setOnClickListener(this);

        instance = Calendar.getInstance();
    }

    private void initData(String y, String m, String d, String oldY, String oldM, String oldD) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://192.168.31.13:8001/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService service = build.create(ApiService.class);
        service.getCategoriesPie("categories_pie/"+
                ContextUtils.UUID_JIZHANG +"/"+
                y+"/"+m+"/"+d+"/"+oldY+"/"+oldM+"/"+oldD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CategoriesPieBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CategoriesPieBean> testBean) {
                        List<PieChartBean> list = new ArrayList<>();
                        for (int i = 0; i < testBean.size(); i++) {
                            CategoriesPieBean addEntryBean = testBean.get(i);
                            PieChartBean pieChartBean = new PieChartBean();
                            pieChartBean.setValuer(addEntryBean.getCategory());
                            Toast.makeText(getActivity(), addEntryBean.getCategory(), Toast.LENGTH_SHORT).show();
//                            list.add()
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "月报" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_time_new:
                // TODO 21/01/26
                showDatePickerDialog(getActivity(), 3, mTimeNewTv, instance);
                break;
            case R.id.tv_time_old:
                // TODO 21/01/26
                showDatePickerDialog(getActivity(), 3, mTimeOldTv, instance);
                break;
            case R.id.btn_AddEntry:
                // TODO 21/01/26

                String trim = mTimeNewTv.getText().toString().trim();
                String trim1 = mTimeOldTv.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)&&!TextUtils.isEmpty(trim1)){
                    String[] split = trim.split("-");
                    String Y = split[0];
                    String M = split[1];
                    String D = split[2];

                    String[] split1 = trim1.split("-");
                    String oldY = split1[0];
                    String oldM = split1[1];
                    String oldD = split1[2];

                    initData(Y,M,D,oldY,oldM,oldD);
                }
                Log.e(TAG, "onClick: " + trim + trim1);
                break;
            default:
                break;
        }
    }

    /**
     * 日期
     * themeResId 1-5任意输入(5种不同的时间选择器)
     * <p>
     * tvTime 设置时间
     * <p>
     * calendar  楼上写的有
     */
    public void showDatePickerDialog(Activity activity, int themeResId, final TextView tvTime, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId
                // 绑定监听器(How the parent is notified that the date is set.)
                , new DatePickerDialog.OnDateSetListener() {
            private String times;

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                times = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                tvTime.setText(times);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}
