package com.troncodroide.heroadventurehelper.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.citicens.presenter.CiticensPresenter;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter.FilterCategory;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter.FilterValueRanged;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterCategoryRangeView extends FrameLayout {

    private FilterItemBindView itemBindView;
    private FilterCategory data;
    private FilterValueRanged rangedValue;

    public FilterCategoryRangeView(Context context) {
        super(context);
        _init(context);
    }

    public FilterCategoryRangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    public FilterCategoryRangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _init(context);
    }

    public void setData(FilterCategory data) {
        this.data = data;
        rangedValue = data.getRangedValue();
        loadData(rangedValue);
    }

    private void loadData(final FilterValueRanged data) {
        this.itemBindView.mFilterItem.setText(data.getName());
        this.itemBindView.mSeekBarFrom.setMax(data.getMax());
        this.itemBindView.mSeekBarTo.setMax(data.getMax());
        this.itemBindView.mSeekBarFrom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= data.getSelectedMax()) ;
                {
                    seekBar.setProgress(data.getSelectedMax());
                }
                data.setSelectedMin(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.itemBindView.mSeekBarTo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= data.getSelectedMin()) ;
                {
                    seekBar.setProgress(data.getSelectedMin());
                }
                data.setSelectedMax(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void toggle() {
        this.itemBindView.mFilterItem.toggle();
    }

    private void _init(Context c) {
        View v = inflate(c, R.layout.item_filter_category_range, this);
        if (!isInEditMode()) {
            itemBindView = new FilterItemBindView(v);
            itemBindView.mFilterItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    rangedValue.setSelected(isChecked);
                    itemBindView.mSeekBarFrom.setEnabled(isChecked);
                    itemBindView.mSeekBarTo.setEnabled(isChecked);
                }
            });
        }
    }

    public class FilterItemBindView {

        @Bind(R.id.filter_value_item)
        public CheckBox mFilterItem;

        @Bind(R.id.seekBar_from)
        public SeekBar mSeekBarFrom;

        @Bind(R.id.seekBar_to)
        public SeekBar mSeekBarTo;

        public FilterItemBindView(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
