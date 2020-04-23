package io.github.longlinht.library.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import io.github.longlinht.library.R;

/**
 * Created by Tao He on 18-7-5.
 * hetaoof@gmail.com
 */
abstract public class BaseDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawableResource(R.color.ht_color_trans);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }

    abstract public void bindViews(View view);

}
