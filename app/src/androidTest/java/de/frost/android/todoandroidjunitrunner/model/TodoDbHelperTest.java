package de.frost.android.todoandroidjunitrunner.model;

import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by david on 23.02.17.
 */

@RunWith(AndroidJUnit4.class)
public class TodoDbHelperTest {

    private TodoDbHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new TodoDbHelper(
                InstrumentationRegistry.getTargetContext()
        );

        helper.removeAll();
    }

    @After
    public void tearDown() throws Exception {
        helper.close();

    }

    /*
    @Ignore
    @Test
    public void onCreate() throws Exception {
        //TODO how to test?
    }

    @Ignore
    @Test
    public void onUpgrade() throws Exception {
        //TODO how to test?
    }*/

    @Test
    public void insert() throws Exception {
        assertEquals(0, helper.queryAll().getCount());

        Todo toBeInserted = new Todo("inserted");

        helper.insert(toBeInserted);

        assertEquals(1, helper.queryAll().getCount());
    }

    @Test
    public void query() throws Exception {
        assertEquals(0, helper.queryAll().getCount());

        String description = "inserted" + System.currentTimeMillis();

        Todo toBeInserted = new Todo(description);

        long id = helper.insert(toBeInserted);

        assertEquals(
                description,
                helper.query(id).getDescription()
        );
    }

    @Test
    public void update() throws Exception {
        assertEquals(0, helper.queryAll().getCount());

        Todo toBeInserted = new Todo("bla bla");
        long id = helper.insert(toBeInserted);

        Todo toBeUpdated = helper.query(id);
        final String descriptionAfterUpdate = "updated" + System.currentTimeMillis();

        toBeUpdated.setDescription(descriptionAfterUpdate);

        int count = helper.update(toBeUpdated);

        assertEquals(
                "This update should have only effected 1 row instead of" + count,
                1,
                count
        );

        assertEquals(
                "An update should not have changed the ID",
                id,
                toBeUpdated.getId()
        );

        assertEquals(
                descriptionAfterUpdate,
                helper.query(toBeUpdated.getId()).getDescription()
        );
    }

    @Test
    public void remove_by_id() throws Exception {
        assertEquals(0, helper.queryAll().getCount());

        Todo toBeInserted1 = new Todo("toBeInserted1");
        Todo toBeInserted2 = new Todo("toBeInserted2");
        Todo toBeDeleted = new Todo("toBeDeleted description");

        helper.insert(toBeInserted1);
        final long id = helper.insert(toBeDeleted);
        helper.insert(toBeInserted2);

        assertEquals(
                3,
                helper.queryAll().getCount()
        );

        final int rowsRemoved = helper.remove(id);

        assertEquals(
                1,
                rowsRemoved
        );

        final int rowsRemovedAgain = helper.remove(id);

        assertEquals(
                "The entity with this id should have been deleted already, so there should be 0 removed rows instead of" + rowsRemovedAgain,
                0,
                rowsRemovedAgain
        );

        assertEquals(
                2,
                helper.queryAll().getCount()
        );
    }

    @Test
    public void remove_by_obj() throws Exception {
        assertEquals(0, helper.queryAll().getCount());

        Todo toBeInserted1 = new Todo("toBeInserted1");
        Todo toBeInserted2 = new Todo("toBeInserted2");
        Todo toBeInsertedAndLaterDeleted = new Todo("toBeInsertedAndLaterDeleted description");

        helper.insert(toBeInserted1);
        final long id = helper.insert(toBeInsertedAndLaterDeleted);
        helper.insert(toBeInserted2);

        assertEquals(
                3,
                helper.queryAll().getCount()
        );

        Todo toBeDeletedForReal = helper.query(id);

        final int rowsRemoved = helper.remove(toBeDeletedForReal);

        assertEquals(
                1,
                rowsRemoved
        );

        final int rowsRemovedAgain = helper.remove(toBeDeletedForReal);

        assertEquals(
                "The entity should have been deleted already, so there should be 0 removed rows instead of" + rowsRemovedAgain,
                0,
                rowsRemovedAgain
        );

        assertEquals(
                2,
                helper.queryAll().getCount()
        );
    }


    @Test
    public void queryAll() throws Exception {
        assertEquals(0, helper.queryAll().getCount());

        Todo toBeQueried1 = new Todo("toBeQueried1");
        Todo toBeQueried2 = new Todo("toBeQueried2");
        Todo toBeQueried3 = new Todo("toBeQueried3");

        helper.insert(toBeQueried1);
        helper.insert(toBeQueried2);
        helper.insert(toBeQueried3);

        TodoDbHelper.TodoCursor todoCursor = helper.queryAll();

        todoCursor.moveToFirst();

        Todo first = todoCursor.getTodo();
        todoCursor.moveToNext();
        Todo second = todoCursor.getTodo();
        todoCursor.moveToNext();
        Todo third = todoCursor.getTodo();

        assertEquals(
                toBeQueried1.getDescription(),
                first.getDescription()
        );

        assertEquals(
                toBeQueried2.getDescription(),
                second.getDescription()
        );

        assertEquals(
                toBeQueried3.getDescription(),
                third.getDescription()
        );
    }

    @Test
    public void removeAll() throws Exception {
        assertEquals(0, helper.queryAll().getCount());

        Todo toBeRemoved1 = new Todo("toBeRemoved1");
        Todo toBeRemoved2 = new Todo("toBeRemoved2");
        Todo toBeRemoved3 = new Todo("toBeRemoved3");

        helper.insert(toBeRemoved1);
        helper.insert(toBeRemoved2);
        helper.insert(toBeRemoved3);

        helper.removeAll();

        assertEquals(
                0,
                helper.queryAll().getCount()
        );

    }

}