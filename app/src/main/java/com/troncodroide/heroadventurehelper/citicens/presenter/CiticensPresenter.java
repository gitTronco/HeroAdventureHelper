package com.troncodroide.heroadventurehelper.citicens.presenter;

import com.troncodroide.heroadventurehelper.Base.BasePresenter;
import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
import com.troncodroide.heroadventurehelper.citicens.interactor.CiticensInteractor;
import com.troncodroide.heroadventurehelper.managers.HeroManager;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;

import java.util.List;

public class CiticensPresenter extends BasePresenter implements CiticensInteractor.CiticensListener {

    CiticensInteractor _interactor;
    private List<CiticenData> citicens;

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
        if (citicens == null)
            _interactor.getCiticens(new BaseRequest<>(new GetCiticensRequest(town)), this);
        else {
            onGetCiticensSuccess(citicens);
        }
    }

    private void stopLoading() {
        _listener.stopLoading();
    }

    private void startLoading(String title, String message) {
        _listener.startLoading(title, message);
    }


    @Override
    public void onGetCiticensSuccess(List<CiticenData> items) {
        citicens = items;
        stopLoading();
        ((CiticensPresenterInterface) _listener).onCiticensSuccess(items);

    }

    public void help(CiticenData data) {
        help(data, null);
    }

    public void help(CiticenData data, String profesion) {
        data.help(HeroManager.getHeroSession(), profesion);
        ((CiticensPresenterInterface) _listener).reloadCiticensChanges();
        HeroManager.saveHeroSession();
    }

    public void goTo(String friend) {
        ((CiticensPresenterInterface) _listener).scrollToCiticenPosition(getCiticenPosition(friend));
    }

    private int getCiticenPosition(String name) {
        if (citicens != null) {
            for (CiticenData data : citicens) {
                if (data.getName().contentEquals(name)) {
                    return citicens.indexOf(data);
                }
            }
        }
        return 0;


    }

    public interface CiticensPresenterInterface extends PresenterListener {
        void onCiticensSuccess(List<CiticenData> item);

        void scrollToCiticenPosition(int position);

        void reloadCiticensChanges();
    }


}

