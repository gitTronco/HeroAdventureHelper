package com.troncodroide.heroadventurehelper.repository.retrofit;

import android.util.Log;

import com.troncodroide.heroadventurehelper.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroManager {

    private static Retrofit retrofit;
    private static OkHttpClient client;


    private static Retrofit newOauthInstance() {


        if (retrofit == null) {

            HttpLoggingInterceptor logger = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("RetrofitLog", message);
                }
            });
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);

            Interceptor requestInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            };

            client = new OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .addInterceptor(logger)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BuildConfig.urlWS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return newOauthInstance().create(serviceClass);
    }


}
