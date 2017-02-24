package de.frost.android.todoandroidjunitrunner.connection;

import de.frost.android.todoandroidjunitrunner.Secrets;
import de.frost.android.todoandroidjunitrunner.model.PixiImageResponse;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 24.02.17.
 */

public class RestClient {
    private static RestClient restClient;

    public static final String API_URL = "https://pixabay.com/";
    private final PixabayAPI pixabayAPI;


    private RestClient() {
        /*
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("key", "4653901-e3420f3030ea36e31c61c932f")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
//                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pixabayAPI = retrofit.create(PixabayAPI.class);
    }

    public static RestClient getInstance() {
        if (restClient == null) restClient = new RestClient();

        return restClient;
    }

    public void loadImages(String query, Callback<PixiImageResponse> callback) {
        pixabayAPI.loadImages(Secrets.PixabayKey, query).enqueue(callback);
    }

}
