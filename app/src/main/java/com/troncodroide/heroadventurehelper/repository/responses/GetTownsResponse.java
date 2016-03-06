package com.troncodroide.heroadventurehelper.repository.responses;

import com.troncodroide.heroadventurehelper.models.TownData;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;
import com.troncodroide.heroadventurehelper.repository.models.TownDataRepository;

import java.util.List;
import java.util.Map;

public class GetTownsResponse extends Response<List<TownDataRepository>> {

    public GetTownsResponse(List<TownDataRepository> data) {
        super(data);
    }

}
