package com.troncodroide.heroadventurehelper.repository.api.net;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;
import com.troncodroide.heroadventurehelper.repository.retrofit.RetroManager;
import com.troncodroide.heroadventurehelper.repository.retrofit.RetrofitServices;

import java.util.List;
import java.util.Map;

public class Net {

    public interface APIListener<T> {
        void onSuccess(T data);

        void onError(Response.Error<String> error);
    }

    public static void getAppData(final APIListener<Map<String,List<CiticenDataRepository>>> result) {
        RetroManager.createService(RetrofitServices.TOWN.class).getTownDetail().enqueue(new NetCallback<>(result));
    }
}
