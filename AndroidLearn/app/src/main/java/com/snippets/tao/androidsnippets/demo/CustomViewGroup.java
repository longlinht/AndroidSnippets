package com.snippets.tao.androidsnippets.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tao He on 17/9/25.
 * hetaoof@gmail.com
 */

public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();

        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                int height = getTotalHeight();
                int width = getMaxChildWidth();
                setMeasuredDimension(width, height);
            } else if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, getTotalHeight());
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxChildWidth(), heightSize);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();

        int curHeight = t;
        for (int i=0; i<count; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            child.layout(l, curHeight, l + width, curHeight + height);
            curHeight += height;
        }
    }

    private int getMaxChildWidth() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getMeasuredWidth() > maxWidth)
                maxWidth = childView.getMeasuredWidth();
        }
        return maxWidth;
    }

    private int getTotalHeight() {
        int childCount = getChildCount();
        int height = 0;
        for(int i=0; i<childCount; i++) {
            View childView = getChildAt(i);
            height += childView.getMeasuredHeight();
        }
        return height;
    }
}
