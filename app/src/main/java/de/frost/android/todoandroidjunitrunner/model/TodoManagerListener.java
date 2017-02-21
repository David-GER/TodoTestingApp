package de.frost.android.todoandroidjunitrunner.model;

/**
 * Created by david on 20.02.17.
 */

public interface TodoManagerListener {
    public void todoAdded(Todo todo);
    public void todoRemoved(Todo todo);
    public boolean shouldTodoBeRemoved (Todo todo);
}
