package de.frost.android.todoandroidjunitrunner.model;

import android.content.ContentValues;
import android.net.Uri;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by david on 23.02.17.
 */
public class TodoTest {
    @Test
    public void getImage() throws Exception {

    }

    @Test
    public void setImage() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {

    }

    /*
    @Ignore // TODO: 23.02.17 Add to robotium test?
    @Test
    public void createContentValuesInstance() throws Exception {
        final Todo todoUnderTest = new Todo(
                "This is a test",
                10.123,
                11.456,
                "At home",
                "my/uri"
        );

        final ContentValues contentValuesInstance = todoUnderTest.createContentValuesInstance();

        assertEquals(todoUnderTest.getDescription(), contentValuesInstance.getAsString(TodoContract.TodoEntry.COLUMN_NAME_DESCRIPTION));

        assertEquals(todoUnderTest.getLatitude(), contentValuesInstance.getAsDouble(TodoContract.TodoEntry.COLUMN_NAME_LATITUDE), 0.001d);
        assertEquals(todoUnderTest.getLongitude(), contentValuesInstance.getAsDouble(TodoContract.TodoEntry.COLUMN_NAME_LONGITUDE), 0.001d);

        assertEquals(todoUnderTest.getLocation(), contentValuesInstance.getAsString(TodoContract.TodoEntry.COLUMN_NAME_LOCATION));

        assertEquals(todoUnderTest.getImage().toString(), contentValuesInstance.getAsString(TodoContract.TodoEntry.COLUMN_NAME_IMAGE));


    }*/

}