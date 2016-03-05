package com.troncodroide.heroadventurehelper.repository;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;
import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.repository.api.TTL;
import com.troncodroide.heroadventurehelper.repository.api.cache.DiskCache;
import com.troncodroide.heroadventurehelper.repository.api.cache.MemCache;
import com.troncodroide.heroadventurehelper.repository.api.net.API;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetTownInfoRequest;
import com.troncodroide.heroadventurehelper.repository.responses.GetCiticensResponse;
import com.troncodroide.heroadventurehelper.repository.responses.GetTownInfoResponse;

import java.util.List;
import java.util.Map;

public class RepoDAO {

    private static MemCache memCache;
    private static DiskCache diskCache;
    private static Prefser prefser;

    public static MemCache getMemCache() {
        if (memCache == null) {
            memCache = new MemCache();
        }
        return memCache;
    }

    public static DiskCache getDiskCache() {
        if (diskCache == null) {
            diskCache = new DiskCache();
        }
        return diskCache;
    }

    private static Prefser getPrefser() {
        if (prefser == null)
            prefser = new Prefser(APP.getContext());

        return prefser;
    }

    public static class Hero {

        public static void getHeroData(final BaseRequest request, final Response.Listener listener) {

        }

        public static void addNewHero(BaseRequest request, Response.Listener listener) {

        }

        public static void editHeroData(BaseRequest request, final Response.Listener listener) {

        }

        public static void deleteHero(BaseRequest request, final Response.Listener listener) {

        }

        public static void getHeros(BaseRequest request, final Response.Listener listener) {

        }

        public static void getHeroStatistics(final BaseRequest request, final Response.Listener listener) {

        }
    }

    public static class TOWN {

        public static void getInfoTown(final BaseRequest request, Response.Listener listener) {

        }

        public static void getCiticens(final BaseRequest<GetCiticensRequest> request, final Response.Listener<GetCiticensResponse> listener) {
            getMemCache().getData(request.hashRequest(), new MemCache.CacheListener<GetCiticensResponse>() {
                @Override
                public void onCacheDataRetrieved(String key, GetCiticensResponse data, boolean isAlive) {
                    listener.onSuccess(data, true);
                }

                @Override
                public void onNoCacheDataFound(final String key) {
                    DATA.getData(new BaseRequest<>(new GetTownInfoRequest()), new Response.Listener<GetTownInfoResponse>() {
                        @Override
                        public void onSuccess(GetTownInfoResponse data, boolean hideLoading) {
                            List<CiticenData> citicens = data.getData().get(request.getData().getTown());
                            GetCiticensResponse response = new GetCiticensResponse(citicens);
                            getMemCache().putData(key, response);
                            listener.onSuccess(response, true);
                        }

                        @Override
                        public void onError(Response.Error error) {
                            listener.onError(error);
                        }
                    });
                }
            });
        }
    }

    public static class CITICEN {

        public static void getCiticenDetails(final BaseRequest request, Response.Listener result) {
        }
    }

    public static class DATA {

        public static void getData(final BaseRequest<GetTownInfoRequest> request, final Response.Listener<GetTownInfoResponse> listener) {
            getMemCache().getData(request.hashRequest(), new MemCache.CacheListener<GetTownInfoResponse>() {
                @Override
                public void onCacheDataRetrieved(String key, GetTownInfoResponse data, boolean isAlive) {
                    listener.onSuccess(data, true);
                }

                @Override
                public void onNoCacheDataFound(String key) {
                    TypeToken<TTL<GetTownInfoResponse>> typeToken = new TypeToken<TTL<GetTownInfoResponse>>() {
                    };
                    getDiskCache().getData(key, typeToken, new DiskCache.DiskListener<GetTownInfoResponse>() {
                        @Override
                        public void onDiskDataRetrieved(final String key, GetTownInfoResponse data, boolean isAlive) {
                            listener.onSuccess(data, true);
                            if (!isAlive){
                                API.getAppData(new API.APIListener<Map<String, List<CiticenData>>>() {
                                    @Override
                                    public void onSuccess(Map<String, List<CiticenData>> data) {
                                        getMemCache().putData(key, new GetTownInfoResponse(data));
                                        getDiskCache().putData(key, new GetTownInfoResponse(data));
                                    }

                                    @Override
                                    public void onError(Response.Error<String> error) {
                                        listener.onError(error);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNoDiskDataFound(final String key) {
                            API.getAppData(new API.APIListener<Map<String, List<CiticenData>>>() {
                                @Override
                                public void onSuccess(Map<String, List<CiticenData>> data) {
                                    getMemCache().putData(key, new GetTownInfoResponse(data));
                                    getDiskCache().putData(key, new GetTownInfoResponse(data));
                                    listener.onSuccess(new GetTownInfoResponse(data), true);
                                }

                                @Override
                                public void onError(Response.Error<String> error) {
                                    listener.onError(error);
                                }
                            });
                        }
                    });
                }
            });
        }
    }
}
