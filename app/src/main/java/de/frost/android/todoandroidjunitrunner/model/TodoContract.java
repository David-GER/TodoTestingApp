package de.frost.android.todoandroidjunitrunner.model;

import android.provider.BaseColumns;

/**
 * Created by david on 23.02.17.
 */

public final class TodoContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TodoContract() {}

    /* Inner class that defines the table contents */
    public static class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_LOCATION = "location";
    }
}