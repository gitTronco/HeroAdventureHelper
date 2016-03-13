package com.troncodroide.heroadventurehelper.filter.models;

public class FilterValueRangued extends FilterValue {
    int min, max;
    int selectedMin, selectedMax;

    public FilterValueRangued(String name, int min, int max) {
        super(name);
        this.min = min;
        this.max = max;
        selectedMax = max;
        selectedMin = min;

    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSelectedMin() {
        return selectedMin;
    }

    public int getSelectedMax() {
        return selectedMax;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setSelectedMin(int selectedMin) {
        this.selectedMin = selectedMin;
    }

    public void setSelectedMax(int selectedMax) {
        this.selectedMax = selectedMax;
    }

    @Override
    public void clear() {
        super.clear();
        selectedMin = min;
        selectedMax = max;
    }

    @Override
    public String toString() {
        return "FilterValueRangued{" +
                "min=" + min +
                ", max=" + max +
                ", selectedMin=" + selectedMin +
                ", selectedMax=" + selectedMax +
                '}';
    }
}
