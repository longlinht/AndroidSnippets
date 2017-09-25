package com.snippets.tao.androidsnippets.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.snippets.tao.androidsnippets.R;

/**
 * Created by Tao He on 17/9/22.
 * hetaoof@gmail.com
 */

public class CustomView extends View {

    private int mDefaultSize;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.CustomView);
        mDefaultSize = a.getDimensionPixelSize(R.styleable.CustomView_default_size, 100);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getViewSize(widthMeasureSpec);
        int height = getViewSize(heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        int centerX = getLeft() + r;
        int centerY = getTop() + r;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, r, paint);
    }



    private int getViewSize(int measureSpec) {
        int mySize = mDefaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {
                mySize = mDefaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {
                mySize = size;
                break;
            }
        }
        return mySize;
    }

}
