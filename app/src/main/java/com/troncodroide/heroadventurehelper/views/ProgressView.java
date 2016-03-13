package com.troncodroide.heroadventurehelper.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tronco on 12/03/2016.
 */
public class ProgressView extends FrameLayout implements ProgressViewInterface {
    private ProgressListener _listener;

    public ProgressView(Context context) {
        super(context);
        _init(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        _init(context);
    }

    private FilterItemBindView itemBindView;


    @Override
    public void setTitle(String title) {
        itemBindView.mTitle.setText(title);
    }

    @Override
    public void setMessage(String message) {
        itemBindView.mMessage.setText(message);
    }

    @Override
    public void setProgressView(int progress) {
        itemBindView.mProgress.setProgress(progress);
    }

    @Override
    public void setMax(int maxProgress) {
        itemBindView.mProgress.setMax(maxProgress);
    }

    @Override
    public void show() {
        itemBindView.mWrapper.open();
    }

    @Override
    public void hide() {
        itemBindView.mWrapper.close();
    }

    void setProgressListener(ProgressListener listener) {
        this._listener = listener;
    }

    private void _init(Context c) {
        View v = inflate(c, R.layout.view_progress, this);
        itemBindView = new FilterItemBindView(v);
    }

    public class FilterItemBindView {

        public View mView;

        @Bind(R.id.wrapper)
        public CollapsableLayoutView mWrapper;

        @Bind(R.id.title)
        public TextView mTitle;

        @Bind(R.id.progress_stop)
        public TextView mStop;

        @OnClick(R.id.progress_stop)
        void onClick() {
            if (_listener != null) {
                _listener.onStopPressed();
            }
        }

        @Bind(R.id.message)
        public TextView mMessage;

        @Bind(R.id.progress)
        public ProgressBar mProgress;

        public FilterItemBindView(View view) {
            mView = view;
            ButterKnife.bind(this, view);
        }
    }

    public interface ProgressListener {
        void onStopPressed();
    }

}
