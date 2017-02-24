package de.frost.android.todoandroidjunitrunner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.frost.android.todoandroidjunitrunner.dialog.ChooseImageSourceDialog;
import de.frost.android.todoandroidjunitrunner.model.Todo;
import de.frost.android.todoandroidjunitrunner.model.TodoManager;

public class TodoActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, ChooseImageSourceDialog.ChooseImageSourceDialogListener {

    private static final String TAG = TodoActivity.class.getSimpleName();
    public static final String TODO_EXTRA = "TODO_EXTRA";
    private static final int REQUEST_IMAGE = 1002;
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1003;
    private Button btn_save;
    private EditText et_description;
    private ImageView choseImage;

    private Todo currentTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        btn_save = (Button) findViewById(R.id.btn_save);
        et_description = (EditText) findViewById(R.id.description);
        choseImage = (ImageView) findViewById(R.id.choseImage);

        btn_save.setEnabled(false);

        et_description.addTextChangedListener(this);

        btn_save.setOnClickListener(this);
        choseImage.setOnClickListener(this);

        currentTodo = new Todo();

        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (getCurrentFocus() != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_IMAGE:

                if (resultCode == RESULT_OK) {

                    final Uri imageUri = data.getData();

                    this.currentTodo.setImage(imageUri.toString());

                    Picasso.with(this)
                            .load(currentTodo.getImage())
                            .resize(100, 100)
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(choseImage);
                }

                break;
            default:
                throw new IllegalArgumentException("Request code is invalid! " +
                        requestCode);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                this.currentTodo.setDescription(et_description.getText().toString());

                TodoManager.getInstance().insert(currentTodo);

                setResult(RESULT_OK);
                finish();
                break;
            case R.id.choseImage:

                ChooseImageSourceDialog dialog = new ChooseImageSourceDialog();
                dialog.setListener(this);
                dialog.show(getFragmentManager(), "CHOOSE_IMAGE");

                break;
            default:
                throw new IllegalArgumentException("This should not happen! " + v.getId());
        }
    }

    private void getImageFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_IMAGE);
    }

    private boolean askUserToGetImage() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                Toast.makeText(this, "I need this", Toast.LENGTH_SHORT).show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_READ_EXTERNAL_STORAGE);
            }

            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    getImageFromGallery();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "That's too bad ...", Toast.LENGTH_SHORT).show();
                }
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
        btn_save.setEnabled(!TextUtils.isEmpty(et_description.getText()));
    }

    @Override
    public void done(int source) {
        switch (source) {
            case ChooseImageSourceDialog.IMAGE_SOURCE_INTERNET:
                break;
            case ChooseImageSourceDialog.IMAGE_SOURCE_GALLERY:
                if (askUserToGetImage()) {
                    getImageFromGallery();
                }
                break;
            default:
                throw new IllegalArgumentException("This source does not exist! " + source);
        }
    }
}
