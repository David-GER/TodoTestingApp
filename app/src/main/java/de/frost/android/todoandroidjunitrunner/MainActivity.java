package de.frost.android.todoandroidjunitrunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.frost.android.todoandroidjunitrunner.adapters.TodoAdapter;
import de.frost.android.todoandroidjunitrunner.model.Todo;
import de.frost.android.todoandroidjunitrunner.model.TodoManager;
import de.frost.android.todoandroidjunitrunner.model.TodoManagerListener;

public class MainActivity extends AppCompatActivity implements TodoManagerListener {
    private static final int REQUEST_TODO = 1001;
    private static final String TAG = MainActivity.class.getSimpleName();

    private TodoManager manager;
    private Button addTodoButton;
    private ListView listView;
    private TodoAdapter adapter;
    private TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new TodoManager();
        manager.setListener(this);

        addTodoButton = (Button) findViewById(R.id.addTodo);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTodo();
            }
        });

        listView = (ListView) findViewById(R.id.list);

        adapter = new TodoAdapter(this);
        listView.setAdapter(adapter);

        empty = (TextView) findViewById(R.id.empty);
        listView.setEmptyView(empty);
    }

    private void launchTodo() {
        startActivityForResult(new Intent(MainActivity.this, TodoActivity.class), REQUEST_TODO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_TODO:

                if (resultCode == RESULT_OK) {
                    Todo todo = (Todo) data.getParcelableExtra(TodoActivity.TODO_EXTRA);
                    manager.put(todo);

                } else {
                    Toast.makeText(this, "Creating ToDo canceled!", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                throw new IllegalArgumentException("Request code is invalid! " + requestCode);
        }
    }

    @Override
    public void todoAdded(Todo todo) {
        this.adapter.add(todo);
    }

    @Override
    public void todoRemoved(Todo todo) {
        this.adapter.remove(todo);
    }

    @Override
    public boolean shouldTodoBeRemoved(Todo todo) {
        return false;
    }
}
