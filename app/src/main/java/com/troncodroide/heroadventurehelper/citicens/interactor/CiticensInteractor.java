package com.troncodroide.heroadventurehelper.citicens.interactor;

import com.troncodroide.heroadventurehelper.Base.interfaces.ErrorListener;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.Repository;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;
import com.troncodroide.heroadventurehelper.repository.responses.GetCiticensResponse;

import java.util.LinkedList;
import java.util.List;

public class CiticensInteractor {

    public interface CiticensListener extends ErrorListener {
        void onGetCiticensSuccess(List<CiticenData> items);
    }

    public void getCiticens(BaseRequest<GetCiticensRequest> request, final CiticensListener listener) {
        Repository.TOWN.getCiticens(request, new Response.Listener<GetCiticensResponse>() {
            @Override
            public void onSuccess(GetCiticensResponse data, boolean hideLoading) {
                listener.onGetCiticensSuccess(validateAndTrasnformCiticenData(data.getData()));
            }

            @Override
            public void onError(Response.Error data) {
                listener.onError(data.getErrorCode(), data.toString());
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

