package de.frost.android.todoandroidjunitrunner.model;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by david on 13.02.17.
 */
public class TodoManagerTest {

    /* TODO DAGGER
    @Test
    public void init() throws Exception {
        TodoDataSource todoDataSourceMock = mock(TodoDataSource.class);
        TodoManager.init(todoDataSourceMock);

        assertNotNull(TodoManager.getInstance());
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void getInstance_withoutInit() throws Exception {
        TodoManager.getInstance();
    }


    @Test
    public void query() throws Exception {
        TodoDataSource todoDataSourceMock = mock(TodoDataSource.class);
        TodoManager.init(todoDataSourceMock);

        Todo temp = new Todo("Test123");
        when(todoDataSourceMock.query(0)).thenReturn(temp);

        assertEquals(
                temp.getDescription(),
                TodoManager.getInstance().query(0).getDescription()
        );
    }

    @Test
    public void queryAll() throws Exception {
        TodoDataSource todoDataSourceMock = mock(TodoDataSource.class);
        TodoManager.init(todoDataSourceMock);

        TodoDbHelper.TodoCursor todoCursorMock = mock(TodoDbHelper.TodoCursor.class);

        when(todoDataSourceMock.queryAll()).thenReturn(todoCursorMock);

        assertEquals(
                todoCursorMock,
                TodoManager.getInstance().queryAll()
        );
    }

    @Test
    public void insert() throws Exception {
        TodoDataSource todoDataSourceMock = mock(TodoDataSource.class);
        TodoManager.init(todoDataSourceMock);

        Todo temp = new Todo("Test123");

        TodoManager.getInstance().insert(temp);

        verify(todoDataSourceMock, times(1)).insert(temp);
    }

    @Test
    public void removeAll() throws Exception {
        TodoDataSource todoDataSourceMock = mock(TodoDataSource.class);
        TodoManager.init(todoDataSourceMock);

        Todo temp = new Todo("Test123");

        TodoManager.getInstance().removeAll();

        verify(todoDataSourceMock, times(1)).removeAll();
    }



    @Test
    public void remove_id() throws Exception {
        TodoDataSource todoDataSourceMock = mock(TodoDataSource.class);
        TodoManager.init(todoDataSourceMock);


        TodoManager.getInstance().remove(1);

        verify(todoDataSourceMock, times(1)).remove(1);
    }

    @Test
    public void remove_obj() throws Exception {
        TodoDataSource todoDataSourceMock = mock(TodoDataSource.class);
        TodoManager.init(todoDataSourceMock);

        Todo temp = new Todo("Test123");

        TodoManager.getInstance().remove(temp);

        verify(todoDataSourceMock, times(1)).remove(temp);
    }*/

}