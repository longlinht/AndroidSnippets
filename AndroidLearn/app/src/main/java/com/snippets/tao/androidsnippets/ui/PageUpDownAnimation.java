package com.snippets.tao.androidsnippets.ui;

import android.support.annotation.NonNull;
import android.view.View;
import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.view.animation.LinearInterpolator;
import android.animation.PropertyValuesHolder;

public class PageUpDownAnimation {

    private static int pageUpDownDurition = 500;
    private static int pageUpBeforeDownEnd = 400;
    private static int belowViewStayInterval = 800;
    private static int pageUpPivotY = 100;
    private static int pageDownPivotY = 70;

    private static ObjectAnimator aboveViewPageDownAnim;
    private static ObjectAnimator belowViewPageUpAnim;
    private static ObjectAnimator aboveViewPageUpAnim;
    private static ObjectAnimator belowViewPageDownAnim;

    public static void startPageUpDownAnimation(@NonNull final View aboveView, @NonNull final View belowView) {

        aboveViewPageDownAnim = ObjectAnimator.ofPropertyValuesHolder(aboveView,
                PropertyValuesHolder.ofFloat(View.ROTATION_X, 0, 90));

        aboveViewPageDownAnim.setDuration(pageUpDownDurition);
        aboveViewPageDownAnim.setInterpolator(new LinearInterpolator());
        aboveViewPageDownAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                aboveView.setPivotY(pageDownPivotY);
                belowView.setVisibility(View.GONE);
                belowView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       belowViewPageUpAnim.start();
                   }
               }, pageUpBeforeDownEnd);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        belowViewPageUpAnim = ObjectAnimator.ofPropertyValuesHolder(belowView,
        PropertyValuesHolder.ofFloat(View.ROTATION_X, -90, 0));
        belowViewPageUpAnim.setDuration(pageUpDownDurition);
        belowViewPageUpAnim.setInterpolator(new LinearInterpolator());
        belowViewPageUpAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                belowView.setVisibility(View.VISIBLE);
                belowView.setPivotY(pageUpPivotY);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                belowView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       belowViewPageDownAnim.start();
                   }
                }, belowViewStayInterval);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        belowViewPageDownAnim = ObjectAnimator.ofPropertyValuesHolder(belowView,
                PropertyValuesHolder.ofFloat(View.ROTATION_X, 0, 90));

        belowViewPageDownAnim.setDuration(pageUpDownDurition);
        belowViewPageDownAnim.setInterpolator(new LinearInterpolator());
        belowViewPageDownAnim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {
                belowView.setPivotY(pageDownPivotY);
                aboveView.setVisibility(View.GONE);
                aboveView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       aboveViewPageUpAnim.start();
                   }
               }, pageUpBeforeDownEnd);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        aboveViewPageUpAnim = ObjectAnimator.ofPropertyValuesHolder(aboveView,
                PropertyValuesHolder.ofFloat(View.ROTATION_X, -90, 0));
        aboveViewPageUpAnim.setDuration(pageUpDownDurition);
        aboveViewPageUpAnim.setInterpolator(new LinearInterpolator());
        aboveViewPageUpAnim.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {
               aboveView.setVisibility(View.VISIBLE);
               aboveView.setPivotY(pageUpPivotY);
           }

           @Override
           public void onAnimationEnd(Animator animator) {
               aboveView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       aboveViewPageDownAnim.start();
                   }
               }, belowViewStayInterval);
           }

           @Override
           public void onAnimationCancel(Animator animator) {

           }

           @Override
           public void onAnimationRepeat(Animator animator) {

           }
       });

       aboveViewPageDownAnim.start();
    }
}

