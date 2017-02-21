package de.frost.android.todoandroidjunitrunner;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.frost.android.todoandroidjunitrunner.model.Todo;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by david on 04.01.17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void startTodoActivity() throws Exception {
        Intents.init();

        onView(withId(R.id.addTodo)).perform(click());

        intended(hasComponent(TodoActivity.class.getName()));

        Intents.release();
    }

    @Test
    public void activityResult_isHandledProperly() throws Exception {
        Intents.init();
        final String testDescription = "Espresso test Todo description!";
        final Todo todo = new Todo(testDescription);

        Intent resultData = new Intent();
        resultData.putExtra(TodoActivity.TODO_EXTRA, todo);

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        intending(hasComponent(TodoActivity.class.getName())).respondWith(result);

        onView(withId(R.id.addTodo)).perform(click());

        onData(anything())
                .inAdapterView(withId(R.id.list))
                .onChildView(withId(R.id.description))
                .check(matches(withText(testDescription)));

        Intents.release();
    }
}