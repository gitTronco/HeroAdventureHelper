package com.troncodroide.heroadventurehelper.filter.models;

import android.util.Log;

import com.troncodroide.heroadventurehelper.models.CiticenData;

import java.util.LinkedList;
import java.util.List;

public class FilterCategory {
    @Override
    public String toString() {
        return "FilterCategory{" +
                "name='" + name + '\'' +
                ", values=" + values +
                ", type=" + type +
                ", selectedValues=" + selectedValues +
                ", selectedRangued=" + selectedRangued +
                '}';
    }

    public static final int TYPE_RANGED= 1;         // 0000001
    public static final int TYPE_VALUES= 2;         // 0000010
    public static final int TYPE_AGE = 4;           // 0000100
    public static final int TYPE_WEIGHT = 8;        // 0001000
    public static final int TYPE_HEIGHT = 16;       // 0010000
    public static final int TYPE_HAIR = 32;         // 0100000
    public static final int TYPE_PROFESION = 64;    // 1000000
    String name;
    List<FilterValue> values;
    private int type;

    public FilterCategory(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addValue(FilterValue value) {
        value.setType(type);
        if (values == null) {
            values = new LinkedList<>();
            values.add(value);
        } else {
            for (FilterValue item : values) {
                if (item.getName().contentEquals(value.getName())) {
                    return;
                }
            }
            values.add(value);
        }
    }

    public List<FilterValue> getValues() {
        return values;
    }

    List<FilterValue> selectedValues;
    FilterValueRangued selectedRangued;

    public boolean hasFlag(int flag){
        return (type & flag ) == flag;
    }

    private void updateValues() {
        if (hasFlag(TYPE_VALUES)) {
            selectedValues = new LinkedList<>();
            for (FilterValue v : values) {
                if (v.isSelected()) {
                    v.setType(type);
                    selectedValues.add(v);
                }
            }
        } else {
            selectedRangued = _getRangedValue();
        }
    }

    public List<FilterValue> getSelectedValues() {
        if (selectedValues == null || selectedValues.isEmpty()) {
            updateValues();
        }
        return selectedValues;
    }

    public FilterValueRangued getRangedValue() {
        if (selectedRangued == null) {
            updateValues();
        }
        return selectedRangued;
    }


    private FilterValueRangued _getRangedValue() {
        int min = 0;
        int max = 0;
        for (FilterValue item : values) {
            int value = (int) Float.parseFloat(item.getName());
            min = (min <= value) ? min : value;
            max = (max >= value) ? max : value;
        }

        FilterValueRangued rangued = new FilterValueRangued(name, min, max);
        rangued.setSelectedMin(min);
        rangued.setSelectedMax(max);
        rangued.setType(type);
        return rangued;
    }

    public int getType() {
        return type;
    }

    public void clearFilter() {
        selectedRangued = null;
        if (selectedValues != null)
            selectedValues.clear();
        for (FilterValue fv : values) {
            fv.clear();
        }
    }

    public void setSelectedRangued(FilterValueRangued selectedRangued) {
        this.selectedRangued = selectedRangued;
    }
}
