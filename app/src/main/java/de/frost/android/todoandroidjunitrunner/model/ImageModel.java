package de.frost.android.todoandroidjunitrunner.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by david on 24.02.17.
 */

public class ImageModel {

    @SerializedName("tags")
    private String name;

    @SerializedName("webformatURL")
    private String url;

    @SerializedName("previewURL")
    private String preview;

    public ImageModel(String name, String url, String preview) {
        this.name = name;
        this.url = url;
        this.preview = preview;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getPreview() {
        return preview;
    }
}
