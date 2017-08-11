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

    public static void startReverseRotateAnim(final TextView pageUpView, final TextView pageDownView) {
       rotateXAnim = ObjectAnimator.ofPropertyValuesHolder(pageUpView,
       PropertyValuesHolder.ofFloat(View.ROTATION_X, 0, 90));
       rotateXAnim.setDuration(500);
       rotateXAnim.setInterpolator(new LinearInterpolator());

       rotateXAnim.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {
               pageUpView.setPivotY(70);
               pageUpView.setText("分享有奖，快来分享把！");
               pageDownView.setVisibility(View.GONE);
               pageDownView.postDelayed(new Runnable() {
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

       rotateXReverseAnim = ObjectAnimator.ofPropertyValuesHolder(pageDownView,
       PropertyValuesHolder.ofFloat(View.ROTATION_X, -90, 0));
       rotateXReverseAnim.setDuration(500);
       rotateXReverseAnim.setInterpolator(new LinearInterpolator());
       rotateXReverseAnim.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {
               pageDownView.setVisibility(View.VISIBLE);
               pageDownView.setPivotY(100);
               pageDownView.setText("机会多多");
           }

           @Override
           public void onAnimationEnd(Animator animator) {
               pageDownView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       startSubtitleRotateAnim(pageDownView, pageUpView);
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


    public static void startSubtitleRotateAnim(final TextView pageUpView, final TextView pageDownView) {
       rotateXAnim = ObjectAnimator.ofPropertyValuesHolder(pageDownView,
       PropertyValuesHolder.ofFloat(View.ROTATION_X, 0, 90));
       rotateXAnim.setDuration(500);
       rotateXAnim.setInterpolator(new LinearInterpolator());

       rotateXAnim.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {
               pageDownView.setPivotY(70);
               pageDownView.setText("机会多多");
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
               pageUpView.setText("分享有奖，快来分享把！");
           }

           @Override
           public void onAnimationEnd(Animator animator) {
               pageDownView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       startReverseRotateAnim(pageDownView, pageUpView);
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








