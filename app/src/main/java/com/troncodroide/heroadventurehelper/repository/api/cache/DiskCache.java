package com.troncodroide.heroadventurehelper.repository.api.cache;

import android.content.Context;
import android.util.Log;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.repository.api.TTL;

/**
 * Created by Usuario-007 on 22/02/2016.
 */
public class DiskCache {
    public static final String TAG = "DiskCache";
    private static Prefser prefser;

    private void log(String message) {
        Log.d(TAG, message);
    }

    private boolean containsKey(String key) {
        return (getPrefser().contains(key));
    }


    public <T> void getData(final String key, final TypeToken<TTL<T>> typeToken, final DiskListener<T> listener) {
        if (containsKey(key)) {
            TTL<T> data = getPrefser(key).get(key, typeToken, new TTL<T>(null));
            if (data.getData() == null) {
                listener.onNoDiskDataFound(key);
            } else {
                listener.onDiskDataRetrieved(key, (T) data.getData(), data.isAlive());
            }
        } else {
            listener.onNoDiskDataFound(key);
        }
    }

    private Prefser getPrefser() {
        if (prefser == null) {
            prefser = new Prefser(APP.getContext());
        }
        return prefser;
    }

    /**
     * Prefer carga en memoria toda la información que existe en el xml. WsI ES UN archivo enorme la memoria se resiente.
     * Usando namespace se pueden crear distintso xml donde volcar parcialmente la memoria de respuestas gtandes
     *
     * @param namespace then name for shared preferences file.
     * @return Prefser instance
     */
    private Prefser getPrefser(String namespace) {
        if (prefser == null) {
            prefser = new Prefser(APP.getContext().getSharedPreferences(namespace, Context.MODE_PRIVATE));
        }
        return prefser;
    }

    public <T> void putData(String key, T data) {
        getPrefser().put(key, new TTL<>(data));
    }

    public <T> void putData(String key, T data, long ttl) {
        getPrefser().put(key, new TTL<>(ttl, data));
    }

    public void invalidate(String key) {
        getPrefser().remove(key);
    }

    public interface DiskListener<T> {
        void onDiskDataRetrieved(String key, T data, boolean isAlive);

        void onNoDiskDataFound(String key);
    }
}
