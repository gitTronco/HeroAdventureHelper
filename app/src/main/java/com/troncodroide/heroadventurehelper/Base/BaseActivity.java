package com.troncodroide.heroadventurehelper.Base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.APP;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity {
    private ProgressDialog pd;

    public CoordinatorLayout snackbar_target;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

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
//        {
//            TextView title, message;
//
//            @Override
//            public void setMessage(CharSequence message) {
//                super.setMessage(message);
//                if (this.message == null)
//                    this.message = (TextView) this.findViewById(android.R.id.message);
//                this.message.setText(message);
//            }
//
//            @Override
//            public void setTitle(CharSequence title) {
//                super.setTitle(title);
//                if (this.title == null)
//                    this.title = (TextView) this.findViewById(android.R.id.title);
//                this.title.setText(title);
//            }
//        };
        progDialog.setCancelable(false);
        progDialog.show();
        //progDialog.setContentView(R.layout.progress_dialog_simple);
        progDialog.setTitle(title);
        progDialog.setMessage(message);
        //progDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return progDialog;
    }

    public void showMessage(String message) {
        Snackbar.make(snackbar_target, message, Snackbar.LENGTH_LONG).show();
    }
}
