package com.troncodroide.heroadventurehelper.managers;

import android.content.Context;

import com.github.pwittchen.prefser.library.Prefser;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.models.HeroData;

import java.util.Observable;
import java.util.Observer;

public class HeroManager extends Observable {
    private static final String TAG = "HeroManagerData";
    private boolean hasSession = false;
    private HeroData heroData;

    private void _openHeroSession(HeroData hero) {
        this.heroData = hero;
        getPrefser().put(TAG, hero);
        hasSession = true;
        setChanged();
        notifyObservers(hasSession);
    }

    private void _saveHero() {
        getPrefser().put(TAG, heroData);
        hasSession = true;
        setChanged();
        notifyObservers(hasSession);
    }

    private void _closeHeroSession() {
        hasSession = false;
        this.heroData = null;
        getPrefser().put(TAG, new HeroData());
        setChanged();
        notifyObservers(hasSession);
    }

    public static boolean hasSession() {
        return (getInstance().getHeroData() != null);
    }

    public static void openHeroSession(HeroData user) {
        getInstance()._openHeroSession(user);
    }

    public static void saveHeroSession() {
        getInstance()._saveHero();
    }

    public static HeroData getHeroSession() {
        return getInstance().getHeroData();
    }

    public static void closeSession() {
        getInstance()._closeHeroSession();

    }

    public static HeroManager heroManager;

    private static HeroManager getInstance() {
        if (heroManager == null) {
            heroManager = new HeroManager();
        }
        return heroManager;
    }

    public static void addObserverS(Observer o) {
        getInstance().addObserver(o);
    }

    public static void removeObserverS(Observer o) {
        getInstance().deleteObserver(o);
    }

    private static Prefser getPrefser() {
        return new Prefser(APP.getContext().getSharedPreferences("HeroSession", Context.MODE_PRIVATE));
    }


    public HeroData getHeroData() {
        if (heroData == null)
            heroData = getPrefser().get(TAG, HeroData.class, new HeroData());
        return heroData;
    }


    public static void setUser(HeroData user) {
        getInstance()._openHeroSession(user);
    }
}
