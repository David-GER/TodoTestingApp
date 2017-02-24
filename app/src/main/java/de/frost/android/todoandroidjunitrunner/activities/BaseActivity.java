package de.frost.android.todoandroidjunitrunner.activities;

import android.support.v7.app.AppCompatActivity;

import de.frost.android.todoandroidjunitrunner.TodoApplication;
import de.frost.android.todoandroidjunitrunner.dagger.NetComponent;

/**
 * Created by david on 24.02.17.
 */

public class BaseActivity extends AppCompatActivity {

    protected NetComponent getApplicationComponent() {
        return ((TodoApplication)getApplication()).getNetComponent();
    }
}
