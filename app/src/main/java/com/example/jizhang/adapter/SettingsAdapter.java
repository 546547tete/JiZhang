package com.example.jizhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jizhang.R;
import com.example.jizhang.bean.AddEntryBean;
import com.example.jizhang.bean.CategoriesBean;
import com.example.jizhang.bean.CategoriesPieBean;

import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private Context context;
    private List<CategoriesBean> list = new ArrayList<>();

    public SettingsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CategoriesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rcy_settings, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ViewHolder viewHolder = holder;
        CategoriesBean categoriesBean = list.get(position);
        viewHolder.tv_name_rcySettings.setText(categoriesBean.getCategory_name());

        viewHolder.img_bianji_rcySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSettingClickListener.onClickImage(position);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onSettingLongClickListener.onClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_bianji_rcySettings;
        TextView tv_name_rcySettings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_bianji_rcySettings = itemView.findViewById(R.id.img_bianji_rcySettings);
            tv_name_rcySettings = itemView.findViewById(R.id.tv_name_rcySettings);
        }
    }


    private OnSettingClickListener onSettingClickListener;
    private OnSettingLongClickListener onSettingLongClickListener;

    public interface OnSettingClickListener {
        void onClickImage(int post);
    }

    public interface OnSettingLongClickListener {
        void onClick(int post);
    }

    public void setOnSettingClickListener(OnSettingClickListener onSettingClickListener) {
        this.onSettingClickListener = onSettingClickListener;
    }

    public void setOnSettingLongClickListener(OnSettingLongClickListener onSettingLongClickListener) {
        this.onSettingLongClickListener = onSettingLongClickListener;
    }
}
