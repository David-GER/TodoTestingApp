package de.frost.android.todoandroidjunitrunner.model;

/**
 * Created by david on 24.02.17.
 */

public class ImageModel {
    private String name, url;

    public ImageModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
