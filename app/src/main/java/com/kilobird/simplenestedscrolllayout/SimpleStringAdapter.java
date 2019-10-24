package com.kilobird.simplenestedscrolllayout;
/*
 * File description.
 *
 * @author qian.wang.ms
 * @data 19/10/24
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.SimpleStringViewHolder> {
    private final List<String> mData = new ArrayList<>();
    private final Context mContext;

    public SimpleStringAdapter(Context context, List<String> list) {
        mContext = context;
        mData.addAll(list);
    }

    @Override
    public SimpleStringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SimpleStringViewHolder.getDefault(parent);
    }

    @Override
    public void onBindViewHolder(SimpleStringViewHolder holder, int position) {
        holder.update(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SimpleStringViewHolder extends RecyclerView.ViewHolder {
        private final TextView text;

        public SimpleStringViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.id_info);
        }

        public static SimpleStringViewHolder getDefault(ViewGroup parent) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new SimpleStringViewHolder(v);
        }

        public void update(String s) {
            text.setText(s);
        }
    }
}
