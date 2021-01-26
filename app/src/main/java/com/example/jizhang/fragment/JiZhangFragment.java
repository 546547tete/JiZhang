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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.httplibrary.utils.LogUtils;
import com.example.jizhang.R;
import com.example.jizhang.bean.AddEntryBean;
import com.example.jizhang.bean.DataBean;
import com.example.jizhang.utils.ApiService;
import com.example.jizhang.utils.ContextUtils;
import com.example.jizhang.utils.RetrofitUtils;
import com.example.jizhang.utils.UUIDUtils;

import java.util.Calendar;
import java.util.HashMap;
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
public class JiZhangFragment extends Fragment implements View.OnClickListener {

    private EditText mMoneyJizhangEt;
    private EditText mLeibieJizhangEt;
    private TextView tv_time_jizhang;
    private EditText mShuomingJizhangEt;
    private Button mOkJizhangBt;
    private String money;
    private String leibie;
    private String shuoming;
    private String time;
    private Calendar calendar;
    private static final String TAG = "JiZhangFragment";


    public JiZhangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_ji_zhang, container, false);
        initView(inflate);
        return inflate;
    }



    private void initView(@NonNull final View itemView) {
        mMoneyJizhangEt = (EditText) itemView.findViewById(R.id.et_money_jizhang);
        mLeibieJizhangEt = (EditText) itemView.findViewById(R.id.et_leibie_jizhang);
        tv_time_jizhang = (TextView) itemView.findViewById(R.id.tv_time_jizhang);
        mShuomingJizhangEt = (EditText) itemView.findViewById(R.id.et_shuoming_jizhang);
        mOkJizhangBt = (Button) itemView.findViewById(R.id.bt_ok_jizhang);
        mOkJizhangBt.setOnClickListener(this);

        calendar = Calendar.getInstance();

        tv_time_jizhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(getActivity(), 3, tv_time_jizhang, calendar);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ok_jizhang:
                // TODO 21/01/25
                initDateNew();
//                money = mMoneyJizhangEt.getText().toString().trim();
//                leibie = mLeibieJizhangEt.getText().toString().trim();
//                shuoming = mShuomingJizhangEt.getText().toString().trim();
//                time = mTimeJizhangEt.getText().toString().trim();

//                Toast.makeText(getContext(), money+leibie+shuoming+time, Toast.LENGTH_SHORT).show();
//                if (!TextUtils.isEmpty(leibie)) {
//                    if (!TextUtils.isEmpty(shuoming)) {
//                        if (!TextUtils.isEmpty(money)) {
//                            int parseInt = Integer.parseInt(money);
//                            initData(leibie, shuoming, money);
//                        } else {
//                            Log.e(TAG, "onClick: money = 空" + money);
//                        }
//                    } else {
//                        Log.e(TAG, "onClick: shuoming = 空" + shuoming);
//                    }
//                } else {
//                    Log.e(TAG, "onClick: leibie = 空" + leibie);
//                }
//                Toast.makeText(getContext(), shuoming, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void initDateNew() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://192.168.31.13:8001/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService service = build.create(ApiService.class);
        service.getAddEntry("add_entry/"+ContextUtils.UUID_JIZHANG+"娱乐"+"唱歌"+123)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataBean dataBean) {
                        String error_msg = dataBean.getError_msg();
                        int error_code = dataBean.getError_code();
                        Toast.makeText(getContext(), error_code+error_msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData(String leibie, String shuoming, String money) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        map.put("category", leibie);
        map.put("description", shuoming);
        map.put("amount", money);
        Log.e("", "initData: " + ContextUtils.UUID_JIZHANG);
        RetrofitUtils.getInstance()
                .getAddEntry("add_entry/" + ContextUtils.UUID_JIZHANG  + leibie + shuoming  + money)
//                .getAddEntry("add_entry/" + ContextUtils.UUID_JIZHANG + "/" + leibie + "/" + shuoming + "/" + money)
//                .getAddEntry("add_entry/aid633e2151-90ba-4251-a7a0-ead4e0d0ad9a/%E5%A8%B1%E4%B9%90/%E7%9A%84%E9%A3%8E%E6%A0%BC%E5%92%8C%E5%81%A5%E5%BA%B7/"+123)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataBean addEntryBean) {

                        Log.e("TAG", "onNext: " + addEntryBean.getError_msg() + addEntryBean.getError_code());

//                        List<AddEntryBean.DetailBean> detail = addEntryBean.getDetail();
//                        for (int i = 0; i < detail.size(); i++) {
//                            AddEntryBean.DetailBean bean = detail.get(i);
//                            String msg = bean.getMsg();
//                            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG = AddEntry", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
