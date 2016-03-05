package com.troncodroide.heroadventurehelper.repository.responses;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenData;

import java.util.List;
import java.util.Map;

public class GetTownInfoResponse extends Response<Map<String, List<CiticenData>>> {

    public GetTownInfoResponse(Map<String, List<CiticenData>> data) {
        super(data);
    }

}
