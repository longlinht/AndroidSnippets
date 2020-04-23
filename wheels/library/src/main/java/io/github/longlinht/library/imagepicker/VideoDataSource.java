package io.github.longlinht.library.imagepicker;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao He on 2018/8/25.
 * hetaoof@gmail.com
 */
public class VideoDataSource implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int LOADER_ALL = 2;

    private final String[] VIDEO_PROJECTION = {
            MediaStore.Video.VideoColumns._ID,
            MediaStore.Video.VideoColumns.DATA,
            MediaStore.Video.VideoColumns.DISPLAY_NAME,
            MediaStore.Video.VideoColumns.DURATION
    };

    private FragmentActivity activity;
    private OnVideosLoadedListener loadedListener;
    private ArrayList<VideoFileModel> videoFiles = new ArrayList<>();

    /**
     * @param activity       用于初始化LoaderManager，需要兼容到2.3
     * @param loadedListener 加载完成的监听
     */
    public VideoDataSource(FragmentActivity activity, OnVideosLoadedListener loadedListener) {
        this.activity = activity;
        this.loadedListener = loadedListener;

        LoaderManager loaderManager = activity.getSupportLoaderManager();
        loaderManager.destroyLoader(LOADER_ALL);

        loaderManager.initLoader(LOADER_ALL, null, this);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader = null;
        if (id == LOADER_ALL)
            cursorLoader = new CursorLoader(activity, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VIDEO_PROJECTION, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        videoFiles.clear();
        if (data != null) {

            while (data.moveToNext()) {

                VideoFileModel fileItem = new VideoFileModel();
                fileItem.filePath = (data.getString(data.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                File file = new File(fileItem.filePath);
                boolean canRead = file.canRead();
                long length = file.length();
                if (!canRead || length == 0) {
                    continue;
                }
                fileItem.fileName = data.getString(data.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                long duration = data.getLong(data.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                if (duration < 0)
                    duration = 0;
                fileItem.duration = duration;

                if (fileItem.fileName != null && fileItem.fileName.endsWith(".mp4")) {
                    videoFiles.add(fileItem);
                }
            }
            loadedListener.onVideosLoaded(videoFiles);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    public interface OnVideosLoadedListener {
        void onVideosLoaded(List<VideoFileModel> videos);
    }
}
