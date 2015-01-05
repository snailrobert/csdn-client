package com.robert.today.csdn_client.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robert.today.csdn_client.R;
import com.robert.today.csdn_client.model.LeftMenuItem;

import java.util.List;

/**
 * Created by chenjun06 on 2014/12/25.
 */
public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.ViewHolder> {

    private List<LeftMenuItem> mItemList;
    private MenuItemListener mMenuItemListener;

    public void setItemList(List<LeftMenuItem> itemList) {
        mItemList = itemList;
    }

    public void setMenuItemListener(MenuItemListener menuItemListener) {
        mMenuItemListener = menuItemListener;
    }

    public interface MenuItemListener {
        public void onMenuItemClicked(View view);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(mItemList == null || mItemList.isEmpty()) {
            return null;
        }

        if(mItemList.size() <= i) {
            return null;
        }

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_row_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mItemView.setText(mItemList.get(i).getTextName());
        if(-1 == mItemList.get(i).getDrawableRes()) {
            viewHolder.mItemView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewHolder.mItemView.setTextSize(16f);
        } else {
            viewHolder.mItemView.setTextSize(12f);
            viewHolder.mItemView.setTag(mItemList.get(i).getPos());
            viewHolder.mItemView.setCompoundDrawablesRelativeWithIntrinsicBounds(mItemList.get(i).getDrawableRes(), 0, 0, 0);
            viewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null != mMenuItemListener) {
                        mMenuItemListener.onMenuItemClicked(v);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = (TextView) itemView;
        }
    }
}
