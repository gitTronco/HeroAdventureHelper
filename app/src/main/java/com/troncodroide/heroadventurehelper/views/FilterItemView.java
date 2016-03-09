package com.troncodroide.heroadventurehelper.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.citicens.presenter.CiticensPresenter;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter.FilterValue;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterItemView extends FrameLayout {

    private FilterItemBindView itemBindView;
    private FilterValue data;

    public FilterItemView(Context context) {
        super(context);
        _init(context);
    }

    public FilterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    public FilterItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _init(context);
    }

    public void setData(FilterValue data) {
        this.data = data;
        loadData(data);
    }

    private void loadData(FilterValue data) {
        this.itemBindView.mFilterItem.setText(data.getName());
        this.itemBindView.mFilterItem.setSelected(data.isSelected());
    }

    public void toggle() {
        this.itemBindView.mFilterItem.toggle();
    }

    private void _init(Context c) {
        View v = inflate(c, R.layout.item_filter_value, this);
        if (!isInEditMode()) {
            itemBindView = new FilterItemBindView(v);
            itemBindView.mFilterItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.setSelected(isChecked);
                }
            });
        }
    }

    public class FilterItemBindView {

        @Bind(R.id.filter_value_item)
        public CheckBox mFilterItem;

        public FilterItemBindView(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
