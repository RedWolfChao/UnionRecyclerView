package com.red.wolf.unionrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.red.wolf.unionrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mDataList = new ArrayList<>();

    public ListAdapter(Context mContext, List<String> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public ListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ItemViewHolder(mInflater.inflate(R.layout.item_theme_list_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(ListAdapter.ItemViewHolder holder, int position) {
        holder.mTv.setText(mDataList.get(position));
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.mTv);
        }
    }
}
