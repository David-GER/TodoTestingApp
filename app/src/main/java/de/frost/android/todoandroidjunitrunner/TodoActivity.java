package de.frost.android.todoandroidjunitrunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.frost.android.todoandroidjunitrunner.model.Todo;
import de.frost.android.todoandroidjunitrunner.model.TodoManager;

public class TodoActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    public static final String TODO_EXTRA = "TODO_EXTRA";
    private Button btn_save;
    private EditText et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        btn_save = (Button) findViewById(R.id.btn_save);
        et_description = (EditText) findViewById(R.id.description);

        btn_save.setEnabled(false);

        et_description.addTextChangedListener(this);

        btn_save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Todo todo = new Todo(et_description.getText().toString());

        Intent intent = new Intent();
        intent.putExtra(TODO_EXTRA, todo);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Not needed
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Not needed
    }

    @Override
    public void afterTextChanged(Editable s) {
        btn_save.setEnabled(!TextUtils.isEmpty(et_description.getText()));
    }
}
