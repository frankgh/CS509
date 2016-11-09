package com.wordsweeper.server.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by francisco on 10/12/16.
 */
public class WordSweeperServiceFactory {

    public static final String LOG_TAG = WordSweeperServiceFactory.class.getSimpleName();
    private static final WordSweeperService service;

    static {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/wordsweeper/rest/") // TODO: hardcoded URL
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WordSweeperService.class);
    }

    public static WordSweeperService getService() {
        return service;
    }
}
