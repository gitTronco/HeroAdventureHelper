package com.troncodroide.heroadventurehelper.repository.responses;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;

import java.util.List;
import java.util.Map;

public class GetTownInfoResponse extends Response<Map<String, List<CiticenDataRepository>>> {

    public GetTownInfoResponse(Map<String, List<CiticenDataRepository>> data) {
        super(data);
    }

    @Override
    public String toString() {
        return "GetTownInfoResponse{}";
    }
}
