package com.troncodroide.heroadventurehelper.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.citicens.presenter.CiticensPresenter;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter.FilterCategory;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter.FilterValue;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tronco on 08/03/2016.
 */

public class FilterCategoryView extends FrameLayout {

    private FilterCategory category;
    private CategoryFilterCardInteractor _listener;
    private CategoriListBindView categoryview;

    public FilterCategoryView(Context context) {
        super(context);
        _init(context);
    }

    public FilterCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    public FilterCategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _init(context);
    }

    public void setData(FilterCategory data) {
        this.category = data;
        loadData();
    }

    private void loadData() {
        if (category != null) {
            categoryview.mName.setText(category.getName());
            categoryview.mList.setAdapter(new FilterValueAdapter(getContext(), category.getValues()));
        }
    }


    public void setListener(CategoryFilterCardInteractor listener) {
        this._listener = listener;
    }

    private void _init(Context c) {
        View v = inflate(c, R.layout.item_filter_category_values, this);
        if (!isInEditMode()) {
            categoryview = new CategoriListBindView(v);

        }
    }

    public interface CategoryFilterCardInteractor {
        void onItemClick();
    }

    public class CategoriListBindView {

        @Bind(R.id.filter_category_name)
        public CheckBox mName;
        @Bind(R.id.filter_list_items)
        public ListView mList;

        public CategoriListBindView(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class FilterValueAdapter extends ArrayAdapter<FilterValue> {

        public FilterValueAdapter(Context context, List<FilterValue> objects) {
            super(context, R.layout.list_item_text, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_filter_value, null);
            }
            ((FilterItemView) convertView).setData(getItem(position));
            return convertView;
        }
    }
}
