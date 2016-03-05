package com.troncodroide.heroadventurehelper.repository.api.net;

/**
 * Created by Usuario-007 on 19/02/2016.
 */
import com.troncodroide.heroadventurehelper.repository.interfaces.Response.Error;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetCallback<T> implements Callback<T> {

    API.APIListener<T> listener;

    public NetCallback(API.APIListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccess()) {
            if (response.body() instanceof com.troncodroide.heroadventurehelper.repository.interfaces.Response) {
                if (((com.troncodroide.heroadventurehelper.repository.interfaces.Response)response.body()).getStatus()==0){
                    listener.onError(new Error<>(response.code(), response.message()));
                }else{
                    listener.onSuccess(response.body());
                }
            }else{
                listener.onSuccess(response.body());
            }
        } else {
            listener.onError(new Error<>(response.code(), response.message()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        listener.onError(new Error<>(1, t.getMessage()));
    }
}
