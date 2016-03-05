package com.troncodroide.heroadventurehelper.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class InterceptScrollView extends ScrollView {

    private InterceptListener interceptListener;

    public interface InterceptListener {
        void onInterceptTouch(MotionEvent ev, View v);
    }

    public InterceptScrollView(Context context) {
        super(context);
    }

    public InterceptScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InterceptScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setInterceptListener(InterceptListener interceptListener) {
        this.interceptListener = interceptListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        if (action == MotionEvent.ACTION_DOWN) {

            if (interceptListener != null) {
                interceptListener.onInterceptTouch(ev, this);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

}
