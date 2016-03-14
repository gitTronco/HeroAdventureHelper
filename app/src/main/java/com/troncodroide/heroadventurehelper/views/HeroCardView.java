package com.troncodroide.heroadventurehelper.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.managers.HeroManager;
import com.troncodroide.heroadventurehelper.managers.LoadImageManager;
import com.troncodroide.heroadventurehelper.models.HeroData;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HeroCardView extends FrameLayout {

    private UserBindView userview;
    private HeroData data;
    private HeroCardInteractor _listener;

    public HeroCardView(Context context) {
        super(context);
        _init(context);
    }

    public HeroCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    public HeroCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _init(context);
    }

    public void setData(HeroData data) {
        this.data = data;
        loadData(data);
    }

    private void loadData(HeroData data) {
        this.userview.mLevel.setText(String.format("Level: %d", data.getLevel()));
        this.userview.mName.setText(data.getName());
        LoadImageManager.loadImage(data.getResImage(), this.userview.mImage);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_listener != null) {
                    _listener.onItemClick();
                }
            }
        });
        this.userview.mImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_listener != null) {
                    _listener.onImageClick();
                }
            }
        });
    }


    public void setListener(HeroCardInteractor listener) {
        this._listener = listener;
    }

    private void _init(Context c) {
        View v = inflate(c, R.layout.item_hero, this);
        if (!isInEditMode()) {
            userview = new UserBindView(v);
            loadData(HeroManager.getHeroSession());
        }
    }

    public interface HeroCardInteractor {
        void onImageClick();

        void onItemClick();
    }

    public class UserBindView {

        @Bind(R.id.hero_image)
        public ImageView mImage;
        @Bind(R.id.hero_name)
        public TextView mName;
        @Bind(R.id.hero_level)
        public TextView mLevel;

        public UserBindView(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
