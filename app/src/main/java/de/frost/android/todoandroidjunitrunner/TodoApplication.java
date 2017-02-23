package de.frost.android.todoandroidjunitrunner;

import android.app.Application;

import de.frost.android.todoandroidjunitrunner.model.TodoDbHelper;
import de.frost.android.todoandroidjunitrunner.model.TodoManager;

/**
 * Created by david on 23.02.17.
 */

public class TodoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TodoManager.init(
                new TodoDbHelper(this)
        );
    }
}
