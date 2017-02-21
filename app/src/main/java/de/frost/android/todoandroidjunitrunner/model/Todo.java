package de.frost.android.todoandroidjunitrunner.model;

import java.io.Serializable;

/**
 * Created by david on 13.02.17.
 */

public class Todo implements Serializable {
    private String description;

    public Todo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "description='" + description + '\'' +
                '}';
    }
}
