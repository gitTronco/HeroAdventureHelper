package com.troncodroide.heroadventurehelper.towns.presenter;

import com.troncodroide.heroadventurehelper.Base.BasePresenter;
import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
import com.troncodroide.heroadventurehelper.models.HeroData;
import com.troncodroide.heroadventurehelper.models.TownData;
import com.troncodroide.heroadventurehelper.towns.interactor.TownInteractor;

import java.util.List;

public class TownPresenter extends BasePresenter implements TownInteractor.TownsListener {

    TownInteractor _interactor;

    public TownPresenter(LaunchPresenterInterface listener) {
        super(listener);
        _interactor = new TownInteractor();
    }

    @Override
    public void onError(int errorCode, String name) {
        stopLoading();
        _listener.onError(errorCode, name);
    }


    public void getTowns() {
        startLoading("APP", "Loading...");
        _interactor.getTowns(this);
    }

    private void stopLoading() {
        _listener.stopLoading();
    }

    private void startLoading(String title, String message) {
        _listener.startLoading(title, message);
    }


    @Override
    public void onGetTownsSuccess(List<TownData> items) {
        stopLoading();
        ((LaunchPresenterInterface) _listener).onTownsSuccess(items);

    }


    public interface LaunchPresenterInterface extends PresenterListener {
        void onTownsSuccess(List<TownData> item);
    }
}
