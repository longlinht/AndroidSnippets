package io.github.longlinht.library.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import io.github.longlinht.library.R;


/**
 * Created by Tao He on 2020-04-17.
 * hetaoof@gmail.com
 *
 */


public class CountDownView extends FrameLayout {
    private int millisInFuture;
    private TextView textView;
    private OnCompletedListener onCompletedListener;
    private AnimatorSet animatorSet2;
    private View coverView;
    private ObjectAnimator coverAlpha;
    private boolean starting;
    private ValueAnimator countDownAnimator;
    private boolean showGo;

    public CountDownView(Context context) {
        super(context);
        initView(context);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_count_down, this, true);
        textView = rootView.findViewById(R.id.textView);
        coverView = rootView.findViewById(R.id.cover_view);
        initAnimation();

    }

    private void initAnimation() {
        countDownAnimator = ValueAnimator.ofFloat(0f, 1.0f);
        countDownAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float percent = (float) animation.getAnimatedValue();
                textView.setScaleX(1 - percent);
                textView.setScaleY(1 - percent);
                if (percent < 0.2f) {
                    textView.setAlpha(percent * 5);
                } else {
                    textView.setAlpha(1.0f);
                }

            }
        });
        countDownAnimator.setDuration(1000);
        countDownAnimator.setInterpolator(new BezierInterpolator(0.52f, 0.00f, 0.74f, 0.00f));
        countDownAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (showGo) {
                    textView.setText("GO");
                    animatorSet2.start();
                } else {
                    if (!starting) {
                        return;
                    }
                    onCompletedListener.onCompleted();
                    setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                textView.setText(String.valueOf(--millisInFuture));
            }
        });

        coverAlpha = ObjectAnimator.ofFloat(coverView, "alpha", 0.0f, 1.0f);
        coverAlpha.setDuration(200);

        ValueAnimator animator1 = ValueAnimator.ofFloat(0f, 1.0f);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float percent = (float) animation.getAnimatedValue();
                textView.setScaleX(percent);
                textView.setScaleY(percent);
            }
        });
        animator1.setDuration(800);

        ValueAnimator animator2 = ValueAnimator.ofFloat(0f, 1.0f);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float percent = (float) animation.getAnimatedValue();
                textView.setScaleX(1 + 0.8f * percent);
                textView.setScaleY(1 + 0.8f * percent);
                textView.setAlpha(1 - percent);
            }
        });
        animator2.setDuration(200);

        animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(animator1, animator2);
        animatorSet2.setInterpolator(new BezierInterpolator(0.32f, 0.94f, 0.60f, 1.00f));
        animatorSet2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!starting) {
                    return;
                }
                onCompletedListener.onCompleted();
                setVisibility(GONE);
            }
        });
    }

    public void start(int millisInFuture, boolean showGo, OnCompletedListener onCompletedListener) {
        this.millisInFuture = millisInFuture;
        this.showGo = showGo;
        starting = true;
        this.onCompletedListener = onCompletedListener;
        setVisibility(VISIBLE);
        textView.setText(String.valueOf(millisInFuture));
        countDownAnimator.setRepeatCount(millisInFuture-1);
        countDownAnimator.start();
        coverAlpha.start();
    }

    public void stop() {
        starting = false;
        if (animatorSet2.isStarted()) {
            animatorSet2.cancel();
        }
    }

    public interface OnCompletedListener {
        void onCompleted();
    }

}
