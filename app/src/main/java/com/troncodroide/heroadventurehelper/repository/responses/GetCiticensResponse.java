package com.troncodroide.heroadventurehelper.repository.responses;

import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;

import java.util.List;

public class GetCiticensResponse extends Response<List<CiticenDataRepository>> {

    public GetCiticensResponse(List<CiticenDataRepository> data) {
        super(data);
    }
}
