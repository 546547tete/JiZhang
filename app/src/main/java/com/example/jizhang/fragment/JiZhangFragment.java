package com.example.jizhang.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jizhang.R;
import com.example.jizhang.bean.AddEntryBean;
import com.example.jizhang.utils.ContextUtils;
import com.example.jizhang.utils.RetrofitUtils;
import com.example.jizhang.utils.UUIDUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class JiZhangFragment extends Fragment implements View.OnClickListener {

    private EditText mMoneyJizhangEt;
    private EditText mLeibieJizhangEt;
    private EditText mTimeJizhangEt;
    private EditText mShuomingJizhangEt;
    private Button mOkJizhangBt;

    private EditText et_money;
    private EditText et_leibie;
    private EditText et_time;
    private EditText et_shuoming;
    private Button bt_ok;

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

    private void initData() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        Log.e("", "initData: "+ContextUtils.UUID_JIZHANG );
        RetrofitUtils.getInstance()
                .getAddEntry(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddEntryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddEntryBean addEntryBean) {
                        List<AddEntryBean.DetailBean> detail = addEntryBean.getDetail();
                        AddEntryBean.DetailBean detailBean = detail.get(0);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(@NonNull final View itemView) {
        mMoneyJizhangEt = (EditText) itemView.findViewById(R.id.et_money_jizhang);
        mLeibieJizhangEt = (EditText) itemView.findViewById(R.id.et_leibie_jizhang);
        mTimeJizhangEt = (EditText) itemView.findViewById(R.id.et_time_jizhang);
        mShuomingJizhangEt = (EditText) itemView.findViewById(R.id.et_shuoming_jizhang);
        mOkJizhangBt = (Button) itemView.findViewById(R.id.bt_ok_jizhang);
        mOkJizhangBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ok_jizhang:
                // TODO 21/01/25

                break;
            default:
                break;
        }
    }
}
