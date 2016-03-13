package com.troncodroide.heroadventurehelper.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.pwittchen.prefser.library.Prefser;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;
import com.troncodroide.heroadventurehelper.utils.Encryptutils;

import java.util.Observable;

public class ConfigurationManager extends Observable {
    private static int widthFilterItem = 60;
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

    public static int getWidthFilterItem() {
        return widthFilterItem + 2;
    }

    public static void setWidthFilterItem(int width) {
        if (width > 10) widthFilterItem = width;
    }

    public static boolean isRemember() {
        LoginData data = getPrefser().get("conf", LoginData.class, new LoginData());
        return data.isRemember();
    }

    private static Prefser getPrefser() {
        return new Prefser(APP.getContext().getSharedPreferences("LoginConf", Context.MODE_PRIVATE));

    }

    public static void setLoginConf(String email, String pass) {
        getPrefser().put("conf", new LoginData(email, pass));
    }

    public static LoginData getLoginConf() {
        return getPrefser().get("conf", LoginData.class, new LoginData());
    }

    public static void clearLoginConf() {
        getPrefser().remove("conf");
    }

    public static String getCurrentTown() {
        return town;
    }

    public static void setCurrentTown(String town) {
        ConfigurationManager.town = town;
    }

    public static class LoginData {
        private String user, pass;
        private boolean remember = false;

        public LoginData() {
        }

        public LoginData(String user, String pass) {
            this.user = user;
            this.pass = Encryptutils.encryp(user, pass);
            this.remember = true;
        }

        public String getUser() {
            return user;
        }

        public String getPass() {
            return Encryptutils.desEncryp(user, pass);
        }

        public boolean isRemember() {
            return remember;
        }
    }
}
