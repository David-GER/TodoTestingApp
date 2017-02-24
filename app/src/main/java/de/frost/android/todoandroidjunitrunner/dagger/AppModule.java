package de.frost.android.todoandroidjunitrunner.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.frost.android.todoandroidjunitrunner.model.TodoDataSource;
import de.frost.android.todoandroidjunitrunner.model.TodoDbHelper;
import de.frost.android.todoandroidjunitrunner.model.TodoManager;

/**
 * Created by david on 24.02.17.
 */

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    TodoManager providesTodoManager(TodoDataSource dataSource) {
        return new TodoManager(dataSource);
    }

    @Provides
    @Singleton
    TodoDataSource providesTodoDataSource(Application application) {
        return new TodoDbHelper(application);
    }
}
