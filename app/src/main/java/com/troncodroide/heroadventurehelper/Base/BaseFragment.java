package com.troncodroide.heroadventurehelper.Base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.troncodroide.heroadventurehelper.APP;

/**
 * Base Fragment responsible for saving the context, show and hide mistakes and loading processes
 */
public class BaseFragment extends Fragment {

    protected Context _context;
    protected Activity _activity;
    private InputMethodManager _inputManager;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        _activity = activity;
    }

    protected void hideKeyboard(View view) {
        if (_inputManager == null)
            _inputManager = (InputMethodManager) APP.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        _inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showActionBar() {
        if (_activity instanceof BaseActivity) {
            ((BaseActivity) _activity).showActionBar();
        }
    }

    protected void hideActionBar() {
        if (_activity instanceof BaseActivity) {
            ((BaseActivity) _activity).hideActionBar();
        }
    }

    protected void showMessage(int resmessage) {
        showMessage(APP.GetString(resmessage));
    }

    protected void showMessage(String message) {
        ((BaseActivity) _activity).showMessage(message);
    }

    protected void showLoading(String title, String message) {
        ((BaseActivity) _activity).showLoading(title, message);
    }

    protected void hideLoading() {
        ((BaseActivity) _activity).hideLoading();
    }
}
