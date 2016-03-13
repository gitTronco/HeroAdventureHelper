package com.troncodroide.heroadventurehelper.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.filter.models.FilterCategory;
import com.troncodroide.heroadventurehelper.filter.models.FilterValueRangued;
import com.troncodroide.heroadventurehelper.managers.ConfigurationManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterCategoryRangeView extends FrameLayout {

    private FilterItemBindView itemBindView;
    private FilterCategory data;
    private FilterValueRangued rangedValue;

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
        if (data != null) {
            Log.i("RANGUED", "setData: " + data.toString());
            rangedValue = data.getRangedValue();
            loadData(rangedValue);
        }
    }

    private void loadData(final FilterValueRangued data) {
        this.itemBindView.mFilterItem.setText(data.getName());
        this.itemBindView.mSeekBarFrom.setMax(data.getMax());
        this.itemBindView.mSeekBarTo.setMax(data.getMax());
        this.itemBindView.mFilterItem.setChecked(data.isSelected());
        if (data.hasFlag(FilterCategory.TYPE_AGE)) {
            this.itemBindView.mSeekBarFrom.setProgress(ConfigurationManager.getSelectedFilters().getSelectedAge().getMin());
            this.itemBindView.mSeekBarTo.setProgress(ConfigurationManager.getSelectedFilters().getSelectedAge().getMax());
        } else if (data.hasFlag(FilterCategory.TYPE_HEIGHT)) {
            this.itemBindView.mSeekBarFrom.setProgress(ConfigurationManager.getSelectedFilters().getSelectedHeight().getMin());
            this.itemBindView.mSeekBarTo.setProgress(ConfigurationManager.getSelectedFilters().getSelectedHeight().getMax());
        } else if (data.hasFlag(FilterCategory.TYPE_WEIGHT)) {
            this.itemBindView.mSeekBarFrom.setProgress(ConfigurationManager.getSelectedFilters().getSelectedWeight().getMin());
            this.itemBindView.mSeekBarTo.setProgress(ConfigurationManager.getSelectedFilters().getSelectedWeight().getMax());
        }
    }

    private void _init(Context c) {
        View v = inflate(c, R.layout.item_filter_category_range, this);
        if (!isInEditMode()) {
            itemBindView = new FilterItemBindView(v);
        }
    }

    public class FilterItemBindView {

        @Bind(R.id.filter_value_item)
        public CheckBox mFilterItem;

        @Bind(R.id.filter_value_item_from)
        public TextView mTextFrom;

        @Bind(R.id.filter_value_item_to)
        public TextView mTextTo;

        @Bind(R.id.seekBar_from)
        public SeekBar mSeekBarFrom;

        @Bind(R.id.seekBar_to)
        public SeekBar mSeekBarTo;

        public FilterItemBindView(View view) {
            ButterKnife.bind(this, view);
            mSeekBarFrom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mTextFrom.setText(String.format("From:%d", progress));
                    rangedValue.setSelectedMin(progress);
                    data.setSelectedRangued(getRangued());
                    ConfigurationManager.getSelectedFilters().updateSelected(getRangued());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            mSeekBarTo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mTextTo.setText(String.format("To:%d", progress));
                    rangedValue.setSelectedMax(progress);
                    data.setSelectedRangued(getRangued());
                    ConfigurationManager.getSelectedFilters().updateSelected(getRangued());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            mFilterItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    itemBindView.mSeekBarTo.setEnabled(isChecked);
                    itemBindView.mSeekBarFrom.setEnabled(isChecked);
                }
            });
        }
    }

    private FilterValueRangued getRangued() {
        rangedValue.setSelectedMin(itemBindView.mSeekBarFrom.getProgress());
        rangedValue.setSelectedMax(itemBindView.mSeekBarTo.getProgress());
        rangedValue.setSelected(itemBindView.mFilterItem.isChecked());
        Log.i("Category", "getRangued: " + rangedValue);
        return rangedValue;
    }
}
