package com.troncodroide.heroadventurehelper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.squareup.leakcanary.LeakCanary;

import java.util.Locale;

public class APP extends MultiDexApplication {
    private static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
        _context = this;
        LeakCanary.install(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static int GetColor(int colorRes) {
        return getContext().getResources().getColor(colorRes);
    }

    public static Drawable GetDrawable(int drawableRes) {
        return getContext().getResources().getDrawable(drawableRes);
    }

    public static String GetString(int stringRes) {
        return getContext().getString(stringRes);
    }

    public static String[] GetStringArray(int stringRes) {
        return getContext().getResources().getStringArray(stringRes);
    }

    public static Context getContext() {
        return _context;
    }

    public static int getDisplayHeight() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getDisplayWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static Display getDisplay() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    public static Configuration getConfiguration() {
        return getContext().getResources().getConfiguration();
    }

    public static String getLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }

    public static int getDps(int pixels) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getDisplay().getMetrics(displaymetrics);
        // int h = displaymetrics.heightPixels;
        float d = displaymetrics.density;
        return (int) (pixels / d);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        _context = null;
    }

    public static String getScreenDescription() {
        //Determine screen size
        String toret = "";
        Configuration conf = getConfiguration();
        if ((conf.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            toret += "Large screen";
        } else if ((conf.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            toret += "Normal screen";
        } else if ((conf.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            toret += "Small screen";
        } else if ((conf.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            toret += "XLarge screen";
        } else {
            toret += "Screen not defined";
        }
        toret += "(" + getDisplayHeight() + "x" + getDisplayWidth() + ") ";

//Determine density
        DisplayMetrics metrics = new DisplayMetrics();
        getDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if (density == DisplayMetrics.DENSITY_HIGH) {
            toret += "Density: HIGH (" + String.valueOf(density)+")";
        } else if (density == DisplayMetrics.DENSITY_MEDIUM) {
            toret += "Density: MEDIUM (" + String.valueOf(density)+")";
        } else if (density == DisplayMetrics.DENSITY_LOW) {
            toret += "Density: LOW ( " + String.valueOf(density)+")";
        } else if (density == DisplayMetrics.DENSITY_XHIGH) {
            toret += "Density: XHIGH ( " + String.valueOf(density)+")";
        } else if (density == DisplayMetrics.DENSITY_XXHIGH) {
            toret += "Density: XXHIGH (" + String.valueOf(density)+")";
        } else if (density == DisplayMetrics.DENSITY_XXXHIGH) {
            toret += "Density: XXXHIGH (" + String.valueOf(density)+")";
        } else {
            toret += "Density is " + String.valueOf(density);
        }

        return toret;
    }

    public static Resources GetResources() {
        return getContext().getResources();
    }
}
