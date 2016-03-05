package com.troncodroide.heroadventurehelper.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class InterceptRelativeLayout extends RelativeLayout{

    private InterceptListener interceptListener;

    public interface InterceptListener {
        void onInterceptTouch(MotionEvent ev, View v);
    }

    public InterceptRelativeLayout(Context context) {
        super(context);
    }

    public InterceptRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InterceptRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

}
