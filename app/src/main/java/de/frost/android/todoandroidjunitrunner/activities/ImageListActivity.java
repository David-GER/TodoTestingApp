package de.frost.android.todoandroidjunitrunner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import de.frost.android.todoandroidjunitrunner.R;
import de.frost.android.todoandroidjunitrunner.adapters.ImageAdapter;
import de.frost.android.todoandroidjunitrunner.adapters.ImageAdapterListener;
import de.frost.android.todoandroidjunitrunner.connection.RestClient;
import de.frost.android.todoandroidjunitrunner.model.ImageModel;
import de.frost.android.todoandroidjunitrunner.model.PixiImageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by david on 24.02.17.
 */

public class ImageListActivity extends BaseActivity implements View.OnClickListener, TextWatcher, ImageAdapterListener {

    private static final String TAG = ImageListActivity.class.getSimpleName();
    public static final String EXTRA_URL = "EXTRA_URL";
    public static final String EXTRA_DESC = "EXTRA_DESC";
    private EditText et_search;
    private Button btn_search;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ProgressBar progressBar;

    @Inject
    public RestClient restClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        getApplicationComponent().inject(this);

        et_search = (EditText) findViewById(R.id.et_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        et_search.addTextChangedListener(this);
        btn_search.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ImageAdapter(this);
        recyclerView.setAdapter(adapter);

        if (getIntent().hasExtra(EXTRA_DESC)) {
            et_search.setText(getIntent().getStringExtra(EXTRA_DESC));
        }
    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

        this.restClient.loadImages(et_search.getText().toString(), new Callback<PixiImageResponse>() {
            @Override
            public void onResponse(Call<PixiImageResponse> call, Response<PixiImageResponse> response) {

                if (response.isSuccessful()) {
                    List<ImageModel> body = response.body().getList();

                    if (body.size() > 0) {
                        //Add Pixabay image added to the search result to show the user where the images came from.

                        body.add(new ImageModel(
                                "Pixabay",
                                "https://pixabay.com/static/img/logo_square.png",
                                "https://pixabay.com/static/img/logo_square.png")
                        );
                    }


                    adapter.setItems(body);
                } else {
                    Toast.makeText(ImageListActivity.this, "There has been an error. Code :" + response.code(), Toast.LENGTH_LONG)
                            .show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PixiImageResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });

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

    @Override
    public void onItemClicked(int pos) {
        if (this.adapter.getList().get(pos).getUrl().equals("https://pixabay.com/static/img/logo_square.png")) {
            Toast.makeText(this, "These images are provided by pixabay.com!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_URL, this.adapter.getList().get(pos).getUrl());
        setResult(RESULT_OK, data);

        finish();
    }
}
