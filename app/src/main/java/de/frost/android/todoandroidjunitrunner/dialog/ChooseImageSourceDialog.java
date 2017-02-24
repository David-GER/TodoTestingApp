package de.frost.android.todoandroidjunitrunner.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import de.frost.android.todoandroidjunitrunner.R;

/**
 * Created by david on 24.02.17.
 */


public class ChooseImageSourceDialog extends DialogFragment {
    private ChooseImageSourceDialogListener listener;

    public static final int IMAGE_SOURCE_INTERNET = 0;
    public static final int IMAGE_SOURCE_GALLERY = 1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_chose_image)
                .setItems(R.array.image_sources, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) listener.done(which);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setListener(ChooseImageSourceDialogListener listener) {
        this.listener = listener;
    }

    public interface ChooseImageSourceDialogListener {
        public void done(int source);
    }
}

