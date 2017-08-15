package com.snippets.tao.androidsnippets.utils;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;
import com.snippets.tao.androidsnippets.logger.Log;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tao He on 17/8/11.
 * hetaoof@gmail.com
 */

public class ScreenshotManager {

    private OnScreenshotTakenListener mListener;
    private String mLastTakenPath;
    private Context mContext;
    private Timer mCheckImageTimer;
    private int mCount = 0;

    /**
     * Internal storage observer
     */
    private ScreenshotObserver mInternalObserver;

    /**
     * External storage observer
     */

    private ScreenshotObserver mExternalObserver;

    private HandlerThread mHandlerThread;
    private Handler mContenHandler;


    private static final String[] MEDIA_PROJECTIONS =  {
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DATE_TAKEN,
    };

    private static final String[] KEYWORDS = {
            "screenshot", "screen_shot", "screen-shot", "screen shot",
            "screencapture", "screen_capture", "screen-capture", "screen capture",
            "screencap", "screen_cap", "screen-cap", "screen cap", "截屏"
    };


    public ScreenshotManager(Context context, OnScreenshotTakenListener listener) {
        mContext = context;
        mListener = listener;
        mLastTakenPath = getLastTakenPath();

        mHandlerThread = new HandlerThread("Screenshot_Observer");
        mHandlerThread.start();
        mContenHandler = new Handler(mHandlerThread.getLooper());

        mInternalObserver = new ScreenshotObserver(mContenHandler, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        mExternalObserver = new ScreenshotObserver(mContenHandler, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    public void startScreenshotObserver() {
        Log.e("htdebug", "start observe");
        mContext.getContentResolver().registerContentObserver(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                false,
                mInternalObserver
        );
        mContext.getContentResolver().registerContentObserver(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                false,
                mExternalObserver
        );
    }

    public void stopScreenshotObserver() {
        mContext.getContentResolver().unregisterContentObserver(mInternalObserver);
        mContext.getContentResolver().unregisterContentObserver(mExternalObserver);
        releaseTimer();
    }

    private void handleMediaContentChange(Uri contentUri) {
        Cursor cursor = null;
        try {
            // 数据改变时查询数据库中最后加入的一条数据
            cursor = mContext.getContentResolver().query(
                    contentUri,
                    MEDIA_PROJECTIONS,
                    null,
                    null,
                    MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1"
            );

            if (cursor == null) {
                return;
            }
            if (!cursor.moveToFirst()) {
                return;
            }

            // 获取各列的索引
            int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            int dateTakenIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN);

            // 获取行数据
            String data = cursor.getString(dataIndex);
            long dateTaken = cursor.getLong(dateTakenIndex);

            // 处理获取到的第一行数据
            handleMediaRowData(data, dateTaken);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    private void handleMediaRowData(String data, long dateTaken) {
        if (TextUtils.isEmpty(data))
            return;

        if (checkScreenShot(data, dateTaken)) {
            if (!data.equals(mLastTakenPath)) {
                mLastTakenPath = data;
                final File file = new File(data);
                if(null == BitmapFactory.decodeFile(file.getPath())) {
                    if (mCheckImageTimer == null) {
                        mCheckImageTimer = new Timer();
                    }
                    mCheckImageTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (mCount > 10) {
                                mCheckImageTimer.cancel();
                                mCheckImageTimer = null;
                                return;
                            }
                            Bitmap image = BitmapFactory.decodeFile(mLastTakenPath);
                            if(image != null) {
                                mListener.onScreenshotTaken(mLastTakenPath);
                                mCheckImageTimer.cancel();
                                mCheckImageTimer = null;
                                mCount = 0;
                            } else {
                                mCount++;
                            }
                        }
                    }, 0, 1000);
                } else {
                    mListener.onScreenshotTaken(mLastTakenPath);
                }
            }
            mLastTakenPath = data;
        }
    }

    private boolean checkScreenShot(String data, long dateTaken) {

        data = data.toLowerCase();
        // 判断图片路径是否含有指定的关键字之一, 如果有, 则认为当前截屏了
        for (String keyWork : KEYWORDS) {
            if (data.contains(keyWork)) {
                return true;
            }
        }
        return false;
    }

    private String getLastTakenPath() {
        Cursor cursor = null;
        String path = "";
        try {
            // 数据改变时查询数据库中最后加入的一条数据
            cursor = mContext.getContentResolver().query(
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                    MEDIA_PROJECTIONS,
                    null,
                    null,
                    MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1"
            );

            if (cursor == null) {
                return "";
            }
            if (!cursor.moveToFirst()) {
                return "";
            }

            int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            int dateTakenIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN);

            path = cursor.getString(dataIndex);

            cursor = mContext.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    MEDIA_PROJECTIONS,
                    null,
                    null,
                    MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1"
            );

            if (cursor == null) {
                return "";
            }
            if (!cursor.moveToFirst()) {
                return "";
            }

            dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN);
            if (index > dateTakenIndex) {
                path = cursor.getString(dataIndex);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return path;
    }

    private void releaseTimer() {
        if (mCheckImageTimer != null) {
            mCheckImageTimer.cancel();
            mCheckImageTimer = null;
        }
    }

    public interface OnScreenshotTakenListener {
        void onScreenshotTaken(String path);
    }

    private class ScreenshotObserver extends ContentObserver {
        private Uri mContentUri;
        public ScreenshotObserver(Handler handler, Uri contentUri) {
            super(handler);
            mContentUri = contentUri;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            handleMediaContentChange(mContentUri);
        }
    }
}
