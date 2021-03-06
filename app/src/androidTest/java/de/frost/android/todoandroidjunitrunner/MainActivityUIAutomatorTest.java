package de.frost.android.todoandroidjunitrunner;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import org.junit.Before;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import java.io.File;

/**
 * Created by david on 20.02.17.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class MainActivityUIAutomatorTest {
    private static final String BASIC_SAMPLE_PACKAGE
            = "de.frost.android.todoandroidjunitrunner";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator testen";
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void general() throws Exception {
        final String tempTodoDesc = "This is a automated todo!";

        //when starting the app the empty view will be shown.
        UiObject emptyView = mDevice.findObject(
                new UiSelector().resourceId("de.frost.android.todoandroidjunitrunner:id/empty")
                    .className("android.widget.TextView")
        );

        UiObject addButton = mDevice.findObject(
                new UiSelector().resourceId("de.frost.android.todoandroidjunitrunner:id/addTodo")
                    .className("android.widget.Button")
        );

        UiObject listView = mDevice.findObject(
                new UiSelector().resourceId("de.frost.android.todoandroidjunitrunner:id/list")
                        .className("android.widget.ListView")
        );

        assertTrue(emptyView.exists());
        assertEquals("There are\nno ToDos!", emptyView.getText());

        assertTrue(addButton.exists());
        assertTrue(addButton.isEnabled());

        assertFalse(listView.exists());

        addButton.click();

        //to the todo activity
        assertFalse(addButton.exists());

        UiObject saveButton = mDevice.findObject(
                new UiSelector().resourceId("de.frost.android.todoandroidjunitrunner:id/btn_save")
                        .className("android.widget.Button")
        );

        UiObject descriptionEditText = mDevice.findObject(
                new UiSelector().resourceId("de.frost.android.todoandroidjunitrunner:id/description")
                        .className("android.widget.EditText")
        );

        assertTrue(saveButton.exists());
        assertTrue(descriptionEditText.exists());
        assertFalse(saveButton.isEnabled());

        descriptionEditText.setText("abc");
        assertTrue(saveButton.isEnabled());

        descriptionEditText.clearTextField();
        assertFalse(saveButton.isEnabled());

        descriptionEditText.setText(tempTodoDesc);
        saveButton.click();

        //back to the main activity
        assertFalse(saveButton.exists());
        assertTrue(addButton.exists());


        assertFalse(emptyView.exists());


        assertTrue(listView.exists());
        assertTrue(listView.getChildCount() == 1);

        UiObject child = listView.getChild(
                new UiSelector().resourceId("de.frost.android.todoandroidjunitrunner:id/description").className("android.widget.TextView")
        );

        assertTrue(child.exists());
        assertEquals(tempTodoDesc, child.getText());

    }
}