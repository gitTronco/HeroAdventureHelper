package com.troncodroide.heroadventurehelper.citicens;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troncodroide.heroadventurehelper.Base.BaseFragment;
import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.citicens.presenter.CiticensPresenter;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.models.TownData;

import java.util.List;

public class CiticensFragment extends BaseFragment implements CiticensPresenter.CiticensPresenterInterface {
    public static final String TAG = "CiticensFragment";

    RecyclerView reciclerView;

    CiticensPresenter presenter;
    private String town;

    public CiticensFragment() {
    }

    public static CiticensFragment newInstance(String town) {
        CiticensFragment fragment = new CiticensFragment();
        Bundle args = new Bundle();
        args.putString("TOWN", town);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            town = getArguments().getString("TOWN");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        reciclerView = (RecyclerView) inflater.inflate(R.layout.fragment_citicens, container, false);
        presenter = new CiticensPresenter(this);
        presenter.getCiticens(town);
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
    public void onCiticensSuccess(List<CiticenData> items) {
        reciclerView.setLayoutManager(new LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false));
        reciclerView.setAdapter(new CiticensCardListViewAdapter(items, new CiticensCardListViewAdapter.CiticensListListener() {
            @Override
            public void onItemClick(CiticenData data) {
                presenter.help(data);
            }

            @Override
            public void onFriendClick(CiticenData data, String friend) {
                presenter.goTo(friend);
            }

            @Override
            public void onProfesionClick(CiticenData data, String profesion) {
                presenter.help(data, profesion);
            }
        }));
    }

    @Override
    public void scrollToCiticenPosition(int position) {
        reciclerView.getLayoutManager().scrollToPosition(position);

    }

    @Override
    public void reloadCiticensChanges() {
        reciclerView.getAdapter().notifyDataSetChanged();
    }
}
