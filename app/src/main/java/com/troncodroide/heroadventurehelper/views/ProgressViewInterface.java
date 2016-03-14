package com.troncodroide.heroadventurehelper.views;

public interface ProgressViewInterface {
    void setTitle(String title);

    void setMessage(String message);

    void setProgressView(int progress);

    void setMax(int maxProgress);

    void show();

    void hide();
}
