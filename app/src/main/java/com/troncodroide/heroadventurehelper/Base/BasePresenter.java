package com.troncodroide.heroadventurehelper.Base;

import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;
/**
 * Base Presenter thar provides a presenter listener. All of presenter class must extends this.
 * */
public class BasePresenter {

    protected PresenterListener _listener;

    public BasePresenter(PresenterListener listener) {
        _listener = listener;
    }

}
