package com.troncodroide.heroadventurehelper.managers;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.R;

public class LoadImageManager {

    //private static Picasso picasso;

    private static Picasso getImageManager() {
//        if (picasso == null) {
//            picasso = Picasso.with(CAT.getContext());
//        }
//        return picasso;
        return Picasso.with(APP.getContext());
    }

    public static RequestCreator loadImage(String urls) {
        return getImageManager().load(urls);
    }

    public static RequestCreator loadImage(int resId) {
        return getImageManager().load(resId);
    }

    public static void loadImage(String urls, ImageView imageView) {
        getImageManager().load(urls).error(R.color.colorPrimaryDark).into(imageView);
    }

    public static void loadImage(int resID, ImageView imageView) {
        getImageManager().load(resID).error(R.color.colorPrimaryDark).into(imageView);

    }
}
