package com.troncodroide.heroadventurehelper.towns.interactor;

import com.troncodroide.heroadventurehelper.Base.interfaces.ErrorListener;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.models.HeroData;
import com.troncodroide.heroadventurehelper.models.TownData;
import com.troncodroide.heroadventurehelper.repository.Repository;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;
import com.troncodroide.heroadventurehelper.repository.models.TownDataRepository;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetHerosRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetTownInfoRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetTownsRequest;
import com.troncodroide.heroadventurehelper.repository.responses.GetHerosResponse;
import com.troncodroide.heroadventurehelper.repository.responses.GetTownInfoResponse;
import com.troncodroide.heroadventurehelper.repository.responses.GetTownsResponse;

import java.util.LinkedList;
import java.util.List;

public class TownInteractor {

    public interface TownsListener extends ErrorListener {
        void onGetTownsSuccess(List<TownData> items);
    }

    public void getTowns(final TownsListener listener) {
        Repository.TOWN.getTowns(new BaseRequest<>(new GetTownsRequest()), new Response.Listener<GetTownsResponse>() {
            @Override
            public void onSuccess(GetTownsResponse data, boolean hideLoading) {
                listener.onGetTownsSuccess(validateAndTrasnformData(data.getData()));
            }

            @Override
            public void onError(Response.Error data) {
                listener.onError(data.getErrorCode(), data.toString());
            }

            private List<TownData> validateAndTrasnformData(List<TownDataRepository> list) {
                List<TownData> toRet = new LinkedList<TownData>();
                for (TownDataRepository data : list) {
                    toRet.add(new TownData(data.getName(), validateAndTrasnformCiticenData(data.getCiticens())));
                }
                return toRet;
            }

            private List<CiticenData> validateAndTrasnformCiticenData(List<CiticenDataRepository> list) {
                List<CiticenData> toRet = new LinkedList<CiticenData>();
                for (CiticenDataRepository data : list) {
                    toRet.add(
                            new CiticenData(
                                    data.getName(),
                                    data.getThumbnail(),
                                    data.getHair_color(),
                                    data.getId(),
                                    data.getAge(),
                                    data.getWeight(),
                                    data.getHeight(),
                                    data.getProfessions(),
                                    data.getFriends(),
                                    null));

                }
                return toRet;
            }
        });
    }
}

