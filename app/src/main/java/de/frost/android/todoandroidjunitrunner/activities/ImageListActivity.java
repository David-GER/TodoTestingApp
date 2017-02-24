package de.frost.android.todoandroidjunitrunner.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import de.frost.android.todoandroidjunitrunner.R;
import de.frost.android.todoandroidjunitrunner.adapters.ImageAdapter;

/**
 * Created by david on 24.02.17.
 */

public class ImageListActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText et_search;
    private Button btn_search;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        et_search = (EditText) findViewById(R.id.et_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        et_search.addTextChangedListener(this);
        btn_search.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new ImageAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
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
        btn_search.setEnabled(!TextUtils.isEmpty(et_search.getText()));
    }

}
