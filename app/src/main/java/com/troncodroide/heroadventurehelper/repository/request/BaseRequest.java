package com.troncodroide.heroadventurehelper.repository.request;

import android.support.annotation.NonNull;

import com.troncodroide.heroadventurehelper.utils.Hash;

import java.io.Serializable;

public class BaseRequest<T> implements Serializable {
    private T data;

    public BaseRequest(@NonNull T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String hashRequest() {
        return Hash.md5(getData().toString());
    }
}
