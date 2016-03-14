package com.troncodroide.heroadventurehelper.managers;

import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;

import java.util.Observable;

public class ConfigurationManager extends Observable {
    private static String town;
    private static FilterPresenter.SelectedFilters selectedFilters;
    public static FilterPresenter.SelectedFilters getSelectedFilters() {
        if (selectedFilters == null) {
            selectedFilters = new FilterPresenter.SelectedFilters();
        }
        return selectedFilters;
    }

    public static void setSelectedFilters(FilterPresenter.SelectedFilters filters) {
        selectedFilters = filters;
    }

    public static String getCurrentTown() {
        return town;
    }

    public static void setCurrentTown(String town) {
        ConfigurationManager.town = town;
    }
}
