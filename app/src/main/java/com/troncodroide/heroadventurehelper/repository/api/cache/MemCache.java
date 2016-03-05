package com.troncodroide.heroadventurehelper.repository.api.cache;

import android.util.Log;

import com.troncodroide.heroadventurehelper.repository.api.TTL;

import java.util.Map;
import java.util.TreeMap;

public class MemCache {
    public static final String TAG = "MemCache";

    private Map<String, TTL> cache;

    private void log(String message) {
        Log.d(TAG, message);
    }

    private boolean isAlive(String key) {
        boolean toRet = false;
        if (containsKey(key)) {
            toRet = getCache().get(key).isAlive();
        }
        log("isAlive:" + key + ":" + toRet);
        return toRet;
    }

    private boolean containsKey(String key) {
        return (getCache().containsKey(key));
    }


    public <T> void getData(String key, CacheListener<T> listener) {
        Log.i(TAG, "getData: " + key);
        if (containsKey(key)) {
            try {
                listener.onCacheDataRetrieved(key, (T) getCache().get(key).getData(), getCache().get(key).isAlive());
            } catch (ClassCastException ex) {
                listener.onNoCacheDataFound(key);
            }
        } else {
            listener.onNoCacheDataFound(key);
        }
    }

    private Map<String, TTL> getCache() {
        if (cache == null) {
            cache = new TreeMap<>();
        }
        return cache;
    }

    public <T> void putData(String key, T data) {
        getCache().put(key, new TTL<>(data));
    }

    public <T> void putData(String key, T data, long ttl) {
        getCache().put(key, new TTL<>(ttl, data));
    }

    public void invalidate(String key) {
        getCache().remove(key);
    }

    public interface CacheListener<T> {
        void onCacheDataRetrieved(String key, T data, boolean isAlive);

        void onNoCacheDataFound(String key);
    }
}
