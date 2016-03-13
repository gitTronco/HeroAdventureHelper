package com.troncodroide.heroadventurehelper.citicens.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.troncodroide.heroadventurehelper.Base.BasePresenter;
import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
import com.troncodroide.heroadventurehelper.citicens.interactor.CiticensInteractor;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter.SelectedFilters;
import com.troncodroide.heroadventurehelper.managers.HeroManager;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;

import java.util.LinkedList;
import java.util.List;

public class CiticensPresenter extends BasePresenter implements CiticensInteractor.CiticensListener {

    CiticensInteractor _interactor;
    private List<CiticenData> originalCiticens;

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
        if (originalCiticens == null)
            _interactor.getCiticens(new BaseRequest<>(new GetCiticensRequest(town)), this);
        else {
            onGetCiticensSuccess(originalCiticens);
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
        if (originalCiticens == null)
            originalCiticens = items;
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
        if (originalCiticens != null) {
            for (CiticenData data : originalCiticens) {
                if (data.getName().contentEquals(name)) {
                    return originalCiticens.indexOf(data);
                }
            }
        }
        return 0;
    }

    public void filter(final SelectedFilters selectedFilters) {
        startFiltering();
        onGetCiticensSuccess(new LinkedList<CiticenData>());
        new AsyncTask<Void, CiticenData, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(500);
                    for (CiticenData citicen : originalCiticens) {
                        if (selectedFilters.evalue(citicen)) {
                            publishProgress(citicen);
                        } else {
                            publishProgress(null);
                        }
                    }
                } catch (Exception ex) {
                }
                return null;
            }

            int size = 0;
            int progress = 0;

            @Override
            protected void onProgressUpdate(CiticenData... values) {
                super.onProgressUpdate(values);
                progress++;
                progressFiltering(progress);
                if (values != null && values[0] != null) {
                    size++;
                    ((CiticensPresenterInterface) _listener).onCiticenSuccess(values[0]);
                }
            }

            @Override
            protected void onPostExecute(Void voidd) {
                super.onPostExecute(voidd);
                stopFiltering();
                Log.i("Filter", "onPostExecute: " + size);
            }
        }.execute();
    }

    private void startFiltering() {
        ((CiticensPresenterInterface) _listener).startFiltering("Filter process", String.format("Filtering (0/%d)", originalCiticens.size()), originalCiticens.size());
    }

    private void progressFiltering(int progress) {
        ((CiticensPresenterInterface) _listener).progressFiltering(progress, String.format("Filtering (%d/%d)", progress, originalCiticens.size()));
    }

    private void stopFiltering() {
        ((CiticensPresenterInterface) _listener).stopFiltering();
    }


    public interface CiticensPresenterInterface extends PresenterListener {
        void onCiticensSuccess(List<CiticenData> item);

        void onCiticenSuccess(CiticenData item);

        void startFiltering(String title, String message, int max);

        void progressFiltering(int progress, String message);

        void stopFiltering();

        void scrollToCiticenPosition(int position);

        void reloadCiticensChanges();
    }


}

