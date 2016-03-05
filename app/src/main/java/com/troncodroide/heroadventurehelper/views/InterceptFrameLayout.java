package com.troncodroide.heroadventurehelper.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.troncodroide.heroadventurehelper.APP;

public class InterceptFrameLayout extends FrameLayout {

    private InterceptListener interceptListener;
    private InputMethodManager _inputManager;

    public interface InterceptListener {
        void onInterceptTouch(MotionEvent ev, View v);
    }

    public InterceptFrameLayout(Context context) {
        super(context);
    }

    public InterceptFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InterceptFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setInterceptListener(InterceptListener interceptListener) {
        this.interceptListener = interceptListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_DOWN) {

            if (interceptListener != null) {
                interceptListener.onInterceptTouch(ev, this);
            } else {
                hideKeyboard(this);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void hideKeyboard(View view) {
        if (_inputManager == null)
            _inputManager = (InputMethodManager) APP.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        _inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
