package com.snippets.tao.androidsnippets.source.audiovideo.chapter1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.snippets.tao.androidsnippets.R;

/**
 * Created by Tao He on 2019-10-23.
 * hetaoof@gmail.com
 */
public class DrawCustomView extends View {

    private Paint paint = new Paint();
    private Bitmap bitmap;

    public DrawCustomView(Context context) {
        super(context);
        init();
    }

    public DrawCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, paint);
        }
    }
}
