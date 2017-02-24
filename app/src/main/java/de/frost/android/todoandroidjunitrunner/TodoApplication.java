package de.frost.android.todoandroidjunitrunner;

import android.app.Application;

import de.frost.android.todoandroidjunitrunner.dagger.AppModule;
import de.frost.android.todoandroidjunitrunner.dagger.DaggerNetComponent;
import de.frost.android.todoandroidjunitrunner.dagger.NetComponent;
import de.frost.android.todoandroidjunitrunner.dagger.NetModule;
import de.frost.android.todoandroidjunitrunner.model.TodoDbHelper;
import de.frost.android.todoandroidjunitrunner.model.TodoManager;

/**
 * Created by david on 23.02.17.
 */

public class TodoApplication extends Application {

    public static final String IMAGE_API_URL = "https://pixabay.com/";

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        TodoManager.init(
                new TodoDbHelper(this)
        );

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(IMAGE_API_URL))
                .build();

    }

    public NetComponent getNetComponent() {
        return this.netComponent;
    }
}
