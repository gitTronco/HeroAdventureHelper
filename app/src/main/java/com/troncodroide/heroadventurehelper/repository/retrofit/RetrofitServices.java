package com.troncodroide.heroadventurehelper.repository.retrofit;

import com.troncodroide.heroadventurehelper.repository.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.responses.GetTownInfoResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public class RetrofitServices {
    public interface TOWN {
        @GET("/AXA-GROUP-SOLUTIONS/mobilefactory-test/master/data.json")
        Call<Map<String,List<CiticenData>>> getTownDetail();
    }
}
