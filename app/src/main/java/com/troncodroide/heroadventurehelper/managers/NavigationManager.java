package com.troncodroide.heroadventurehelper.managers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.citicens.CiticensFragment;
import com.troncodroide.heroadventurehelper.towns.TownsFragment;

import java.util.Observable;
import java.util.Observer;

public class NavigationManager extends Observable {

    private static NavigationManager navigationManager;
    public static final int TARGET_NO_TARGET = 0;
    public static final int TARGET_HOME = 10;
    public static final int TARGET_TOWNS = 11;
    public static final int TARGET_CITICENS = 12;
    public static final String CLEAR_STACK = "clear";
    private static int current, before;
    public static final String TAG = "NavigationManager";

    public static Fragment goTo(FragmentManager fm, int target) {
        return goTo(fm, target, new Bundle());
    }

    public static Fragment goTo(final FragmentManager fm, final int target, Bundle extra) {
        Fragment toret = null;

        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fm.getBackStackEntryCount() > 0) {
                    String name = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName();
                    switch (name) {
                        case TownsFragment.TAG:
                            current = TARGET_TOWNS;
                            break;
                    }
                } else {
                    current = TARGET_HOME;
                }
                notifyChanges();
            }
        });

        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        if (target != before)
            before = current;
        current = target;
        if (extra == null) {
            extra = new Bundle();
        }

        notifyChanges();
        if (extra.containsKey(CLEAR_STACK)) {
            while (fm.getBackStackEntryCount() != 0) {
                fm.popBackStackImmediate();
            }
        }

        switch (target) {
            case NavigationManager.TARGET_HOME: {

                //toret = HomeItemFragment.newInstance();
                ft.replace(R.id.frame, toret);
                break;
            }

            case NavigationManager.TARGET_TOWNS: {
                Fragment f = fm.findFragmentByTag(TownsFragment.TAG);
                if (f != null) {
                    ((TownsFragment) f).reload();
                } else {

                    toret = TownsFragment.newInstance();
                    ft.replace(R.id.frame, toret, TownsFragment.TAG);
                    ft.addToBackStack(TownsFragment.TAG);
                }

                break;
            }
            case NavigationManager.TARGET_CITICENS: {
                if (before != TARGET_CITICENS) {
                    String town = extra.getString("data");
                    toret = CiticensFragment.newInstance(town);
                    ft.replace(R.id.frame, toret, CiticensFragment.TAG);
                    ft.addToBackStack(CiticensFragment.TAG);
                }

                break;
            }

        }
        ft.commitAllowingStateLoss();
        return toret;
    }

    private static void notifyChanges() {
        getNavigationManager().setChanged();
        getNavigationManager().notifyObservers();
    }

    public static boolean isHome() {
        return (current == TARGET_HOME);
    }

    public static int getPreviusTarget() {
        return before;
    }

    public static int getTarget() {
        return current;
    }


    public static void notifyChangues() {
        notifyChanges();
    }

    public static void addObserverS(Observer observer) {
        getNavigationManager().addObserver(observer);
    }

    private static NavigationManager getNavigationManager() {
        if (navigationManager == null) {
            navigationManager = new NavigationManager();
        }
        return navigationManager;
    }

    public static void removeObserverS(Observer observer) {
        getNavigationManager().deleteObserver(observer);
    }


    public static void clearBackstack(FragmentManager fm) {
        while (fm.getBackStackEntryCount() != 0) {
            fm.popBackStackImmediate();
        }
    }
}
