package com.troncodroide.heroadventurehelper.repository.api.cache;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.repository.api.TTL;

import java.lang.ref.WeakReference;

public class DiskCache {
    public static final String TAG = "DiskCache";
    private static final int MESSAGE_NO_DISK = 1;
    private static final int MESSAGE_GET_DATA = 2;
    private static Prefser prefser;

    private void log(String message) {
        Log.d(TAG, message);
    }

    private boolean containsKey(String key) {
        return (getPrefser().contains(key));
    }

    private Handler weakHandler;

    private static class MyHandler extends Handler{
        public MyHandler(Looper looper) {
            super(looper);
        }
    }

    public <T> void getData(final String key, final TypeToken<TTL<T>> typeToken, final DiskListener<T> listener) {
        weakHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                TTL<T> ttl = (TTL<T>) msg.obj;
                switch (msg.what) {
                    case MESSAGE_NO_DISK:
                        listener.onNoDiskDataFound(key);
                        break;
                    case MESSAGE_GET_DATA:
                        listener.onDiskDataRetrieved(key, (T) ttl.getData(), ttl.isAlive());
                        break;
                    default:
                        break;
                }
                weakHandler = null;
                return true;
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                if (containsKey(key)) {
                    TTL<T> data = getPrefser(key).get(key, typeToken, new TTL<T>(null));
                    if (data.getData() == null) {
                        Message completedMessage = weakHandler.obtainMessage(MESSAGE_NO_DISK);
                        completedMessage.sendToTarget();
                        //listener.onNoDiskDataFound(key);
                    } else {
                        Message completedMessage = weakHandler.obtainMessage(MESSAGE_GET_DATA, data);
                        completedMessage.sendToTarget();
//                        listener.onDiskDataRetrieved(key, (T) data.getData(), data.isAlive());
                    }
                } else {
                    Message completedMessage = weakHandler.obtainMessage(MESSAGE_NO_DISK);
                    completedMessage.sendToTarget();
                    //listener.onNoDiskDataFound(key);
                }
                Looper.loop();

            }
        });
        t.start();
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
