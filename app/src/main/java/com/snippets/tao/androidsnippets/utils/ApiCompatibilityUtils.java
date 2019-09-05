package com.snippets.tao.androidsnippets.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Tao He on 16-7-13.
 * Email: hetaoof@gmail.com
 */
public class ApiCompatibilityUtils {

    private ApiCompatibilityUtils() {

    }

    /**
     * @see android.view.ViewGroup.MarginLayoutParams#setMarginEnd(int)
     */
    public static void setMarginEnd(@NonNull ViewGroup.MarginLayoutParams layoutParams, int end) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            layoutParams.setMarginEnd(end);
        } else {
            layoutParams.rightMargin = end;
        }
    }

    /**
     * @see android.view.ViewGroup.MarginLayoutParams#getMarginEnd()
     */
    public static int getMarginEnd(@NonNull ViewGroup.MarginLayoutParams layoutParams) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            return layoutParams.getMarginEnd();
        } else {
            return layoutParams.rightMargin;
        }
    }

    /**
     * @see android.view.ViewGroup.MarginLayoutParams#setMarginStart(int)
     */
    public static void setMarginStart(@NonNull ViewGroup.MarginLayoutParams layoutParams, int start) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            layoutParams.setMarginStart(start);
        } else {
           layoutParams.leftMargin = start;
        }
    }

    /**
     * @see android.view.ViewGroup.MarginLayoutParams#getMarginStart()
     */
    public static int getMarginStart(@NonNull ViewGroup.MarginLayoutParams layoutParams) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            return layoutParams.getMarginStart();
        } else {
            return layoutParams.leftMargin;
        }
    }

    /**
     * @see android.view.View#postInvalidateOnAnimation()
     */
    public static void postInvalidateOnAnimation(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    // These methods have a new name, and the old name is deprecated.

    /**
     * @see android.view.View#setBackground(Drawable)
     */
    @SuppressWarnings("deprecation")
    public static void setBackgroundForView(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /**
     * @see android.view.ViewTreeObserver#removeOnGlobalLayoutListener()
     */
    @SuppressWarnings("deprecation")
    public static void removeOnGlobalLayoutListener(
            @NonNull View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } else {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }

    /**
     * @see android.app.Notification.Builder#build()
     */
    @Nullable
    @SuppressWarnings("deprecation")
    public static Notification buildNotification(@Nullable Notification.Builder builder) {
        if(null == builder){
            return null;
        }
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                return builder.build();
            } else {
                return builder.getNotification();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public static void setProperty(@NonNull WebSettings settings, String property, String value) {
        if (Build.VERSION.SDK_INT >= AndroidBuild.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && Build.VERSION.SDK_INT < AndroidBuild.VERSION_CODES.KITKAT) {
            Method m = getMethodNoException(settings.getClass(), "setProperty",
                    String.class, String.class);
            if (m != null) {
                invokeVoidMethod(settings, m, property, value);
            }
        }
    }

    public static void webviewSetWebContentsDebuggingEnabled(boolean enable) {
        // invoke WebView.setWebContentsDebuggingEnabled(true);
        if (Build.VERSION.SDK_INT >= AndroidBuild.VERSION_CODES.KITKAT) {
            Method m = getMethodNoException(WebView.class, "setWebContentsDebuggingEnabled", boolean.class);
            if (m != null) {
                invokeVoidMethod(null, m, enable);
            }
        }
    }

    private static void invokeVoidMethod(Object object, Method method, Object... params) {
        try {
            method.invoke(object, params);
        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
    }

    private static Method getMethodNoException(Class<?> clazz, @NonNull String name,
                                               Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public static void viewTreeObserverRemoveGlobalOnLayoutListener(@NonNull ViewTreeObserver viewTreeObserver,
                                                                    ViewTreeObserver.OnGlobalLayoutListener victim) {
        if (Build.VERSION.SDK_INT >= AndroidBuild.VERSION_CODES.JELLY_BEAN)
            viewTreeObserver.removeOnGlobalLayoutListener(victim);
        else
            viewTreeObserver.removeGlobalOnLayoutListener(victim);
    }

    public static void webViewSetAllowUniversalAccessFromFileURLs(@NonNull WebSettings webSettings, boolean value) {
        if (Build.VERSION.SDK_INT >= AndroidBuild.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowUniversalAccessFromFileURLs(value);
        }
    }


    /**
     * Returns true if view's layout direction is right-to-left.
     *
     * @param view the View whose layout is being considered
     */
    public static boolean isLayoutRtl(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return view.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        } else {
            // All layouts are LTR before JB MR1.
            return false;
        }
    }
    /**
     * @see Configuration#getLayoutDirection()
     */
    public static int getLayoutDirection(@NonNull Configuration configuration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return configuration.getLayoutDirection();
        } else {
            // All layouts are LTR before JB MR1.
            return View.LAYOUT_DIRECTION_LTR;
        }
    }
    /**
     * @return True if the running version of the Android supports printing.
     */
    public static boolean isPrintingSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
    /**
     * @return True if the running version of the Android supports elevation. Elevation of a view
     * determines the visual appearance of its shadow.
     */
    public static boolean isElevationSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
    /**
     * @see android.view.View#setLayoutDirection(int)
     */
    public static void setLayoutDirection(@NonNull View view, int layoutDirection) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setLayoutDirection(layoutDirection);
        } else {
            // Do nothing. RTL layouts aren't supported before JB MR1.
        }
    }
    /**
     * @see android.view.View#setTextAlignment(int)
     */
    public static void setTextAlignment(@NonNull View view, int textAlignment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setTextAlignment(textAlignment);
        } else {
            // Do nothing. RTL text isn't supported before JB MR1.
        }
    }
    /**
     * @see android.view.View#setTextDirection(int)
     */
    public static void setTextDirection(@NonNull View view, int textDirection) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setTextDirection(textDirection);
        } else {
            // Do nothing. RTL text isn't supported before JB MR1.
        }
    }

    /**
     * @see android.view.View#setPaddingRelative(int, int, int, int)
     */
    public static void setPaddingRelative(@NonNull View view, int start, int top, int end, int bottom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setPaddingRelative(start, top, end, bottom);
        } else {
            // Before JB MR1, all layouts are left-to-right, so start == left, etc.
            view.setPadding(start, top, end, bottom);
        }
    }
    /**
     * @see android.view.View#getPaddingStart()
     */
    public static int getPaddingStart(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return view.getPaddingStart();
        } else {
            // Before JB MR1, all layouts are left-to-right, so start == left.
            return view.getPaddingLeft();
        }
    }
    /**
     * @see android.view.View#getPaddingEnd()
     */
    public static int getPaddingEnd(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return view.getPaddingEnd();
        } else {
            // Before JB MR1, all layouts are left-to-right, so end == right.
            return view.getPaddingRight();
        }
    }
    /**
     * @see android.widget.TextView#setCompoundDrawablesRelative(Drawable, Drawable, Drawable,
     *      Drawable)
     */
    public static void setCompoundDrawablesRelative(@NonNull TextView textView, Drawable start, Drawable top,
                                                    Drawable end, Drawable bottom) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // On JB MR1, due to a platform bug, setCompoundDrawablesRelative() is a no-op if the
            // view has ever been measured. As a workaround, use setCompoundDrawables() directly.
            // See: http://crbug.com/368196 and http://crbug.com/361709
            boolean isRtl = isLayoutRtl(textView);
            textView.setCompoundDrawables(isRtl ? end : start, top, isRtl ? start : end, bottom);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setCompoundDrawablesRelative(start, top, end, bottom);
        } else {
            textView.setCompoundDrawables(start, top, end, bottom);
        }
    }
    /**
     * @see android.widget.TextView#setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable,
     *      Drawable, Drawable, Drawable)
     */
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView,
                                                                       Drawable start, Drawable top, Drawable end, Drawable bottom) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Work around the platform bug described in setCompoundDrawablesRelative() above.
            boolean isRtl = isLayoutRtl(textView);
            textView.setCompoundDrawablesWithIntrinsicBounds(isRtl ? end : start, top,
                    isRtl ? start : end, bottom);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        }
    }
    /**
     * @see android.widget.TextView#setCompoundDrawablesRelativeWithIntrinsicBounds(int, int, int,
     *      int)
     */
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView,
                                                                       int start, int top, int end, int bottom) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Work around the platform bug described in setCompoundDrawablesRelative() above.
            boolean isRtl = isLayoutRtl(textView);
            textView.setCompoundDrawablesWithIntrinsicBounds(isRtl ? end : start, top,
                    isRtl ? start : end, bottom);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        }
    }
    // These methods have a new name, and the old name is deprecated.
    /**
     * @see android.app.PendingIntent#getCreatorPackage()
     */
    @Nullable
    @SuppressWarnings("deprecation")
    public static String getCreatorPackage(@NonNull PendingIntent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return intent.getCreatorPackage();
        } else {
            return intent.getTargetPackage();
        }
    }
    /**
     * @see android.provider.Settings.Global#DEVICE_PROVISIONED
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isDeviceProvisioned(@Nullable Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) return true;
        if (context == null) return true;
        if (context.getContentResolver() == null) return true;
        return Settings.Global.getInt(
                context.getContentResolver(), Settings.Global.DEVICE_PROVISIONED, 0) != 0;
    }
    /**
     * @see android.app.Activity#finishAndRemoveTask()
     */
    public static void finishAndRemoveTask(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            activity.finishAndRemoveTask();
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            // crbug.com/395772 : Fallback for Activity.finishAndRemoveTask() failing.
            new FinishAndRemoveTaskWithRetry(activity).run();
        } else {
            activity.finish();
        }
    }
    /**
     * Set elevation if supported.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean setElevation(@NonNull View view, float elevationValue) {
        if (!isElevationSupported()) return false;
        view.setElevation(elevationValue);
        return true;
    }

    private static class FinishAndRemoveTaskWithRetry implements Runnable {
        private static final long RETRY_DELAY_MS = 500;
        private static final long MAX_TRY_COUNT = 3;
        private final Activity mActivity;
        private int mTryCount;
        FinishAndRemoveTaskWithRetry(Activity activity) {
            mActivity = activity;
        }
        @Override
        public void run() {
            mActivity.finishAndRemoveTask();
            mTryCount++;
            if (!mActivity.isFinishing()) {
                if (mTryCount < MAX_TRY_COUNT) {
                    ThreadUtils.postOnUiThreadDelayed(this, RETRY_DELAY_MS);
                } else {
                    mActivity.finish();
                }
            }
        }
    }
    /**
     * @return Whether the screen of the device is interactive.
     */
    @SuppressWarnings("deprecation")
    public static boolean isInteractive(Context context) {
        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            return manager.isInteractive();
        } else {
            return manager.isScreenOn();
        }
    }
    @SuppressWarnings("deprecation")
    public static int getActivityNewDocumentFlag() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            return Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
    }
    /**
     * @see android.provider.Settings.Secure#SKIP_FIRST_USE_HINTS
     */
    public static boolean shouldSkipFirstUseHints(ContentResolver contentResolver) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Settings.Secure.getInt(
                    contentResolver, Settings.Secure.SKIP_FIRST_USE_HINTS, 0) != 0;
        } else {
            return false;
        }
    }
    /**
     * @param activity Activity that should get the task description update.
     * @param title Title of the activity.
     * @param icon Icon of the activity.
     * @param color Color of the activity. It must be a fully opaque color.
     */
    public static void setTaskDescription(@NonNull Activity activity, String title, Bitmap icon, int color) {
        // TaskDescription requires an opaque color.
        assert Color.alpha(color) == 255;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager.TaskDescription description =
                    new ActivityManager.TaskDescription(title, icon, color);
            activity.setTaskDescription(description);
        }
    }
    /**
     * @see android.view.Window#setStatusBarColor(int color).
     */
    public static void setStatusBarColor(@NonNull Window window, int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // If both system bars are black, we can remove these from our layout,
            // removing or shrinking the SurfaceFlinger overlay required for our views.
            if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            window.setStatusBarColor(statusBarColor);
        }
    }
    /**
     * @see android.content.res.Resources#getDrawable(int id).
     */
    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(@NonNull Resources res, int id) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return res.getDrawable(id, null);
        } else {
            return res.getDrawable(id);
        }
    }
    /**
     * @see android.content.res.Resources#getDrawableForDensity(int id, int density).
     */
    @Nullable
    @SuppressWarnings("deprecation")
    public static Drawable getDrawableForDensity(@NonNull Resources res, int id, int density) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return res.getDrawableForDensity(id, density, null);
        } else {
            return res.getDrawableForDensity(id, density);
        }
    }
    /**
     * @see android.app.Activity#finishAfterTransition().
     */
    public static void finishAfterTransition(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }
    /**
     * @see android.content.pm.PackageManager#getUserBadgedIcon(Drawable, android.os.UserHandle).
     */
    public static Drawable getUserBadgedIcon(Context context, int id) {
        Drawable drawable = getDrawable(context.getResources(), id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PackageManager packageManager = context.getPackageManager();
            drawable = packageManager.getUserBadgedIcon(drawable, Process.myUserHandle());
        }
        return drawable;
    }
    /**
     * @see android.content.pm.PackageManager#getUserBadgedDrawableForDensity(Drawable drawable,
     * UserHandle user, Rect badgeLocation, int badgeDensity).
     */
    public static Drawable getUserBadgedDrawableForDensity(
            @NonNull Context context, Drawable drawable, Rect badgeLocation, int density) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getUserBadgedDrawableForDensity(
                    drawable, Process.myUserHandle(), badgeLocation, density);
        }
        return drawable;
    }
    /**
     * @see android.content.res.Resources#getColor(int id).
     */
    @SuppressWarnings("deprecation")
    public static int getColor(@NonNull Resources res, int id) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return res.getColor(id, null);
        } else {
            return res.getColor(id);
        }
    }
    /**
     * @see android.graphics.drawable.Drawable#getColorFilter().
     */
    @SuppressWarnings("NewApi")
    public static ColorFilter getColorFilter(@NonNull Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return drawable.getColorFilter();
        } else {
            return null;
        }
    }
    /**
     * @see android.content.res.Resources#getColorStateList(int id).
     */
    @SuppressWarnings("deprecation")
    public static ColorStateList getColorStateList(@NonNull Resources res, int id) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return res.getColorStateList(id, null);
        } else {
            return res.getColorStateList(id);
        }
    }
    /**
     * @see android.widget.TextView#setTextAppearance(int id).
     */
    @SuppressWarnings("deprecation")
    public static void setTextAppearance(@NonNull TextView view, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setTextAppearance(id);
        } else {
            view.setTextAppearance(view.getContext(), id);
        }
    }
    /**
     * See {@link android.os.StatFs#getBlockCount()}.
     */
    @SuppressWarnings("deprecation")
    public static long getBlockCount(@NonNull StatFs statFs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return statFs.getBlockCountLong();
        } else {
            return statFs.getBlockCount();
        }
    }
    /**
     * See {@link android.os.StatFs#getBlockSize()}.
     */
    @SuppressWarnings("deprecation")
    public static long getBlockSize(@NonNull StatFs statFs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return statFs.getBlockSizeLong();
        } else {
            return statFs.getBlockSize();
        }
    }
}
