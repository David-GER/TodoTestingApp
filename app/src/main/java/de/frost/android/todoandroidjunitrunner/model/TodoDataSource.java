package de.frost.android.todoandroidjunitrunner.model;

import de.frost.android.todoandroidjunitrunner.model.TodoDbHelper.TodoCursor;

/**
 * Created by david on 23.02.17.
 */

interface TodoDataSource {
    public long insert(Todo todo);
    public int update(Todo todo);
    public int remove(Todo todo);
    public int remove(long id);
    public int removeAll();
    public Todo query(long id);
    public TodoCursor queryAll();
}
