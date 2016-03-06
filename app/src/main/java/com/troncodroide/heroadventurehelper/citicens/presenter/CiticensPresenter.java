package com.troncodroide.heroadventurehelper.citicens.presenter;

import com.troncodroide.heroadventurehelper.Base.BasePresenter;
import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
import com.troncodroide.heroadventurehelper.citicens.interactor.CiticensInteractor;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;

import java.util.List;

public class CiticensPresenter extends BasePresenter implements CiticensInteractor.CiticensListener {

    CiticensInteractor _interactor;

    public CiticensPresenter(CiticensPresenterInterface listener) {
        super(listener);
        _interactor = new CiticensInteractor();
    }

    @Override
    public void onError(int errorCode, String name) {
        stopLoading();
        _listener.onError(errorCode, name);
    }


    public void getCiticens(String town) {
        startLoading("APP", "Loading...");
        _interactor.getCiticens(new BaseRequest<>(new GetCiticensRequest(town)),this);
    }

    private void stopLoading() {
        _listener.stopLoading();
    }

    private void startLoading(String title, String message) {
        _listener.startLoading(title, message);
    }


    @Override
    public void onGetCiticensSuccess(List<CiticenData> items) {
        stopLoading();
        ((CiticensPresenterInterface) _listener).onCiticensSuccess(items);

    }


    public interface CiticensPresenterInterface extends PresenterListener {
        void onCiticensSuccess(List<CiticenData> item);
    }
}
