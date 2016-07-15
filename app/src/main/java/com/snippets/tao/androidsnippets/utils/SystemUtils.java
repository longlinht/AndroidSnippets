package com.snippets.tao.androidsnippets.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Tao He on 16-7-15.
 * Email: hetaoof@gmail.com
 */
public class SystemUtils {

    private static final String SCHEME = "package";
    private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
    private static final String APP_PKG_NAME_22 = "pkg";
    private static final String ACTION_APPLICATION_DETAILS_SETTINGS_23 = "android.settings.APPLICATION_DETAILS_SETTINGS";
    private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
    private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";

    private static String TAG = SystemUtils.class.getSimpleName() ;
    private static boolean mInitialized = false;
    private static int SCREEN_WIDTH = 480;
    private static int SCREEN_HEIGHT = 800;
	private static int SMALLEST_SCREEN_WIDTHWDP = 320;
	private static float FONT_SCALE = 1.0F;
    private static int sNavigationBarHeight = Integer.MIN_VALUE;

    private static void ensureInitialized(Context context) {
        try{
            if (mInitialized)
                return;
            mInitialized = true;

            int width = context.getResources().getDisplayMetrics().widthPixels;
            int height = context.getResources().getDisplayMetrics().heightPixels;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                SCREEN_WIDTH = height;
                SCREEN_HEIGHT = width;

            } else {
                SCREEN_WIDTH = width;
                SCREEN_HEIGHT = height;
            }
            SMALLEST_SCREEN_WIDTHWDP = context.getResources().getConfiguration().smallestScreenWidthDp;
            FONT_SCALE = context.getResources().getConfiguration().fontScale;
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static int getScreenWidth(Context context) {
        ensureInitialized(context);
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight(Context context) {
        ensureInitialized(context);
        return SCREEN_HEIGHT;
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static void setTextToClipBoard(String text, Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(text);
    }

    public static ArrayList<ResolveInfo> hasDefaultBrowser(Context context) {
        ArrayList<ResolveInfo> browserList = new ArrayList<ResolveInfo>();
        PackageManager packageManager = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("www.google.com"));

        List<ResolveInfo> activities = null;
        try {
            activities = packageManager.queryIntentActivities(intent, 0);
        } catch (Exception e) {

        }

        if (activities != null) {
            List<ComponentName> componentNameList = new ArrayList<ComponentName>();
            List<IntentFilter> filterList = new ArrayList<IntentFilter>();

            Iterator<ResolveInfo> activityIterator = activities.iterator();
            while (activityIterator.hasNext()) {

                ResolveInfo resolveInfo = activityIterator.next();
                packageManager.getPreferredActivities(filterList, componentNameList,
                        resolveInfo.activityInfo.packageName);

                Iterator<IntentFilter> filterIterator = filterList.iterator();
                while (filterIterator.hasNext()) {
                    IntentFilter fil = filterIterator.next();
                    if ((fil.hasCategory(Intent.CATEGORY_BROWSABLE)
                            || fil.hasCategory(Intent.CATEGORY_DEFAULT))
                            && fil.hasDataScheme("http")) {
                        browserList.add(resolveInfo);
                    }
                }
            }
        }

        return browserList;
    }



    public static Intent getPackageDetailsIntent(String packageName) {
        Intent intent = new Intent();
        int apiLevel = 0;
        try {
            apiLevel = VERSION.SDK_INT;
        } catch (Exception ex) {
        }
        if (apiLevel >= 9) {
            intent.setAction(ACTION_APPLICATION_DETAILS_SETTINGS_23);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
        } else {
            final String appPkgName = (apiLevel > 7 ? APP_PKG_NAME_22 : APP_PKG_NAME_21);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(APP_DETAILS_PACKAGE_NAME, APP_DETAILS_CLASS_NAME);
            intent.putExtra(appPkgName, packageName);
        }
        return intent;
    }


    public static void clearAllNotification(Context context) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

    public static boolean killProcess(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);

            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                return false;
            }

            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(packageName);

            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isLandscape(Context activity) {
        if (activity == null) {
            return false;
        }

        int t = activity.getResources().getConfiguration().orientation;
        if (t == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        }

        return false;
    }

    public static void fullScreen(Activity activity) {
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void nonFullScreen(Activity activity) {
        activity.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void screenLandscape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static void screenPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void zoomView(int w, int h, View view) {
        if (view == null) {
            return;
        }

        Context context = view.getContext().getApplicationContext();
        ViewGroup.LayoutParams params = view.getLayoutParams();

        if (params == null) {
            return;
        }

        int width = Math.min(getScreenWidth(context), getScreenHeight(context));
        int height = Math.round(width * h / 320f + 0.5f);

        params.width = width;
        params.height = height;
    }

    public static void zoomViewFull(View view) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();

        if (params == null) {
            return;
        }

        Context context = view.getContext().getApplicationContext();

        params.width = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        params.height = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }

    public static View inflate(Context context, int resource, ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(resource, root, attachToRoot);
    }

    public static int getStatusHeight(Context context){
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight){
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    private static int getNavigationHeightFromResource(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int navigationBarHeight = 0;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("config_showNavigationBar",
                "bool", "android");
        if (resourceId > 0) {
            boolean hasNav = resources.getBoolean(resourceId);
            if (hasNav) {
                resourceId = resources.getIdentifier("navigation_bar_height",
                        "dimen", "android");
                if (resourceId > 0) {
                    navigationBarHeight = resources
                            .getDimensionPixelSize(resourceId);
                }
            }
        }

        if (navigationBarHeight <= 0) {
            DisplayMetrics dMetrics = new DisplayMetrics();
            display.getMetrics(dMetrics);
            int screenHeight = Math.max(dMetrics.widthPixels, dMetrics.heightPixels);
            int realHeight = 0;
            try {
                Method mt = display.getClass().getMethod("getRealSize", Point.class);
                Point size = new Point();
                mt.invoke(display, size);
                realHeight = Math.max(size.x, size.y);
            } catch (NoSuchMethodException e) {
                Method mt = null;
                try {
                    mt = display.getClass().getMethod("getRawHeight");
                } catch (NoSuchMethodException e2) {
                    try {
                        mt = display.getClass().getMethod("getRealHeight");
                    } catch (NoSuchMethodException e3) {
                    }
                }
                if (mt != null) {
                    try {
                        realHeight = (Integer) mt.invoke(display);
                    } catch (Exception e1) {
                    }
                }
            } catch (Exception e) {
            }
            navigationBarHeight = realHeight - screenHeight;
        }

        return navigationBarHeight;
    }

    public static File getFilesDir(Context ctx) {
		if (null == ctx) {
			return null;
		}

		File result = null;
		for (int i = 0; i < 3; ++i) {
			result = ctx.getFilesDir();
			if (null != result) {
				break;
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}

		return result;
	}
}
