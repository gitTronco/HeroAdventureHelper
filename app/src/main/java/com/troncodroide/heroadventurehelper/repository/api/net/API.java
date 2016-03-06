package com.troncodroide.heroadventurehelper.repository.api.net;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;
import com.troncodroide.heroadventurehelper.repository.retrofit.RetroManager;
import com.troncodroide.heroadventurehelper.repository.retrofit.RetrofitServices;

import java.util.List;
import java.util.Map;

/**
 * Created by Usuario-007 on 22/02/2016.
 */
public class API {

    public interface APIListener<T> {
        void onSuccess(T data);

        void onError(Response.Error<String> error);
    }

    public static void getAppData(final APIListener<Map<String,List<CiticenDataRepository>>> result) {
        RetroManager.createService(RetrofitServices.TOWN.class).getTownDetail().enqueue(new NetCallback<>(result));
    }
}
