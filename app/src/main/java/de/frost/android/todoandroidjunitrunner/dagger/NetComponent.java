package de.frost.android.todoandroidjunitrunner.dagger;

import javax.inject.Singleton;

import dagger.Component;
import de.frost.android.todoandroidjunitrunner.MainActivity;
import de.frost.android.todoandroidjunitrunner.activities.ImageListActivity;
import de.frost.android.todoandroidjunitrunner.activities.TodoActivity;
import de.frost.android.todoandroidjunitrunner.connection.RestClient;

/**
 * Created by david on 24.02.17.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(ImageListActivity activity);
    void inject(MainActivity activity);
    void inject(TodoActivity activity);
    void inject(RestClient restClient);

}
