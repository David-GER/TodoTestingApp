package de.frost.android.todoandroidjunitrunner.connection;

import de.frost.android.todoandroidjunitrunner.model.PixiImageResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by david on 24.02.17.
 */

public interface PixabayAPI {
    @GET("/api/")
    Call<PixiImageResponse> loadImages(@Query("key") String key, @Query("q") String search);
}
