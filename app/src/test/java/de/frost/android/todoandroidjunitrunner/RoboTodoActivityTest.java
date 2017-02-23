package de.frost.android.todoandroidjunitrunner;

import android.view.View;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by david on 23.02.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RoboTodoActivityTest {

    @Test
    public void onCreate() throws Exception {
        TodoActivity activity = Robolectric.setupActivity(TodoActivity.class);

        assertFalse(
                "The save button shouldn't be enabled in the beginning",
                activity.findViewById(R.id.btn_save).isEnabled()
        );
    }

    @Test
    public void enablingSaveButton() throws Exception {
        TodoActivity activity = Robolectric.setupActivity(TodoActivity.class);

        EditText description = (EditText) activity.findViewById(R.id.description);
        description.setText("This is a test!");

        View button = activity.findViewById(R.id.btn_save);
        assertTrue(
                "The save button should be enabled because there is text in the description tf",
                button.isEnabled()
        );

        description.setText("");

        assertFalse(
                "Now, after clearing the text the text field the button should be disabled again.",
                button.isEnabled()
        );
    }

    @Test
    public void finishActivityAfterNewTodo() throws Exception {
        TodoActivity activity = Robolectric.setupActivity(TodoActivity.class);

        EditText description = (EditText) activity.findViewById(R.id.description);
        description.setText("This is a test!");

        activity.findViewById(R.id.btn_save).performClick();

        assertTrue(activity.isFinishing());
    }
}