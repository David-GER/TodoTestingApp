package de.frost.android.todoandroidjunitrunner;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Field;

import de.frost.android.todoandroidjunitrunner.model.Todo;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

/**
 * Created by david on 20.02.17.
 */
public class TodoActivityTest {

    @Rule
    public ActivityTestRule<TodoActivity> rule = new ActivityTestRule<>(TodoActivity.class);

    @Test
    public void onCreate() throws Exception {
        onView(withId(R.id.btn_save))
                .check(matches(not(isEnabled())));
    }

    @Test
    public void enableSaveButton() throws Exception {
        onView(withId(R.id.description))
                .perform(typeText("Testing with Espresso!"));

        onView(withId(R.id.btn_save))
                .check(matches(isEnabled()));

        onView(withId(R.id.description))
                .perform(clearText());

        onView(withId(R.id.btn_save)).check(matches(not(isEnabled())));
    }

    @Test
    public void creatingTodo() throws Exception {
        final String debugDesc = "this is a test 1234!";
        onView(withId(R.id.description)).perform(typeText(debugDesc));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.btn_save)).perform(click());

        Field codeField = Activity.class.getDeclaredField("mResultCode"); //NoSuchFieldException
        Field dataField = Activity.class.getDeclaredField("mResultData"); //NoSuchFieldException
        codeField.setAccessible(true);
        dataField.setAccessible(true);

        final int mResultCode = codeField.getInt(rule.getActivity());
        final Intent mResultData = (Intent) dataField.get(rule.getActivity());
        final Todo todo = (Todo) mResultData.getParcelableExtra(TodoActivity.TODO_EXTRA);

        assertTrue("The result code is not ok. ", mResultCode == Activity.RESULT_OK);
        assertEquals("Description is wrong!", debugDesc, todo.getDescription());

    }
}