package de.frost.android.todoandroidjunitrunner.connection;

import android.app.Application;

import javax.inject.Inject;

import de.frost.android.todoandroidjunitrunner.Secrets;
import de.frost.android.todoandroidjunitrunner.TodoApplication;
import de.frost.android.todoandroidjunitrunner.model.PixiImageResponse;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by david on 24.02.17.
 */

public class RestClient {
    private final PixabayAPI pixabayAPI;

    @Inject
    public Retrofit retrofit;


    public RestClient(Application application) {
        ((TodoApplication)application).getNetComponent().inject(this);

        pixabayAPI = retrofit.create(PixabayAPI.class);
    }


    public void loadImages(String query, Callback<PixiImageResponse> callback) {
        pixabayAPI.loadImages(Secrets.PixabayKey, query).enqueue(callback);
    }

}
