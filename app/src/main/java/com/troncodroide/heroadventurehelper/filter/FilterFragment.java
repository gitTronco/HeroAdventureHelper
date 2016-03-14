package com.troncodroide.heroadventurehelper.filter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.troncodroide.heroadventurehelper.Base.BaseDialogFragment;
import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.filter.models.FilterCategory;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterFragment extends BaseDialogFragment implements FilterPresenter.FilterPresenterInterface {

    public static final String TAG = "FilterFragment";

    private FilterPresenter presenter;
    private String town;
    private FragmentFilterListener _listener;

    @Bind(R.id.recycler)
    public RecyclerView recyclerView;

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
        presenter.clearFilters();
    }

    private void applyFilters() {
        if (_listener != null) {
            presenter.evaluateFilters();
            _listener.applyFilters();
        }
        dismiss();
    }

    public void setListener(FragmentFilterListener listener) {
        this._listener = listener;
    }

    public interface FragmentFilterListener {
        void applyFilters();
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
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before

        return dialog;
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
    public void onGetFilterSuccess(List<FilterCategory> item) {
        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
        recyclerView.setAdapter(new FilterRecyclerAdapter(item));
    }
}
