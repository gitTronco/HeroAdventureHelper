package com.troncodroide.heroadventurehelper.managers;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.R;

public class LoadImageManager {

    //private static Picasso picasso;

    private static RequestManager getImageManager() {
//        if (picasso == null) {
//            picasso = Picasso.with(CAT.getContext());
//        }
//        return picasso;
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

    public static void loadImage(String url, ImageView imageView, final Palette.PaletteAsyncListener listener) {
        getImageManager()
                .load(url)
                .asBitmap()
                .error(R.color.cardview_dark_background)
                .into(new BitmapImageViewTarget(imageView) {
                          @Override
                          public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                              super.onResourceReady(resource, glideAnimation);
                              Palette.generateAsync(resource, listener);
                          }
                      }
                );

    }

    public static void loadImage(int resID, ImageView imageView, final Palette.PaletteAsyncListener listener) {
        getImageManager()
                .load(resID)
                .asBitmap()
                .error(R.color.cardview_dark_background)
                .into(new BitmapImageViewTarget(imageView) {
                          @Override
                          public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                              super.onResourceReady(resource, glideAnimation);
                              Palette.generateAsync(resource, listener);
                          }
                      }
                );

    }
}
