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

import com.example.jizhang.R;
import com.example.jizhang.bean.TestBean;
import com.example.jizhang.utils.ApiService;
import com.example.jizhang.utils.ContextUtils;
import com.example.jizhang.utils.ZheXianView;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
public class MeiYueBaoFragment extends Fragment implements View.OnClickListener {


    private TextView mStartTimeShujuTv;
    private TextView mEndTimeShujuTv;
    private Button mOkShujuBtn;
    private Calendar instance;
    private ZheXianView mZhexiantu;

    public MeiYueBaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_mei_yue_bao, container, false);
        initView(inflate);
        return inflate;

    }

    private void initView(@NonNull final View itemView) {
        instance = Calendar.getInstance();
        mStartTimeShujuTv = (TextView) itemView.findViewById(R.id.tv_startTime_shuju);
        mStartTimeShujuTv.setOnClickListener(this);
        mEndTimeShujuTv = (TextView) itemView.findViewById(R.id.tv_endTime_shuju);
        mEndTimeShujuTv.setOnClickListener(this);
        mOkShujuBtn = (Button) itemView.findViewById(R.id.btn_ok_shuju);
        mOkShujuBtn.setOnClickListener(this);
        mZhexiantu = (ZheXianView) itemView.findViewById(R.id.zhexiantu);
    }


    private void initDateNew(String startYear, String startMonth, String endYear, String endMonth)   {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://192.168.31.13:8001/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService service = build.create(ApiService.class);
        service.getBean("monthly_trend/" + ContextUtils.UUID_JIZHANG + "/" + startYear + "/" + startMonth + "/" + endYear + "/" + endMonth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TestBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TestBean> testBean) {
                        Map<String ,Float> map=new LinkedHashMap<>() ;//一定要用有序的Map
                        for (int i = 0; i < testBean.size(); i++) {
                            TestBean testBean1 = testBean.get(i);
                            map.put(testBean1.getMonth()+"", (float) testBean1.getAmount());
                        }

                        String[] a=new String[]{"10000","15000","20000","25000","30000"};
//                        String[] a=new String[]{"个","十","百","千","万","十万"};
                        mZhexiantu.startDraw(map,a,"金额",80,16);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAg", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_startTime_shuju:
                // TODO 21/01/26
                showDatePickerDialog(getActivity(), 3, mStartTimeShujuTv, instance);
                break;
            case R.id.tv_endTime_shuju:
                // TODO 21/01/26
                showDatePickerDialog(getActivity(), 3, mEndTimeShujuTv, instance);
                break;
            case R.id.btn_ok_shuju:
                // TODO 21/01/26
                String start = mStartTimeShujuTv.getText().toString().trim();
                String end = mEndTimeShujuTv.getText().toString().trim();

                if (!TextUtils.isEmpty(start)&&!TextUtils.isEmpty(end)){
                    String[] split = start.split("-");
                    String startYear = split[0];
                    String startMonth = split[1];

                    String[] split1 = end.split("-");
                    String endYear = split1[0];
                    String endMonth = split1[1];

                    initDateNew(startYear, startMonth, endYear, endMonth);
                }

//                Map<String ,Float> map=new LinkedHashMap<>() ;//一定要用有序的Map
//                map.put("周一",8.0f);
//                map.put("周二",19.0f);
//                map.put("周三",10.0f);
//                map.put("周四",30.0f);
//                map.put("周五",15.0f);
//                String[] a=new String[]{"10","20","30","40","50"};
//                mZhexiantu.startDraw(map,a,"m/s",40,16);
                break;
            default:
                break;
        }
    }

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
