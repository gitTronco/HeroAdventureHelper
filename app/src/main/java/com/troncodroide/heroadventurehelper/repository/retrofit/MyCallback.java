package com.troncodroide.heroadventurehelper.repository.retrofit;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response.Listener;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response.Error;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCallback<T> implements Callback<T> {

    Listener<T> listener;

    public MyCallback(Listener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccess()) {
            listener.onSuccess(response.body(),true);
        } else {
            listener.onError(new Error<>(response.code(),response.message()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        listener.onError(new Error<>(1,t.getMessage()));
    }
}
