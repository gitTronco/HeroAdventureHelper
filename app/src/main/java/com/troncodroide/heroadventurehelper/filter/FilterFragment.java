package com.troncodroide.heroadventurehelper.filter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.troncodroide.heroadventurehelper.Base.BaseDialogFragment;
import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tronco on 08/03/2016.
 */
public class FilterFragment extends BaseDialogFragment implements FilterPresenter.FilterPresenterInterface {

    public static final String TAG = "FilterFragment";

    FilterPresenter presenter;

    String town;

    FragmentFilterListener _listener;

    @Bind(R.id.recycler)
    RecyclerView recyclerView;

    @OnClick({R.id.filter_apply, R.id.filter_cancel, R.id.filter_clear})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filter_apply:
                applyFilters();
                break;
            case R.id.filter_cancel:
                dismiss();
                break;
            case R.id.filter_clear:
                clearFilters();
                break;
        }
    }

    private void clearFilters() {
        presenter.clearFIlters();
    }

    private void applyFilters() {
        if (_listener != null) {
            _listener.onFilterItems(presenter.getCacheFilters());
        }
        dismiss();
    }

    public void setListener(FragmentFilterListener listener) {
        this._listener = listener;
    }

    public interface FragmentFilterListener {
        void onFilterItems(List<FilterPresenter.FilterCategory> list);
    }

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
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.bind(this, view);
        presenter = new FilterPresenter(this);
        presenter.getFilters(town);
        return view;
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
