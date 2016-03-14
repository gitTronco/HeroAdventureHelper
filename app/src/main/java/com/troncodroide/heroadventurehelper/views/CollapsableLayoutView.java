package com.troncodroide.heroadventurehelper.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.troncodroide.heroadventurehelper.R;

public class CollapsableLayoutView extends LinearLayout {

    public static final int STATE_OPEN = 1;
    public static final int STATE_CLOSED = 2;
    public static final int DEFAULT_ANIMATION_DURATION = 500;
    private static final String TAG = "CollapsableLayoutView";

    private int closedHeigth = 0;
    private int openHeigth = -1;
    private int currentState = STATE_OPEN;
    private int animationDuration = DEFAULT_ANIMATION_DURATION;

    private CollapsableEventsListener listenerEvents;

    private ViewTreeObserver.OnPreDrawListener preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
        @Override
        public boolean onPreDraw() {
            openHeigth = (getMeasuredHeight() > openHeigth) ? getMeasuredHeight() : openHeigth;
            getViewTreeObserver().removeOnPreDrawListener(preDrawListener);
            if (currentState == STATE_OPEN) {
                show();
            } else {
                hide();
            }
            return true;
        }
    };

    public void logHeight() {
        Log.i("Collapsable", "Height:" + openHeigth);
    }

    public CollapsableLayoutView(Context context) {
        super(context);
        this.getViewTreeObserver().addOnPreDrawListener(preDrawListener);
        currentState = getCollapsableState(context, null);
        animationDuration = getCollapsableAnimationDuration(context, null);
    }

    public CollapsableLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.getViewTreeObserver().addOnPreDrawListener(preDrawListener);
        currentState = getCollapsableState(context, attrs);
        animationDuration = getCollapsableAnimationDuration(context, attrs);
    }

    public CollapsableLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.getViewTreeObserver().addOnPreDrawListener(preDrawListener);
        currentState = getCollapsableState(context, attrs);
        animationDuration = getCollapsableAnimationDuration(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CollapsableLayoutView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.getViewTreeObserver().addOnPreDrawListener(preDrawListener);
        currentState = getCollapsableState(context, attrs);
        animationDuration = getCollapsableAnimationDuration(context, attrs);
    }

    public void hide() {
        currentState = STATE_CLOSED;
        heightParams(this, closedHeigth, 0);
    }

    public void show() {
        currentState = STATE_OPEN;
        heightParams(this, openHeigth, 0);
    }

    public void close() {
        if (currentState != STATE_CLOSED) {
            currentState = STATE_CLOSED;
            heightParams(this, closedHeigth, animationDuration);
        }
    }

    public void open() {
        if (currentState != STATE_OPEN) {
            currentState = STATE_OPEN;
            heightParams(this, openHeigth, animationDuration);
        }
    }

    public void toggle() {
        if (currentState == STATE_CLOSED) {
            open();
        } else {
            close();
        }
    }

    public CollapsableEventsListener getListenerEvents() {
        return listenerEvents;
    }

    public void setListenerEvents(CollapsableEventsListener listenerEvents) {
        this.listenerEvents = listenerEvents;
    }

    private void heightParams(final View view, final int height, int animationDuration) {

        final ValueAnimator anim = ValueAnimator.ofInt(view.getMeasuredHeight(), height);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams ll = view.getLayoutParams();
                ll.height = val;
                ll.width = ViewGroup.LayoutParams.MATCH_PARENT;
                view.setLayoutParams(ll);
                if (listenerEvents != null) {
                    listenerEvents.onProgressPercent(getCurrentPercent(val));
                }
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (listenerEvents != null) {
                    if (currentState == STATE_OPEN) {
                        listenerEvents.onOpended();
                    } else {
                        listenerEvents.onClossed();
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.setDuration(animationDuration);
        anim.start();
    }

    private float getCurrentPercent(int val) {
        return 1 - ((openHeigth - val) / (float) openHeigth);
    }

    public static int getCollapsableState(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return STATE_OPEN;
        } else {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.CollapsableLayoutView,
                    0, 0);
            try {
                return a.getInteger(R.styleable.CollapsableLayoutView_setCollapseInitState, STATE_OPEN);
            } finally {
                a.recycle();
            }
        }
    }

    public static int getCollapsableAnimationDuration(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return 500;
        } else {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.CollapsableLayoutView,
                    0, 0);
            try {
                return a.getInteger(R.styleable.CollapsableLayoutView_setCollapseAnimationTime, DEFAULT_ANIMATION_DURATION);
            } finally {
                a.recycle();
            }
        }
    }

    public interface CollapsableEventsListener {
        void onOpended();

        void onClossed();

        void onProgressPercent(float percent);
    }
}
