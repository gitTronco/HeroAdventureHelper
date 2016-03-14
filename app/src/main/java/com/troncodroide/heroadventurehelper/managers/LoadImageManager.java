package com.troncodroide.heroadventurehelper.managers;

import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.R;

public class LoadImageManager {

    private static RequestManager getImageManager() {
        return Glide.with(APP.getContext());
    }

    public static DrawableTypeRequest loadImage(String urls) {
        return getImageManager().load(urls);
    }

    public static DrawableTypeRequest loadImage(int resId) {
        return getImageManager().load(resId);
    }

    public static void loadImage(String urls, ImageView imageView) {
        getImageManager().load(urls).error(R.color.colorPrimaryDark).into(imageView);
    }

    public static void loadImage(int resID, ImageView imageView) {
        getImageManager().load(resID).error(R.color.colorPrimaryDark).into(imageView);

    }
}
