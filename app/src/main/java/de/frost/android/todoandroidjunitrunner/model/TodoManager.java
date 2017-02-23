package de.frost.android.todoandroidjunitrunner.model;

/**
 * Created by david on 13.02.17.
 */

public class TodoManager {
    private TodoDataSource source;
    private static TodoManager manager;

    private TodoManager(TodoDataSource source) {
        this.source = source;
    }

    public static void init(TodoDataSource dataSource) {
        manager = new TodoManager(dataSource);
    }

    public static TodoManager getInstance() {
        if (manager == null)
            throw new IllegalStateException("This Manager has never been initialized!");

        return manager;
    }

    public Todo query(long id) {
        return this.source.query(id);
    }

    public TodoDbHelper.TodoCursor queryAll() {
        return this.source.queryAll();
    }

    public void insert(Todo todo) {
        this.source.insert(todo);
    }

    public void remove(Todo todo) {
        this.source.remove(todo);
    }

    public void remove(long index) {
        this.source.remove(index);
    }

    public int removeAll() {
        return this.source.removeAll();
    }
}
