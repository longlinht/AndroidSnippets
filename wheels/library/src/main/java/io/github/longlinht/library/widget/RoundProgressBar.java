package io.github.longlinht.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;

import io.github.longlinht.library.R;

/**
 * Created by Tao He on 2020-04-17.
 * hetaoof@gmail.com
 */


/**
 * Use in xml
 *
 * <io.github.longlinht.library.widget.RoundProgressBar
 *         android:id="@+id/progressbar"
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent"
 *         app:roundColor="#00ff00"
 *         app:roundProgressColor="#80979797"
 *         app:roundWidth="2dp"
 *         app:style="STROKE"
 *         app:textIsDisplayable="false"
 *         />
 */

public class RoundProgressBar extends View {

    private static int PADDING = 15;

    private Paint paint;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int max;
    private int progress;
    private boolean textIsDisplayable;
    private int style = STROKE;
    public static final int STROKE = 0;
    public static final int FILL = 1;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.TRANSPARENT);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);
        mTypedArray.recycle();
    }

    public void setRoundColor(@ColorInt int roundColor) {
        this.roundColor = roundColor;
    }

    public void setRoundProgressColor(@ColorInt int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public void setTextIsDisplayable(boolean textIsDisplayable) {
        this.textIsDisplayable = textIsDisplayable;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        int centre = getWidth()/2;
        int radius = (int) (centre - roundWidth/2);
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);
        canvas.drawCircle(centre, centre, radius, paint);

        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent = (int)(((float)progress / (float)max) * 100);
        float textWidth = paint.measureText(percent + "%");

        if(textIsDisplayable && percent >= 0 && style == STROKE){
            canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize/2, paint);
        }

        /**
         * 画圆弧 ，画圆环的进度
         */

        //设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundProgressColor);


        switch (style) {
            case STROKE:{
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
                break;
            }
            case FILL:{
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if(progress !=0)
                    canvas.drawArc(oval, 0, 360 * progress / max, true, paint);
                break;
            }
        }
    }


    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if(max < 0){
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    public void setProgress(int progress) {
        if(progress < 0){
            progress = 0;
        }
        if(progress > max){
            progress = max;
        }
        if(progress <= max){
            this.progress = progress;
            postInvalidate();
        }

    }

    public int getProgress() {
        return progress;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
}

