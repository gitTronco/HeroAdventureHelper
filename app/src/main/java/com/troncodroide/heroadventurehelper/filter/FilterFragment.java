package com.troncodroide.heroadventurehelper.filter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troncodroide.heroadventurehelper.Base.BaseDialogFragment;
import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;

import java.util.List;

/**
 * Created by Tronco on 08/03/2016.
 */
public class FilterFragment extends BaseDialogFragment implements FilterPresenter.FilterPresenterInterface {

    public static final String TAG = "FilterFragment";

    RecyclerView recyclerView;

    FilterPresenter presenter;

    String town;

    public FilterFragment() {
    }

    public static FilterFragment newInstance(String town) {
        FilterFragment fragment = new FilterFragment();
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
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_filter, container, false);
        presenter = new FilterPresenter(this);
        presenter.getFilters(town);
        return recyclerView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = super.onCreateDialog(savedInstanceState);
        d.setTitle("FILTERS");
        return d;
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
    public void onFilterSuccess(List<FilterPresenter.FilterCategory> item) {
        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
        recyclerView.setAdapter(new FilterRecyclerAdapter(item));
    }
}
