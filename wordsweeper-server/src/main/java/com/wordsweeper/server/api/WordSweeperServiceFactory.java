package com.wordsweeper.server.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by francisco on 10/12/16.
 *
 * @author Francisco Guerrero afguerrerohernan@wpi.edu
 */
public class WordSweeperServiceFactory {

    /**
     * The constant LOG_TAG.
     */
    public static final String LOG_TAG = WordSweeperServiceFactory.class.getSimpleName();

    /**
     * The service interface.
     */
    private static final WordSweeperService service;

    static {

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/wordsweeper/rest/") // TODO: hardcoded URL
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WordSweeperService.class);
    }

    /**
     * Gets WordSweeper service.
     *
     * @return the service
     */
    public static WordSweeperService getService() {
        return service;
    }
}
