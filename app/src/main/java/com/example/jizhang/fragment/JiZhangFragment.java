package com.example.jizhang.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jizhang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JiZhangFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_ji_zhang, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_money = view.findViewById(R.id.et_money_jizhang);
        et_leibie = view.findViewById(R.id.et_leibie_jizhang);
        et_time = view.findViewById(R.id.et_time_jizhang);
        et_shuoming = view.findViewById(R.id.et_shuoming_jizhang);
        bt_ok = view.findViewById(R.id.bt_ok_jizhang);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = et_money.getText().toString().trim();
                String leibie = et_leibie.getText().toString().trim();
                String time = et_time.getText().toString().trim();
                String shuoming = et_shuoming.getText().toString().trim();

            }
        });
    }
}
