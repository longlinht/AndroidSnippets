package io.github.longlinht.library.permission;

import android.content.Context;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ming on 2018/2/22.
 */

public class PermissionChecker {
    private static final String TAG = "PermissionChecker";

    private static PermissionChecker sInstance;
    private final Context mContext;
    private PermissionRequestWrapper mCurPermissionRequestWrapper;
    private Queue<PermissionRequestWrapper> mPermissionRequestWrappers =
            new ConcurrentLinkedQueue<>();
    private ShadowPermissionActivity.OnPermissionRequestFinishedListener
            mOnPermissionRequestFinishedListener =
            new ShadowPermissionActivity.OnPermissionRequestFinishedListener() {
                @Override
                public boolean onPermissionRequestFinishedAndCheckNext(String[] permissions) {
                    mCurPermissionRequestWrapper = mPermissionRequestWrappers.poll();
                    if (mCurPermissionRequestWrapper != null) {
                        requestPermissions(mCurPermissionRequestWrapper);
                    }

                    return mCurPermissionRequestWrapper != null;
                }
            };

    public static PermissionChecker instance(Context context) {
        if (sInstance == null) {
            synchronized (PermissionChecker.class) {
                if (sInstance == null) {
                    sInstance = new PermissionChecker(context);
                }
            }
        }

        return sInstance;
    }

    private PermissionChecker(Context context) {
        this.mContext = context.getApplicationContext();

        ShadowPermissionActivity.setOnPermissionRequestFinishedListener(mOnPermissionRequestFinishedListener);
    }

    public void check(PermissionItem permissionItem, PermissionListener permissionListener) {
        if (permissionItem == null || permissionListener == null) {
            return;
        }

        if (!PermissionUtils.isOverMarshmallow()) {
            onPermissionGranted(permissionItem, permissionListener);
        } else {
            mPermissionRequestWrappers.add(new PermissionRequestWrapper(permissionItem, permissionListener));
            mCurPermissionRequestWrapper = mPermissionRequestWrappers.poll();
            if (mCurPermissionRequestWrapper != null) {
                requestPermissions(mCurPermissionRequestWrapper);
            }
        }
    }

    private void requestPermissions(PermissionRequestWrapper permissionRequestWrapper) {
        final PermissionItem item = permissionRequestWrapper.permissionItem;
        final PermissionListener listener = permissionRequestWrapper.permissionListener;

        if (PermissionUtils.hasSelfPermissions(mContext, item.permissions)) {
            onPermissionGranted(item, listener);
        } else {
            ShadowPermissionActivity.start(mContext, item, listener);
        }
    }

    private void onPermissionGranted(PermissionItem item, PermissionListener listener) {

        if (listener != null) {
            listener.permissionGranted();
        }

        mOnPermissionRequestFinishedListener.onPermissionRequestFinishedAndCheckNext(item.permissions);
    }

    class PermissionRequestWrapper {
        PermissionItem permissionItem;
        PermissionListener permissionListener;

        public PermissionRequestWrapper(PermissionItem item, PermissionListener listener) {
            this.permissionItem = item;
            this.permissionListener = listener;
        }
    }
}
