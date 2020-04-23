package io.github.longlinht.library.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import io.github.longlinht.library.android.AndroidUnit;

/**
 * Created by Tao He on 2018/9/13.
 * hetaoof@gmail.com
 */
public class RoundGLSurfaceView extends GLSurfaceView {

    private Path clipPath;

    public RoundGLSurfaceView(Context context) {
        super(context);
    }

    public RoundGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        clipPath = new Path();
        float radius = AndroidUnit.DP.toPx(133);

        float x = getLeft() + radius;
        float y = getTop() + radius;

        clipPath.addCircle(x, y, radius, Path.Direction.CCW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.clipPath(clipPath);
        super.dispatchDraw(canvas);
    }

}
