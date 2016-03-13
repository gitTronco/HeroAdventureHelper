package com.troncodroide.heroadventurehelper;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.Base.BaseActivity;
import com.troncodroide.heroadventurehelper.Base.interfaces.ToolbarInterface;
import com.troncodroide.heroadventurehelper.citicens.CiticensFragment;
import com.troncodroide.heroadventurehelper.filter.FilterFragment;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;
import com.troncodroide.heroadventurehelper.managers.ConfigurationManager;
import com.troncodroide.heroadventurehelper.managers.NavigationManager;
import com.troncodroide.heroadventurehelper.views.ProgressView;
import com.troncodroide.heroadventurehelper.views.ProgressViewInterface;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, Observer, ProgressViewInterface {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_progress)
    ProgressView toolbarProgress;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        snackbar_target = (CoordinatorLayout) findViewById(R.id.snackbar_target);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            NavigationManager.goTo(getSupportFragmentManager(), NavigationManager.TARGET_TOWNS);
            toolbarTitle.setText("TOWNS");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            final FilterFragment fragment = FilterFragment.newInstance(ConfigurationManager.getCurrentTown());
            fragment.show(getSupportFragmentManager(), FilterFragment.TAG);
            fragment.setListener(new FilterFragment.FragmentFilterListener() {
                @Override
                public void applyFilters() {
                    CiticensFragment fragment1 = (CiticensFragment) getSupportFragmentManager().findFragmentByTag(CiticensFragment.TAG);
                    if (fragment1 != null)
                        fragment1.filter();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            showMessage("Configuration");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        NavigationManager.removeObserverS(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationManager.addObserverS(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof NavigationManager) {
            toolbarTitle.setText(NavigationManager.getCurrentTargetName());
            toolbar.getMenu().clear();
            if (NavigationManager.getTarget() == NavigationManager.TARGET_CITICENS) {
                toolbar.inflateMenu(R.menu.citicens);
            } else {
                toolbar.inflateMenu(R.menu.main);
            }
        }
    }

    @Override
    public void setTitle(String title) {
        if (toolbarProgress != null) {
            toolbarProgress.setTitle(title);
        }
    }

    @Override
    public void setMessage(String message) {
        if (toolbarProgress != null) {
            toolbarProgress.setMessage(message);
        }
    }

    @Override
    public void setProgressView(int progress) {
        if (toolbarProgress != null) {
            toolbarProgress.setProgressView(progress);
        }
    }

    @Override
    public void setMax(int maxProgress) {

        if (toolbarProgress != null) {
            toolbarProgress.setMax(maxProgress);
        }
    }

    @Override
    public void show() {
        if (toolbarProgress != null) {
            toolbarProgress.show();
        }
    }

    @Override
    public void hide() {
        if (toolbarProgress != null) {
            toolbarProgress.hide();
        }
    }
}
