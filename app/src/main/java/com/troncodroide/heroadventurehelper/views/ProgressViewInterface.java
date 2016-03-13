package com.troncodroide.heroadventurehelper.views;

/**
 * Created by Tronco on 12/03/2016.
 */
public interface ProgressViewInterface {
    void setTitle(String title);

    void setMessage(String message);

    void setProgressView(int progress);

    void setMax(int maxProgress);

    void show();

    void hide();
}
