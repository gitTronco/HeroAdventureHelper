package com.troncodroide.heroadventurehelper.Base.interfaces;

public interface PresenterListener extends ErrorListener {

    void startLoading(String title, String message);

    void stopLoading();

}
