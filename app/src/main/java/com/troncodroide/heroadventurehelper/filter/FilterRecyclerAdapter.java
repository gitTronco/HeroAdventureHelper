package com.troncodroide.heroadventurehelper.filter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.filter.models.FilterCategory;
import com.troncodroide.heroadventurehelper.views.FilterCategoryRangeView;
import com.troncodroide.heroadventurehelper.views.FilterCategoryView;


import java.util.List;

public class FilterRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CiticensCardViewAdapter";
    private final List<FilterCategory> mValues;

    public FilterRecyclerAdapter(List<FilterCategory> items) {
        super();
        mValues = items;
    }

    private FilterCategory getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if ((viewType & FilterCategory.TYPE_RANGED) == FilterCategory.TYPE_RANGED) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_filter_category_range, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_filter_category_value, parent, false);
            return new ViewHolder(view);
        }
    }

    public boolean hasFlag(int position, int flag) {
        return getItem(position).hasFlag(flag);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder parentHolder, int position) {
        if (parentHolder != null) {
            final ViewHolder holder = (ViewHolder) parentHolder;
            holder.mItem = getItem(position);
            if (hasFlag(position, FilterCategory.TYPE_RANGED)) {
                holder.mCategoryRangued.setData(holder.mItem);
            } else {
                holder.mCategoryValues.setData(holder.mItem);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public FilterCategory mItem;

        public FilterCategoryView mCategoryValues;
        public FilterCategoryRangeView mCategoryRangued;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            if (view instanceof FilterCategoryRangeView) {
                mCategoryRangued = (FilterCategoryRangeView) view;
                mCategoryRangued.setData(mItem);
            } else if (view instanceof FilterCategoryView) {
                mCategoryValues = (FilterCategoryView) view;
                mCategoryValues.setData(mItem);
            }
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.toString() + "'";
        }
    }

}

