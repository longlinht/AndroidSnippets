package com.snippets.tao.androidsnippets.utils;

import android.animation.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Tao He on 16-7-13.
 * Email: hetaoof@gmail.com
 */
public class AnimationUtils {

    private static final String PROPERTY_SCALE_X = "scaleX";
    private static final String PROPERTY_SCALE_Y = "scaleY";
    private static final String PROPERTY_ALPHA = "scaleY";
    private static final float DEFAULT_FRACTION_TIME = 1.0f;
    private static final long DEFAULT_DURATION = 100;
    private static final float DEFAULT_ALPH_FROM = 1.0f;
    private static final float DEFAULT_ALPH_TO = 0.4f;
    private static final int DEFAULT_SAMPLE_POINT_COUNT = 300;

    public static void setScaleAnimator(final View view, final float scaleFrom, final float scaleTo) {
        setScaleAnimator(view, scaleFrom, scaleTo, DEFAULT_ALPH_FROM, DEFAULT_ALPH_TO);
    }

    public static void setScaleAnimator(final View view, final float scaleFrom, final float scaleTo,
                                        final float alphFrom, final float alphTo) {

        setScaleAnimator(view, scaleFrom, scaleTo, alphFrom, alphTo,
                DEFAULT_FRACTION_TIME, DEFAULT_DURATION);
    }


    public static void setScaleAnimator(final View view, final float scaleFrom, final float scaleTo,
                                        final float alphFrom, final float alphTo, final float fractionTime,
                                        final long duration) {
        Keyframe scaleFrameFrom = Keyframe.ofFloat(fractionTime, scaleFrom);
        Keyframe scaleFrameTo = Keyframe.ofFloat(fractionTime, scaleTo);

        Keyframe alphaFrameFrom = Keyframe.ofFloat(fractionTime, alphFrom);
        Keyframe alphaFrameTo = Keyframe.ofFloat(fractionTime, alphTo);

        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofKeyframe(PROPERTY_SCALE_X, scaleFrameFrom, scaleFrameTo);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofKeyframe(PROPERTY_SCALE_Y, scaleFrameFrom, scaleFrameTo);
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofKeyframe(PROPERTY_ALPHA, alphaFrameFrom, alphaFrameTo);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, scaleXHolder, scaleYHolder, alphaHolder);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(duration);

        final int touchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        view.setOnTouchListener(new ScaleAnimatorTouchListener(animator, touchSlop));
    }

    private static class  ScaleAnimatorTouchListener implements View.OnTouchListener {
        private ObjectAnimator animator;
        private int touchSlop;
        private boolean pressed = false;
        private boolean reversed = false;

        private ObjectAnimator getObjectAnimator() {
            return animator;
        }
        public ScaleAnimatorTouchListener(ObjectAnimator animator, int touchSlop) {
            this.animator = animator;
            this.touchSlop = touchSlop;
            this.animator.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator paramAnimator) {

                }

                @Override
                public void onAnimationRepeat(Animator paramAnimator) {

                }

                @Override
                public void onAnimationEnd(final Animator paramAnimator) {
                    if (!pressed && !reversed) {
                        reversed = true;
                        ThreadUtils.postOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getObjectAnimator().reverse();
                            }
                        });
                    } else {
                        reversed = false;
                    }
                }

                @Override
                public void onAnimationCancel(Animator paramAnimator) {

                }
            });
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    animator.start();
                    pressed = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (pressed) {
                        if (!animator.isRunning()) {
                            animator.reverse();
                            reversed = true;
                        }
                        pressed = false;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (pressed && !pointInView(v, event.getX(), event.getY(), touchSlop)) {
                        if (!animator.isRunning()) {
                            animator.reverse();
                            reversed = true;
                        }
                        pressed = false;
                    }
                    break;

                default:
                    break;
            }
            return false;
        }
    }


    private static boolean pointInView(View view, float localX, float localY, float slop) {
        return localX >= -slop
               && localY >= -slop
               && localX < ((view.getRight() - view.getLeft()) + slop)
               && localY < ((view.getBottom() - view.getTop()) + slop);
    }

    public static Animator setupBackgroundAnimator(final View view, final String color, final long duration,
                                                   final boolean autoStart) {

        return setupBackgroundAnimator(view, color, DEFAULT_SAMPLE_POINT_COUNT, duration, autoStart);
    }

    public static Animator setupBackgroundAnimator(final View view, final String color, final int samplePtCount,
                                                   final long duration, final boolean autoStart) {

        final BackAnimationDrawable d = new BackAnimationDrawable(color, samplePtCount);
        ApiCompatibilityUtils.setBackgroundForView(view, d);
        final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        view.setTag(view.getId(), animator);
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {

            @Override
            public void onViewDetachedFromWindow(View v) {
                animator.end();
                v.removeOnAttachStateChangeListener(this);
            }

            @Override
            public void onViewAttachedToWindow(View v) {
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                d.update(value);
            }
        });

        animator.setDuration(duration);
        if (autoStart) {
            animator.start();
        }
        return animator;
    }

    private static class RepeatAnimatorListener implements Animator.AnimatorListener {
        private int maxCount = 0;
        private int count = 0;
        private long delay = 0;

        public RepeatAnimatorListener(int repeatCount, long delay) {
            this.maxCount = repeatCount;
            this.delay = delay;
        }

        @Override
        public void onAnimationStart(Animator paramAnimator) {

        }

        @Override
        public void onAnimationEnd(Animator paramAnimator) {
            count++;
            if (count < maxCount) {
                paramAnimator.setStartDelay(delay);
                paramAnimator.start();
            }
        }

        @Override
        public void onAnimationCancel(Animator paramAnimator) {

        }

        @Override
        public void onAnimationRepeat(Animator paramAnimator) {

        }
    }



    private static class BackAnimationDrawable extends Drawable {

        private final BezierCurve mCurve;

        private Paint mPaint;
        private float mAnimaValue;
        private DecelerateInterpolator mInterpolator = new DecelerateInterpolator();
        private int mColor;

        public BackAnimationDrawable(final String colorVaule, final int samplePtCount) {
            if (!colorVaule.startsWith("#")) {
                throw new IllegalArgumentException("Illegal argment, color value must begin with '#'");
            }
            mColor = Color.parseColor(colorVaule);
            mPaint = new Paint();
            mPaint.setColor(mColor);

            float[] controls = new float[]{
                    0f, 0f,
                    .30f, 0.9f,
                    .33f, 1f,
                    .35f, 0.75f,
                    .95f, 0f,
                    1f, 0f
            };
            mCurve = new BezierCurve(controls, samplePtCount);
        }

        public void update(float value) {
            this.mAnimaValue = value;
            invalidateSelf();
        }

        @Override
        public void draw(Canvas canvas) {
            Rect r = getBounds();
            int width = r.width();
            int centerX = r.centerX();
            int centerY = r.centerY();
            int alpha;
            alpha = (int) (255 * 0.2f * mCurve.valueY(mAnimaValue));

            if (alpha < 0) {
                alpha = 0;
            }

            if (alpha > 255) {
                alpha = 255;
            }

            mPaint.setColor(mColor);
            mPaint.setAlpha(alpha);
            canvas.drawCircle(centerX, centerY,
                    width * (mInterpolator.getInterpolation(mAnimaValue)) / 2, mPaint);
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(ColorFilter cf) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    }
}
