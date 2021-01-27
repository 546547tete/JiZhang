package com.example.jizhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jizhang.R;
import com.example.jizhang.bean.TestBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ZheXianRcyAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<TestBean> list;

    public ZheXianRcyAdapter(Context context, ArrayList<TestBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_zhexian_rcy, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1= (ViewHolder) holder;
        TestBean bean = list.get(position);
        holder1.tv_month_zhexian.setText("日期："+bean.getMonth());
        holder1.tv_amount_zhexian.setText("金额："+bean.getAmount()+" RMB");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_month_zhexian;
        public TextView tv_amount_zhexian;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_month_zhexian = (TextView) rootView.findViewById(R.id.tv_month_zhexian);
            this.tv_amount_zhexian = (TextView) rootView.findViewById(R.id.tv_amount_zhexian);
        }

    }
}
