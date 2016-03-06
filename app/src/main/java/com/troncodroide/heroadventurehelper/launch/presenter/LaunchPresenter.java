package com.troncodroide.heroadventurehelper.launch.presenter;

import com.troncodroide.heroadventurehelper.Base.BasePresenter;
import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
import com.troncodroide.heroadventurehelper.launch.interactor.LaunchInteractor;
import com.troncodroide.heroadventurehelper.models.HeroData;

import java.util.List;

public class LaunchPresenter extends BasePresenter implements LaunchInteractor.HerosInteractor.HerosListener,LaunchInteractor.APPInteractor.APPListener  {

    LaunchInteractor.HerosInteractor _interactor;
    LaunchInteractor.APPInteractor _interactorAPP;

    public LaunchPresenter(LaunchPresenterInterface listener) {
        super(listener);
        _interactor = new LaunchInteractor.HerosInteractor();
        _interactorAPP = new LaunchInteractor.APPInteractor();
    }

    @Override
    public void onError(int errorCode, String name) {
        stopLoading();
        _listener.onError(errorCode, name);
    }

    public void getAPPdata(){
        startLoading("APP","Loading...");
        _interactorAPP.getAppData(this);
    }

    public void getHeros() {
        _interactor.getHeros(this);
    }

    private void stopLoading() {
        _listener.stopLoading();
    }

    private void startLoading(String title, String message) {
        _listener.startLoading(title, message);
    }

    @Override
    public void onGetHerosSuccess(List<HeroData> items) {
        stopLoading();
        ((LaunchPresenterInterface) _listener).onHerosSuccess(items);
    }

    @Override
    public void onGetAppDataSuccess() {
        getHeros();
    }

    public interface LaunchPresenterInterface extends PresenterListener {
        void onHerosSuccess(List<HeroData> item);
    }
}
