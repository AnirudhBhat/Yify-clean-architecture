package com.abhat.yifycleanarchitecture.data.service;

import android.util.Log;

import com.abhat.yifycleanarchitecture.App;
import com.abhat.yifycleanarchitecture.data.Constants;
import com.abhat.yifycleanarchitecture.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class RestClient {
    static public Retrofit retrofit;

    private RestClient() {

    }

    static Retrofit getInstance() {
        return retrofit;
    }

    public static ApiService getApiServiceForMovies() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .addInterceptor(logging)
                .cache(provideCache())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ApiService.class);
    }

    private static Cache provideCache () {
        Cache cache = null;
        try
        {
            cache = new Cache( new File(App.getContext().getCacheDir(), "http-cache" ),
                    10 * 1024 * 1024 ); // 10 MB
        }
        catch (Exception e)
        {
            Log.e( "OKHTTP", "Could not create Cache!" );
        }
        return cache;
    }

    public static Interceptor provideCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept (Chain chain) throws IOException
            {
                Response response = chain.proceed( chain.request() );

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge( 2, TimeUnit.MINUTES )
                        .build();

                return response.newBuilder()
                        .header( "Cache-Control", cacheControl.toString() )
                        .removeHeader("Pragma")
                        .build();
            }
        };
    }

    public static Interceptor provideOfflineCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept (Chain chain) throws IOException
            {
                Request request = chain.request();

                if ( !Utils.isOnline() )
                {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale( 7, TimeUnit.DAYS )
                            .build();

                    request = request.newBuilder()
                            .cacheControl( cacheControl )
                            .removeHeader("Pragma")
                            .build();
                }

                return chain.proceed( request );
            }
        };
    }

}
