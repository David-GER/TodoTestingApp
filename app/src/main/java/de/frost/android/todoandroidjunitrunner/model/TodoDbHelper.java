package de.frost.android.todoandroidjunitrunner.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import de.frost.android.todoandroidjunitrunner.model.TodoContract.TodoEntry;

/**
 * Created by david on 23.02.17.
 */

public class TodoDbHelper extends SQLiteOpenHelper implements TodoDataSource {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "todo.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoEntry.TABLE_NAME + " (" +
                    TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    TodoEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    TodoEntry.COLUMN_NAME_IMAGE + " TEXT," +
                    TodoEntry.COLUMN_NAME_LONGITUDE + " REAL," +
                    TodoEntry.COLUMN_NAME_LATITUDE + " REAL," +
                    TodoEntry.COLUMN_NAME_LOCATION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME;

    private final String[] projection = {
            TodoEntry._ID,
            TodoEntry.COLUMN_NAME_DESCRIPTION,
            TodoEntry.COLUMN_NAME_IMAGE,
            TodoEntry.COLUMN_NAME_LONGITUDE,
            TodoEntry.COLUMN_NAME_LATITUDE,
            TodoEntry.COLUMN_NAME_LOCATION
    };


    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES); //just delete everything! Not nice!
        onCreate(db);
    }


    @Override
    public long insert(Todo todo) {
        ContentValues values = createContentValuesInstance(todo);


        return getWritableDatabase().insert(
                TodoEntry.TABLE_NAME,
                null,
                values
        );
    }

    @Override
    public int update(Todo todo) {
        ContentValues values = createContentValuesInstance(todo);

        return getWritableDatabase().update(
                TodoEntry.TABLE_NAME,
                values,
                TodoEntry._ID + "=?",
                new String[]{"" + todo.getId()}
        );
    }

    @Override
    public int remove(Todo todo) {
        return remove(todo.getId());
    }

    @Override
    public int remove(long id) {
        return getWritableDatabase().delete(TodoEntry.TABLE_NAME, TodoEntry._ID + "=?", new String[]{"" + id});
    }

    @Override
    public Todo query(long id) {
        String selection = TodoEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = TodoEntry._ID + " DESC";

        Cursor cursor = getReadableDatabase().query(
                TodoEntry.TABLE_NAME,                     // The table to query
                null,                                     // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if (cursor.getCount() > 1)
            throw new IllegalStateException("There should nevery be more than one entry with the same id!" + id);

        TodoCursor todoCursor = new TodoCursor(cursor);

        todoCursor.moveToFirst();
        Todo todo = todoCursor.getTodo();
        todoCursor.close();

        return todo;
    }

    @Override
    public TodoCursor queryAll() {

        // How you want the results sorted in the resulting Cursor
        String sortOrder = TodoEntry._ID + " DESC";

        Cursor cursor = getReadableDatabase().query(
                TodoEntry.TABLE_NAME,                     // The table to query
                null,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        return new TodoCursor(cursor);
    }

    @Override
    public int removeAll() {
        return getWritableDatabase().delete(TodoEntry.TABLE_NAME, null, null);
    }

    public static class TodoCursor extends CursorWrapper {

        public TodoCursor(Cursor cursor) {
            super(cursor);
        }

        public Todo getTodo() {
            if (isBeforeFirst() || isAfterLast())
                return null;

            Todo todo = new Todo();

            todo.setId(getLong(getColumnIndex(TodoEntry._ID)));
            todo.setDescription(getString(getColumnIndex(TodoEntry.COLUMN_NAME_DESCRIPTION)));
            todo.setImage(getString(getColumnIndex(TodoEntry.COLUMN_NAME_IMAGE)));
            todo.setLocation(getString(getColumnIndex(TodoEntry.COLUMN_NAME_LOCATION)));
            todo.setLongitude(getDouble(getColumnIndex(TodoEntry.COLUMN_NAME_LONGITUDE)));
            todo.setLatitude(getDouble(getColumnIndex(TodoEntry.COLUMN_NAME_LATITUDE)));

            return todo;
        }

    }

    private ContentValues createContentValuesInstance(Todo todo) {
        ContentValues values = new ContentValues();

        values.put(TodoContract.TodoEntry.COLUMN_NAME_DESCRIPTION, todo.getDescription());
        values.put(TodoContract.TodoEntry.COLUMN_NAME_IMAGE, todo.getImage());
        values.put(TodoContract.TodoEntry.COLUMN_NAME_LATITUDE, todo.getLatitude());
        values.put(TodoContract.TodoEntry.COLUMN_NAME_LONGITUDE, todo.getLongitude());

        return values;
    }
}
