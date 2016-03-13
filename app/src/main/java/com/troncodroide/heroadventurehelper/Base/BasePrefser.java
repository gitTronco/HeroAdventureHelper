package com.troncodroide.heroadventurehelper.Base;

import android.content.Context;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;
import com.troncodroide.heroadventurehelper.APP;

/**
 * Created by Tronco on 10/03/2016.
 */
public class BasePrefser<T> {

    protected String prefserTag;

    protected Prefser getPrefser(String tag) {
        return new Prefser(APP.getContext().getSharedPreferences(tag, Context.MODE_PRIVATE));
    }

    protected void setPrefserTag(String prefserTag) {
        this.prefserTag = prefserTag;
    }

    protected void save(String key, T data) {
        getPrefser(prefserTag).put(key, data);
    }

    protected T load(String key, TypeToken<? extends T> token) {
        return getPrefser(prefserTag).get(key, token, null);
    }

}
