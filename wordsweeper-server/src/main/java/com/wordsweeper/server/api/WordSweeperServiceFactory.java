package com.wordsweeper.server.api;

import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by francisco on 10/12/16.
 *
 * @author Francisco Guerrero afguerrerohernan@wpi.edu
 */
public class WordSweeperServiceFactory {

    static final String WORDSWEEPER_SERVER_URL_KEY = "wordsweeper.rest.server.url";

    /**
     * The constant LOG_TAG.
     */
    public static final String LOG_TAG = WordSweeperServiceFactory.class.getSimpleName();

    /**
     * The service interface.
     */
    private static final WordSweeperService service;

    /**
     * The retrofit service.
     */
    private static final Retrofit retrofit;

    static {

        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = StringUtils.defaultIfBlank(System.getProperty(WORDSWEEPER_SERVER_URL_KEY), "http://localhost:8080");

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
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

    /**
     * Return the retrofit object.
     *
     * @return the retrofit
     */
    public static Retrofit retrofit() {
        return retrofit;
    }
}
