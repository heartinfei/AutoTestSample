package com.example.heartinfei.testsample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 王强 on 2017/12/15 249346528@qq.com
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VH> {
    private List<ActivityInfo> data = new ArrayList<>();

    public void setData(List<ActivityInfo> data) {
        this.data.addAll(data);
    }

    public ActivityInfo getItem(int i){
        return data.get(i);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH holder = new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false));
        holder.itemView.setOnClickListener((v) -> {
            ActivityInfo info = (ActivityInfo) v.getTag();
            ComponentName componentName = new ComponentName(info.packageName, info.name);
            Intent launchIntent = new Intent();
            launchIntent.setComponent(componentName);
            v.getContext().startActivity(launchIntent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        ActivityInfo info = data.get(position);
        holder.tv.setText(info.name);
        holder.itemView.setTag(info);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public String gettext() {
            return tv.getText().toString();
        }
    }
}
