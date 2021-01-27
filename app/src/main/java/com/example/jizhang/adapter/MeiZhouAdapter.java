package com.example.jizhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jizhang.R;
import com.example.jizhang.bean.CategoriesPieBean;

import java.util.List;

public class MeiZhouAdapter extends RecyclerView.Adapter<MeiZhouAdapter.ViewHolder> {

    private Context context;
    private List<CategoriesPieBean> list;


    public MeiZhouAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CategoriesPieBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_mei_zhou_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriesPieBean pieBean = list.get(position);
        holder.mMeizhouCategoryTv.setText(pieBean.getCategory());
        holder.mMeizhouPercentageTv.setText(pieBean.getPercentage()+"%");
        holder.mMeizhouTotalTv.setText(pieBean.getTotal()+"元");
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMeizhouCategoryTv;
        private TextView mMeizhouPercentageTv;
        private TextView mMeizhouTotalTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMeizhouCategoryTv = (TextView) itemView.findViewById(R.id.tv_meizhou_category);
            mMeizhouPercentageTv = (TextView) itemView.findViewById(R.id.tv_meizhou_percentage);
            mMeizhouTotalTv = (TextView) itemView.findViewById(R.id.tv_meizhou_total);
        }
    }
}
