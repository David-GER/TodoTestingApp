package de.frost.android.todoandroidjunitrunner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import javax.inject.Inject;

import de.frost.android.todoandroidjunitrunner.activities.BaseActivity;
import de.frost.android.todoandroidjunitrunner.activities.TodoActivity;
import de.frost.android.todoandroidjunitrunner.adapters.TodoAdapter;
import de.frost.android.todoandroidjunitrunner.model.TodoManager;

public class MainActivity extends BaseActivity {
    private static final int REQUEST_TODO = 1001;
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button addTodoButton;
    private ListView listView;
    private TodoAdapter adapter;
    private TextView empty;

    @Inject
    public TodoManager todoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getApplicationComponent().inject(this);

        addTodoButton = (Button) findViewById(R.id.addTodo);
        addTodoButton.setOnClickListener(v -> launchTodo());

        listView = (ListView) findViewById(R.id.list);

        adapter = new TodoAdapter(
                this,
                this.todoManager.queryAll()
        );

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
                    adapter.changeCursor(this.todoManager.queryAll());
                }

                break;
            default:
                throw new IllegalArgumentException("Request code is invalid! " + requestCode);
        }
    }
}
