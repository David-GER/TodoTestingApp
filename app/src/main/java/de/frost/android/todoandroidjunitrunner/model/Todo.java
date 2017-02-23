package de.frost.android.todoandroidjunitrunner.model;

/**
 * Created by david on 13.02.17.
 */

public class Todo {

    private long id;
    private String description;
    private double latitude, longitude;
    private String location;
    private String image;


    public Todo() {

    }

    public Todo(String description, double latitude, double longitude, String location, String image) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.image = image;
    }

    public Todo(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", location='" + location + '\'' +
                ", image=" + image +
                '}';
    }
}
