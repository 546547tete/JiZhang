package com.example.jizhang.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.httplibrary.utils.LogUtils;
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
    private String money;
    private String leibie;
    private String shuoming;
    private String time;


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

    private void initData() {
        money = mMoneyJizhangEt.getText().toString().trim();
        leibie = mLeibieJizhangEt.getText().toString().trim();
        shuoming = mShuomingJizhangEt.getText().toString().trim();
        time = mTimeJizhangEt.getText().toString().trim();

        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        map.put("category",leibie);
        map.put("description",shuoming);
        map.put("amount",money);
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
                        for (int i = 0; i < detail.size(); i++) {
                            AddEntryBean.DetailBean bean = detail.get(i);
                            String msg = bean.getMsg();
                            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
                initData();
//                Toast.makeText(getContext(), shuoming, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
