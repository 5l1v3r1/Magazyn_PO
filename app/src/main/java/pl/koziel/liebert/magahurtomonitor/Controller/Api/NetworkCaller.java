package pl.koziel.liebert.magahurtomonitor.Controller.Api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wojciech.liebert on 02.01.2018.
 */

public class NetworkCaller {

    private Retrofit retrofit;
    private MagazynApi service;

    public NetworkCaller() {
        setupCaller();
    }

    private void setupCaller() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MagazynApi.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(MagazynApi.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public MagazynApi getService() {
        return service;
    }
}
