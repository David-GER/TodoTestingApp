package de.frost.android.todoandroidjunitrunner.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by david on 13.02.17.
 */
public class TodoManagerTest {
    @Test
    public void getSize() throws Exception {
        //test scenario
        TodoManager manager = new TodoManager();
        manager.put(new Todo("Test Todo"));

        //test evaluation
        assertEquals(
                1,                  //expected
                manager.getSize()   //actual value
        );
    }

    @Test
    public void clear() throws Exception {
        TodoManager todoMock = mock(TodoManager.class);
    }

    @Test
    public void put() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void remove1() throws Exception {

    }

    @Test
    public void listener() throws Exception {
        //test scenario
        TodoManagerListener listenerMock = mock(TodoManagerListener.class);

        TodoManager manager = new TodoManager();
        manager.setListener(listenerMock);

        Todo todo = new Todo("Example Todo");
        manager.put(todo);
        manager.remove(todo);

        //test evaluation

        verify(listenerMock).todoAdded(todo);
        verify(listenerMock).todoRemoved(todo);
    }
}