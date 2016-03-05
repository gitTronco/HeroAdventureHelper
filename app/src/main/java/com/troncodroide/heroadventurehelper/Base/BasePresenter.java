package com.troncodroide.heroadventurehelper.Base;

import com.troncodroide.heroadventurehelper.Base.interfaces.PresenterListener;

public class BasePresenter {

    protected PresenterListener _listener;

    public BasePresenter(PresenterListener listener) {
        _listener = listener;
    }

}
