package com.troncodroide.heroadventurehelper.repository.responses;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenData;

import java.util.List;
import java.util.Map;

public class GetCiticensResponse extends Response<List<CiticenData>> {

    public GetCiticensResponse(List<CiticenData> data) {
        super(data);
    }
}
