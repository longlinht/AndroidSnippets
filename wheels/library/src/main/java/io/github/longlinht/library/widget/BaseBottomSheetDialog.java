package io.github.longlinht.library.widget;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import io.github.longlinht.library.R;

/**
 * Created by Tao He on 18-6-21.
 * hetaoof@gmail.com
 */
public abstract class BaseBottomSheetDialog extends BottomSheetDialog {

    public BaseBottomSheetDialog(@NonNull Context context) {
        super(context, R.style.TaoBottomSheetDialog);
        initView();
    }

    public abstract void initView();

    public void transBackground(Context context) {
        getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundColor(context.getResources().getColor(R.color.ht_color_trans));
    }
}
