package com.example.jizhang.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.httplibrary.utils.LogUtils;
import com.example.jizhang.R;
import com.example.jizhang.bean.CategoriesBean;
import com.example.jizhang.bean.DataBean;
import com.example.jizhang.utils.ApiService;
import com.example.jizhang.utils.ContextUtils;
import com.example.jizhang.utils.RetrofitUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private TextView mSpinnerText;
    private Spinner mSpinner01;
    private ArrayAdapter<String> adapter;
    private List<CategoriesBean> list;
    private String[] arr;


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
        initData();
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mMoneyJizhangEt = (EditText) itemView.findViewById(R.id.et_money_jizhang);
        mLeibieJizhangEt = (EditText) itemView.findViewById(R.id.et_leibie_jizhang);
        tv_time_jizhang = (TextView) itemView.findViewById(R.id.tv_time_jizhang);
        mShuomingJizhangEt = (EditText) itemView.findViewById(R.id.et_shuoming_jizhang);
        mOkJizhangBt = (Button) itemView.findViewById(R.id.bt_ok_jizhang);
        mOkJizhangBt.setOnClickListener(this);
        mSpinnerText = (TextView) itemView.findViewById(R.id.spinnerText);
        mSpinner01 = (Spinner) itemView.findViewById(R.id.Spinner01);

        calendar = Calendar.getInstance();

        tv_time_jizhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(getActivity(), 3, tv_time_jizhang, calendar);
            }
        });



    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            mSpinnerText.setText(arr[arg2]);
            LogUtils.e("99999999999999999999999999");
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ok_jizhang:
                // TODO 21/01/25
                String leibie = mLeibieJizhangEt.getText().toString().trim();
                String shuoming = mShuomingJizhangEt.getText().toString().trim();
                String money = mMoneyJizhangEt.getText().toString().trim();
                String trim = tv_time_jizhang.getText().toString().trim();
                String spinnerText = mSpinnerText.getText().toString().trim();

                if (TextUtils.isEmpty(trim)) {
                    return;
                }
                String[] split = trim.split("-");
                String year = split[0];
                String month = split[1];
                String day = split[2];

                int y = Integer.parseInt(year);
                int m = Integer.parseInt(month);
                int d = Integer.parseInt(day);

                int parseInt = Integer.parseInt(money);

                if (!TextUtils.isEmpty(spinnerText) && !TextUtils.isEmpty(trim) && !TextUtils.isEmpty(money) && !TextUtils.isEmpty(shuoming)) {
//                    initData(y,m,d,leibie,shuoming,parseInt);
                    initDateNew(y, m, d, spinnerText, shuoming, parseInt);
                } else if (TextUtils.isEmpty(spinnerText)) {
                    Toast.makeText(getContext(), "请输入类别", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(shuoming)) {
                    Toast.makeText(getContext(), "请添加备注", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(money)) {
                    Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(trim)) {
                    Toast.makeText(getContext(), "请输入日期", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void initData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        RetrofitUtils.getInstance()
                .getCategories("categories/" + ContextUtils.UUID_JIZHANG + "/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CategoriesBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CategoriesBean> categoriesPieBeans) {
                        //将数据添加到集合中

                        arr = new String[categoriesPieBeans.size()];
                        for (int i = 0; i < categoriesPieBeans.size(); i++) {
                            arr[i] = categoriesPieBeans.get(i).getCategory_name();
                        }


                        //将可选内容与ArrayAdapter连接起来
                        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arr);

                        //设置下拉列表的风格
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        //将adapter 添加到spinner中
                        mSpinner01.setAdapter(adapter);

                        //添加事件Spinner事件监听
                        mSpinner01.setOnItemSelectedListener(new SpinnerSelectedListener());

                        //设置默认值
                        mSpinner01.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.getMessage());
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

    private void initDateNew(int y, int m, int d, String leibie, String shuoming, int money) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        map.put("year", y);
        map.put("month", m);
        map.put("day", d);
        map.put("category", leibie);
        map.put("description", shuoming);
        map.put("amount", money);
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://192.168.31.13:8001/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService service = build.create(ApiService.class);
        service.getAddEntry(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataBean dataBean) {
                        if (dataBean.getError_code() == 0) {
                            Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
