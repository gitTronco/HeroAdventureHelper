package com.troncodroide.heroadventurehelper.Base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.troncodroide.heroadventurehelper.APP;

/**
 * Base activity responsible for saving the context, show and hide mistakes and loading processes
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog pd;

    protected CoordinatorLayout snackbar_target;

    public void showActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }

    public void hideActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void showLoading(String title, int resmessage) {
        showLoading(title, APP.GetString(resmessage));
    }

    public void showLoading(String title, String message) {
        if (pd == null) {
            pd = loadingDialog(title, message);
        } else {
            pd.setTitle(title);
            pd.setMessage(message);
        }
    }

    public void hideLoading() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    private ProgressDialog loadingDialog(String title, String message) {

        ProgressDialog progDialog = new ProgressDialog(this);
        progDialog.setCancelable(false);
        progDialog.setTitle(title);
        progDialog.setMessage(message);
        progDialog.show();

        return progDialog;
    }

    public void showMessage(String message) {
        Snackbar.make(snackbar_target, message, Snackbar.LENGTH_LONG).show();
    }
}
