package com.troncodroide.heroadventurehelper.filter.presenter;

import android.util.Log;

import com.troncodroide.heroadventurehelper.Base.BasePresenter;
import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
import com.troncodroide.heroadventurehelper.filter.interactor.FilterInteractor;
import com.troncodroide.heroadventurehelper.filter.models.FilterCategory;
import com.troncodroide.heroadventurehelper.filter.models.FilterValue;
import com.troncodroide.heroadventurehelper.filter.models.FilterValueRangued;
import com.troncodroide.heroadventurehelper.managers.ConfigurationManager;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.utils.FlagUtil;

import java.util.LinkedList;
import java.util.List;

public class FilterPresenter extends BasePresenter implements FilterInteractor.FilterListener {

    private FilterInteractor _interactor;
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
        ConfigurationManager.getSelectedFilters().evaluateSelected(items);
        ((FilterPresenterInterface) _listener).onGetFilterSuccess(items);
    }

    public void clearFilters() {
        if (filters != null) {
            for (FilterCategory cat : filters) {
                cat.clearFilter();
            }
            ConfigurationManager.getSelectedFilters().cleat();
            onGetFiltersSuccess(filters);
        }
    }

    public void evaluateFilters() {
        ConfigurationManager.getSelectedFilters().evaluateSelected(filters);
    }

    public interface FilterPresenterInterface extends PresenterListener {
        void onGetFilterSuccess(List<FilterCategory> item);
    }

    public static class SelectedFilters {
        private static final String TAG = "SELECTED_FILTERS";
        List<String> selectedHair;
        List<String> selectedProfessions;
        RanguedValue selectedWeight;
        RanguedValue selectedAge;
        RanguedValue selectedHeight;

        public RanguedValue getSelectedWeight() {
            if (selectedWeight == null) {
                selectedWeight = new RanguedValue(0, Integer.MAX_VALUE);
            }
            return selectedWeight;
        }

        public RanguedValue getSelectedAge() {
            if (selectedAge == null) {
                selectedAge = new RanguedValue(0, Integer.MAX_VALUE);
            }
            return selectedAge;
        }

        public RanguedValue getSelectedHeight() {
            if (selectedHeight == null) {
                selectedHeight = new RanguedValue(0, Integer.MAX_VALUE);
            }
            return selectedHeight;
        }

        private void logSelected() {
            Log.i(TAG, toString());
        }

        @Override
        public String toString() {
            return "SelectedFilters{" +
                    "selectedHair=" + selectedHair +
                    ", selectedProfessions=" + selectedProfessions +
                    ", selectedWeight=" + selectedWeight +
                    ", selectedAge=" + selectedAge +
                    ", selectedHeight=" + selectedHeight +
                    '}';
        }

        public void evaluateSelected(List<FilterCategory> list) {

            for (FilterCategory category : list) {
                if (FlagUtil.passFilter(category.getType(), FilterCategory.TYPE_AGE)) {
                    selectedAge = getRangued(category.getRangedValue());
                } else if (FlagUtil.passFilter(category.getType(), FilterCategory.TYPE_HAIR)) {
                    selectedHair = getValues(category.getValues());
                } else if (FlagUtil.passFilter(category.getType(), FilterCategory.TYPE_HEIGHT)) {
                    selectedHeight = getRangued(category.getRangedValue());
                } else if (FlagUtil.passFilter(category.getType(), FilterCategory.TYPE_PROFESION)) {
                    selectedProfessions = getValues(category.getValues());
                } else if (FlagUtil.passFilter(category.getType(), FilterCategory.TYPE_WEIGHT)) {
                    selectedWeight = getRangued(category.getRangedValue());
                }
            }
            logSelected();
        }

        private List<String> getValues(List<FilterValue> values) {
            List<String> list = new LinkedList<>();
            for (FilterValue value : values) {
                if (value.isSelected())
                    list.add(value.getName());
            }
            return list;
        }

        private RanguedValue getRangued(FilterValueRangued rangedValue) {
            if (rangedValue.isSelected()) {
                int smin, smax;
                smin = (rangedValue.getMin() == rangedValue.getSelectedMin()) ? 0 : rangedValue.getSelectedMin();
                smax = (rangedValue.getMax() == rangedValue.getSelectedMax()) ? Integer.MAX_VALUE : rangedValue.getSelectedMax();
                return new RanguedValue(smin, smax);
            } else {
                return null;
            }
        }

        public void cleat() {
            this.selectedHeight = null;
            this.selectedHair = null;
            this.selectedProfessions = null;
            this.selectedAge = null;
            this.selectedWeight = null;
        }


        public static class RanguedValue {
            int min, max;

            public int getMin() {
                return min;
            }

            public int getMax() {
                return max;
            }

            public RanguedValue(int min, int max) {
                this.min = min;
                this.max = max;
            }

            @Override
            public String toString() {
                return "RanguedValue{" +
                        "min=" + min +
                        ", max=" + max +
                        '}';
            }

            public boolean evaluate(int value) {
                return (value >= min && value <= max);
            }

            public boolean evaluate(double value) {
                return (value >= min && value <= max);
            }

            public boolean isEmpty() {
                return min == 0 && max == Integer.MAX_VALUE;
            }
        }

        private int evaluateAge(int age) {
            if (hasAgeFilter())
                return selectedAge.evaluate(age) ? 1 : 0;
            return 0;
        }

        private int evaluateWeight(double age) {
            if (hasWeightFilter())
                return selectedWeight.evaluate(age) ? 1 : 0;
            return 0;
        }

        private int evaluateHeight(double age) {
            if (hasHeightFilter())
                return selectedHeight.evaluate(age) ? 1 : 0;
            return 0;
        }

        private int evaluateHair(String hair) {
            if (hasHairFilter())
                return selectedHair.contains(hair) ? 1 : 0;
            return 0;
        }

        private int evaluateProfesion(List<String> profesions) {
            if (hasProfessionFilter()) {
                int result = 0;
                for (String profesion : profesions)
                    result += selectedProfessions.contains(profesion) ? 1 : 0;
                return result;
            }
            return 0;
        }

        private boolean hasHairFilter() {
            return selectedHair != null && !selectedHair.isEmpty();
        }

        private boolean hasProfessionFilter() {
            return selectedProfessions != null && !selectedProfessions.isEmpty();
        }

        private boolean hasAgeFilter() {
            return selectedAge != null && !selectedAge.isEmpty();
        }

        private boolean hasWeightFilter() {
            return selectedWeight != null && !selectedWeight.isEmpty();
        }

        private boolean hasHeightFilter() {
            return selectedHeight != null && !selectedHeight.isEmpty();
        }

        public boolean hasSelectedFilters() {
            return (hasProfessionFilter() || hasHairFilter() || hasWeightFilter() || hasHeightFilter() || hasAgeFilter());
        }

        public boolean evalue(CiticenData data) {
            if (hasSelectedFilters()) {
                int result = 0;
                result += evaluateAge(data.getAge());
                result += evaluateHeight(data.getHeight());
                result += evaluateWeight(data.getHeight());
                result += evaluateHair(data.getHair_color());
                result += evaluateProfesion(data.getProfessions());
                return result > 0;
            } else {
                return true;
            }
        }

        public void updateSelected(FilterValueRangued item) {
            if (item.hasFlag(FilterCategory.TYPE_WEIGHT)) {
                selectedWeight = getRangued(item);
            } else if (item.hasFlag(FilterCategory.TYPE_AGE)) {
                selectedAge = getRangued(item);
            } else if (item.hasFlag(FilterCategory.TYPE_HEIGHT)) {
                selectedHeight = getRangued(item);
            }
        }

        public void updateSelected(FilterValue item) {
            if (item.hasFlag(FilterCategory.TYPE_PROFESION)) {
                if (selectedProfessions.contains(item.getName())) {
                    if (!item.isSelected()) {
                        selectedProfessions.remove(item.getName());
                    }
                } else {
                    if (item.isSelected()) {
                        selectedProfessions.add(item.getName());
                    }
                }

            } else if (item.hasFlag(FilterCategory.TYPE_HAIR)) {
                if (selectedHair.contains(item.getName())) {
                    if (!item.isSelected()) {
                        selectedHair.remove(item.getName());
                    }
                } else {
                    if (item.isSelected()) {
                        selectedHair.add(item.getName());
                    }
                }
            }
        }
    }
}

