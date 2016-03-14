package com.troncodroide.heroadventurehelper.Base.interfaces;
/**
 * Presenter interface for delegate Errors and loading procedures, all presenters must extends this
 */
public interface PresenterListener extends ErrorListener {
    void startLoading(String title, String message);

    void stopLoading();

}
