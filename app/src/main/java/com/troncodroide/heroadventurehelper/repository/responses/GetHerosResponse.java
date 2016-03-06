package com.troncodroide.heroadventurehelper.repository.responses;

import com.troncodroide.heroadventurehelper.models.HeroData;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;

import java.util.List;

public class GetHerosResponse extends Response<List<HeroData>> {

    public GetHerosResponse(List<HeroData> data) {
        super(data);
    }
}
