package com.troncodroide.heroadventurehelper.towns;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troncodroide.heroadventurehelper.Base.BaseFragment;
import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.managers.ConfigurationManager;
import com.troncodroide.heroadventurehelper.managers.NavigationManager;
import com.troncodroide.heroadventurehelper.models.TownData;
import com.troncodroide.heroadventurehelper.towns.presenter.TownPresenter;

import java.util.List;

public class TownsFragment extends BaseFragment implements TownPresenter.LaunchPresenterInterface {
    public static final String TAG = "TownsFragment";

    RecyclerView reciclerView;

    TownPresenter presenter;

    public TownsFragment() {
    }

    public static TownsFragment newInstance() {
        TownsFragment fragment = new TownsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        reciclerView = (RecyclerView) inflater.inflate(R.layout.fragment_town, container, false);

        presenter = new TownPresenter(this);
        presenter.getTowns();
        return reciclerView;
    }

    @Override
    public void startLoading(String title, String message) {
        showLoading(title, message);
    }

    @Override
    public void stopLoading() {
        hideLoading();
    }

    @Override
    public void onError(int errorCode, String name) {
        showMessage(name);
    }

    @Override
    public void onTownsSuccess(List<TownData> items) {
        reciclerView.setLayoutManager(new LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false));
        reciclerView.setAdapter(new TownsCardListViewAdapter(items, new TownsCardListViewAdapter.TownsListListener() {
            @Override
            public void onItemClick(TownData data) {
                Bundle b = new Bundle();
                b.putString("data", data.getName());
                ConfigurationManager.setCurrentTown(data.getName());
                NavigationManager.goTo(getFragmentManager(), NavigationManager.TARGET_CITICENS, b);
            }
        }));
    }

    public void reload() {
    }
}
