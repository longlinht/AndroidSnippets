package com.snippets.tao.androidsnippets.ui;

import android.view.View;
import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.view.animation.LinearInterpolator;
import android.animation.PropertyValuesHolder;
import android.widget.TextView;

public class PageUpDownAnimation {

    private static ObjectAnimator rotateXAnim;
    private static ObjectAnimator rotateXReverseAnim;

    public static void startSubtitleRotateAnim(final TextView pageUpView, final TextView pageDownView) {
       rotateXAnim = ObjectAnimator.ofPropertyValuesHolder(pageDownView,
       PropertyValuesHolder.ofFloat(View.ROTATION_X, 0, 90));
       rotateXAnim.setDuration(500);
       rotateXAnim.setInterpolator(new LinearInterpolator());

       rotateXAnim.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {
               pageDownView.setPivotY(70);
               pageDownView.setText("llllllllllllccccccccc");
               pageUpView.setVisibility(View.GONE);
               pageUpView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       rotateXReverseAnim.start();
                   }
               }, 400);
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

       rotateXReverseAnim = ObjectAnimator.ofPropertyValuesHolder(pageUpView,
       PropertyValuesHolder.ofFloat(View.ROTATION_X, -90, 0));
       rotateXReverseAnim.setDuration(500);
       rotateXReverseAnim.setInterpolator(new LinearInterpolator());
       rotateXReverseAnim.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {
               pageUpView.setVisibility(View.VISIBLE);
               pageUpView.setPivotY(100);
               pageUpView.setText("wollfsafdafddafafa");
           }

           @Override
           public void onAnimationEnd(Animator animator) {
               pageDownView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       //startReverseRotateAnim();
                   }
               }, 800);
           }

           @Override
           public void onAnimationCancel(Animator animator) {

           }

           @Override
           public void onAnimationRepeat(Animator animator) {

           }
       });
       rotateXAnim.start();
   }
}








