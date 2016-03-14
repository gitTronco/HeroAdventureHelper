package com.troncodroide.heroadventurehelper.repository.responses;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.TownDataRepository;

import java.util.List;

public class GetTownsResponse extends Response<List<TownDataRepository>> {

    public GetTownsResponse(List<TownDataRepository> data) {
        super(data);
    }

}
