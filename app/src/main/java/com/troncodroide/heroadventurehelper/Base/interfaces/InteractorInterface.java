package com.troncodroide.heroadventurehelper.Base.interfaces;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;

public interface InteractorInterface <T,K> {
    void getData(Response.Listener<K> listener);
    K transformData(T data);
}
