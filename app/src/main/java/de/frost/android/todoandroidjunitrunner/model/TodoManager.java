package de.frost.android.todoandroidjunitrunner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 13.02.17.
 */

public class TodoManager {
    private List<Todo> list = new ArrayList<>();
    private TodoManagerListener listener;

    public int getSize() {
        return this.list.size();
    }

    public void clear() {
        this.list.clear();
    }

    public void put(Todo todo) {
        this.list.add(todo);

        if (this.listener != null) this.listener.todoAdded(todo);
    }

    public void remove(Todo todo) {
        this.list.remove(todo);
        if (this.listener != null) this.listener.todoRemoved(todo);
    }

    public void remove(int index) {
        Todo toBeRemoved = this.list.get(index);

        this.remove(toBeRemoved);
    }

    public void setListener(TodoManagerListener listener) {
        this.listener = listener;
    }
}
