package de.frost.android.todoandroidjunitrunner;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import de.frost.android.todoandroidjunitrunner.activities.TodoActivity;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by david on 23.02.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RoboMainActivityTest {

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void launchTodoActivity() throws Exception {

        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.addTodo).performClick();

        Intent expectedIntent = new Intent(activity, TodoActivity.class);

        assertEquals(
                expectedIntent.toString(),
                shadowOf(activity).getNextStartedActivity().toString()
        );

    }
}