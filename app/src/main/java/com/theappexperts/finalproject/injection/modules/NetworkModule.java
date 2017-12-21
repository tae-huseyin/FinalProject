package com.theappexperts.finalproject.injection.modules;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.theappexperts.finalproject.data.network.consts.Constants;
import com.theappexperts.finalproject.data.network.services.RequestInterface;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "http://food2fork.com/api/";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final int MAX_STALE = 60 * 60 * 1;
    private static final int MAX_AGE = 60;
    private static final String HEADER_NAME = "Cache-Control";

    private static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = chain -> {
        Response originalResponse = chain.proceed(chain.request()); //Gets the intercepted request.
        String cacheControl = originalResponse.header(HEADER_NAME);

        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            Log.i("Values", "REWRITE_RESPONSE_CACHE");
            return originalResponse.newBuilder()
                    .header(HEADER_NAME, "public, max-age=" + MAX_AGE)
                    .build();
        } else {
            Log.i("Values", "REWRITE_RESPONSE_INTERCEPTOR");
            return originalResponse;
        }
    };

    @Provides
    @Singleton
    File provideHttpCacheDirectory(){return new File("test-cache",  "responses");}

    @Provides
    @Singleton
    Cache providesCache(File httpCacheDirectory)
    {
        return new Cache(httpCacheDirectory, CACHE_SIZE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder().
                addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .cache(cache)
                .build();
    }

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString(){
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCallAdapter(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converter, CallAdapter.Factory callAdapter, @Named(NAME_BASE_URL) String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapter)
                .build();
    }

    @Provides
    @Singleton
    RequestInterface provideRequestInterface(Retrofit retrofit)
    {
        return retrofit.create(RequestInterface.class);
    }

}
