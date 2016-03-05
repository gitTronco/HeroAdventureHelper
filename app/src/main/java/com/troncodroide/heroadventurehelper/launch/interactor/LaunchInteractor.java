package com.troncodroide.heroadventurehelper.launch.interactor;

import com.troncodroide.heroadventurehelper.Base.interfaces.ErrorListener;
import com.troncodroide.heroadventurehelper.models.HeroData;
import com.troncodroide.heroadventurehelper.repository.Repository;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetHerosRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetTownInfoRequest;
import com.troncodroide.heroadventurehelper.repository.responses.GetHerosResponse;
import com.troncodroide.heroadventurehelper.repository.responses.GetTownInfoResponse;

import java.util.List;

public class LaunchInteractor {

    public static class HerosInteractor {
        public interface HerosListener extends ErrorListener {
            void onGetHerosSuccess(List<HeroData> items);
        }

        public void getHeros(final HerosListener listener) {
            Repository.HERO.getHeros(new BaseRequest<>(new GetHerosRequest()), new Response.Listener<GetHerosResponse>() {
                @Override
                public void onSuccess(GetHerosResponse data, boolean hideLoading) {
                    listener.onGetHerosSuccess(data.getData());
                }

                @Override
                public void onError(Response.Error data) {
                    listener.onError(data.getErrorCode(), data.toString());
                }
            });
        }
    }

    public static class APPInteractor {
        public interface APPListener extends ErrorListener {
            void onGetAppDataSuccess();
        }

        public void getAppData(final APPListener listener) {
            Repository.DATA.getData(new BaseRequest<>(new GetTownInfoRequest()), new Response.Listener<GetTownInfoResponse>() {
                @Override
                public void onSuccess(GetTownInfoResponse data, boolean hideLoading) {
                    listener.onGetAppDataSuccess();
                }

                @Override
                public void onError(Response.Error data) {
                    listener.onError(data.getErrorCode(), data.toString());
                }
            });
        }
    }
}
