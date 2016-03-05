package com.troncodroide.heroadventurehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.troncodroide.heroadventurehelper.Base.BaseActivity;
import com.troncodroide.heroadventurehelper.launch.HerosListRecyclerViewAdapter;
import com.troncodroide.heroadventurehelper.launch.presenter.LaunchPresenter;
import com.troncodroide.heroadventurehelper.models.HeroData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LaunchActivity extends BaseActivity implements LaunchPresenter.LaunchPresenterInterface {

    @Bind(R.id.loading)
    ProgressBar loading;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.wrapper_heros_list)
    View wrapper_heros;

    LaunchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        snackbar_target = ButterKnife.findById(this, R.id.snackbar_target);
        wrapper_heros.setVisibility(View.GONE);
        presenter = new LaunchPresenter(this);
        presenter.getAPPdata();
    }

    @Override
    public void onHerosSuccess(List<HeroData> items) {
        wrapper_heros.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HerosListRecyclerViewAdapter(items, new HerosListRecyclerViewAdapter.HerosListListener() {
            @Override
            public void onItemClick(HeroData heroData) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            }
        }));
    }

    @Override
    public void startLoading(String title, String message) {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        loading.setVisibility(View.GONE);

    }

    @Override
    public void onError(int errorCode, String name) {

    }
}
