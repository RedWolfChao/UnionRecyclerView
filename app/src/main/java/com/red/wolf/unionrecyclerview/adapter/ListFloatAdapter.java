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

public class ListFloatAdapter extends RecyclerView.Adapter<ListFloatAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mDataList = new ArrayList<>();

    public ListFloatAdapter(Context mContext, List<String> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public ListFloatAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ItemViewHolder(mInflater.inflate(R.layout.item_theme_list_float_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(ListFloatAdapter.ItemViewHolder holder, int position) {
        holder.mTv.setText(mDataList.get(position));
        holder.mTvFloat.setText(mDataList.get(position) + "Float");
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTv, mTvFloat;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.mTv);
            mTvFloat = itemView.findViewById(R.id.mTvFloat);
        }
    }
}
