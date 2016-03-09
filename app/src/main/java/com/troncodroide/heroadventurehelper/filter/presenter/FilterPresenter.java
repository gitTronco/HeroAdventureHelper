package com.troncodroide.heroadventurehelper.filter.presenter;

import com.troncodroide.heroadventurehelper.Base.BasePresenter;
import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
import com.troncodroide.heroadventurehelper.citicens.interactor.CiticensInteractor;
import com.troncodroide.heroadventurehelper.citicens.presenter.CiticensPresenter;
import com.troncodroide.heroadventurehelper.filter.interactor.FilterInteractor;
import com.troncodroide.heroadventurehelper.managers.HeroManager;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;

import java.util.LinkedList;
import java.util.List;

public class FilterPresenter extends BasePresenter implements FilterInteractor.FilterListener {

    FilterInteractor _interactor;
    private static List<FilterCategory> filters;

    public FilterPresenter(FilterPresenterInterface listener) {
        super(listener);
        _interactor = new FilterInteractor();
    }

    @Override
    public void onError(int errorCode, String name) {
        stopLoading();
        _listener.onError(errorCode, name);
    }


    private void stopLoading() {
        _listener.stopLoading();
    }

    private void startLoading(String title, String message) {
        _listener.startLoading(title, message);
    }


    public void getFilters(String town) {
        startLoading("APP", "Loading...");
        if (filters == null)
            _interactor.getFiltersCiticens(town, this);
        else {
            onGetFiltersSuccess(filters);
        }

    }

    @Override
    public void onGetFiltersSuccess(List<FilterCategory> items) {
        stopLoading();
        filters = items;
        ((FilterPresenterInterface) _listener).onFilterSuccess(items);
    }

    public List<FilterCategory> getCacheFilters() {
        return filters;
    }

    public void clearFIlters() {
        for (FilterCategory filter : filters) {
            filter.clearFilter();
        }
        onGetFiltersSuccess(filters);
    }

    public static class FilterCategory {
        public static final int TYPE_RANGED = 1;
        public static final int TYPE_VALUES = 2;
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

        public FilterValueRanged getRangedValue() {
            int min = 0;
            int max = 0;
            for (FilterValue item : values) {
                int value = (int) Float.parseFloat(item.getName());
                min = (min <= value) ? min : value;
                max = (max >= value) ? max : value;
            }

            FilterValueRanged rangued = new FilterValueRanged(name, min, max);
            rangued.setSelectedMin(min);
            rangued.setSelectedMax(max);
            return rangued;
        }

        public int getType() {
            return type;
        }

        public void clearFilter() {
            for (FilterValue fv : values) {
                fv.clear();
            }
        }
    }

    public static class FilterValue {
        String name;
        boolean selected;

        public FilterValue(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isSelected() {
            return selected;
        }

        public void toggleSelection() {
            selected = !selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void clear() {
            selected = false;
        }
    }

    public static class FilterValueRanged extends FilterValue {
        int min, max;
        int selectedMin, selectedMax;

        public FilterValueRanged(String name, int min, int max) {
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
    }

    public interface FilterPresenterInterface extends PresenterListener {
        void onFilterSuccess(List<FilterCategory> item);
    }
}

