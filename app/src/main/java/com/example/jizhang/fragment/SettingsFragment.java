package com.example.jizhang.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.httplibrary.utils.LogUtils;
import com.example.jizhang.R;
import com.example.jizhang.adapter.SettingsAdapter;
import com.example.jizhang.bean.AddEntryBean;
import com.example.jizhang.utils.ContextUtils;
import com.example.jizhang.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private RecyclerView rcy;
    private EditText et_add;
    private Button bt_add;
    private SettingsAdapter settingsAdapter;
    private List<AddEntryBean> list;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(view);
        initData();

        initListener();
        return view;
    }

    private void initView(View view) {
        et_add = view.findViewById(R.id.et_add_settings);
        bt_add = view.findViewById(R.id.bt_add_settings);
        rcy = view.findViewById(R.id.rcy_settings);

        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));

        list = new ArrayList<>();
        settingsAdapter = new SettingsAdapter(getActivity());
        rcy.setAdapter(settingsAdapter);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEditText();
            }
        });
    }

    //添加类别
    private void initEditText() {
        String trim = et_add.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("hardware_id", ContextUtils.UUID_JIZHANG);
            map.put("category",trim);
            RetrofitUtils.getInstance()
                    .getAddCategory(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AddEntryBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AddEntryBean addEntryBean) {
                            //将数据添加到集合中
                            Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
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
    }

    private void initData() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        RetrofitUtils.getInstance()
                .getLeiBie(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddEntryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddEntryBean addEntryBean) {
                        //将数据添加到集合中
                        settingsAdapter.setData(list);
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
     * 监听事件
     * 删除
     * 修改
     */
    private void initListener() {
        //长按删除
        settingsAdapter.setOnSettingLongClickListener(new SettingsAdapter.OnSettingLongClickListener() {
            @Override
            public void onClick(final int post) {
                //提示dialog是否删除
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("是否删除")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteNetWork(post);
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        /**
         * 点击图标编辑类型
         * 弹出popupWindow
         */
        settingsAdapter.setOnSettingLongClickListener(new SettingsAdapter.OnSettingLongClickListener() {
            @Override
            public void onClick(int post) {
                final String text = null;
                Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
                int width = defaultDisplay.getWidth();
                int height = defaultDisplay.getHeight();
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popup_layout, null);
                final PopupWindow popupWindow = new PopupWindow(inflate, width - 200, height / 3);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
                alpha(0.5f);
                TextView tv_update_old = inflate.findViewById(R.id.tv_update_old);
                final EditText et_update_new = inflate.findViewById(R.id.et_update_new);
                Button btn_update_no = inflate.findViewById(R.id.btn_update_no);
                Button btn_update_ok = inflate.findViewById(R.id.btn_update_ok);

                tv_update_old.setText(text);

                //确认修改
                btn_update_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String trim = et_update_new.getText().toString().trim();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("hardware_id",ContextUtils.UUID_JIZHANG);
                        map.put("old_category",text);
                        map.put("new_category",trim);
                        RetrofitUtils.getInstance()
                                .getUpdateCategory(map)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddEntryBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddEntryBean addEntryBean) {
                                        Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        LogUtils.e(e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                        popupWindow.dismiss();
                    }
                });

                //取消修改
                btn_update_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                //关闭弹窗
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        alpha(1);
                    }
                });


            }
        });
    }

    //删除接口
    private void deleteNetWork(int post) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hardware_id", ContextUtils.UUID_JIZHANG);
        RetrofitUtils.getInstance()
                .getDeleteCategory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddEntryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddEntryBean addEntryBean) {
                        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
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

    public void alpha(float alpha) {
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = alpha;

        getActivity().getWindow().setAttributes(attributes);
    }
}
