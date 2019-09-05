package com.snippets.tao.androidsnippets.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

/**
 * Created by Tao He on 17/9/13.
 * hetaoof@gmail.com
 */

public class PermissionUtils {

    public static boolean checkReadStoragePermission(@NonNull Activity activity) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }
        int readStoragePermissionState =
                ContextCompat.checkSelfPermission(activity, READ_EXTERNAL_STORAGE);

        boolean readStoragePermissionGranted = readStoragePermissionState == PackageManager.PERMISSION_GRANTED;

        if (!readStoragePermissionGranted) {
            ActivityCompat.requestPermissions(activity,
                    PermissionConstant.PERMISSIONS_EXTERNAL_READ,
                    PermissionConstant.REQUEST_EXTERNAL_READ);
        }
        return readStoragePermissionGranted;
    }

    public static boolean checkWriteStoragePermission(Fragment fragment) {

        int writeStoragePermissionState =
                ContextCompat.checkSelfPermission(fragment.getContext(), WRITE_EXTERNAL_STORAGE);

        boolean writeStoragePermissionGranted = writeStoragePermissionState == PackageManager.PERMISSION_GRANTED;

        if (!writeStoragePermissionGranted) {
            fragment.requestPermissions(PermissionConstant.PERMISSIONS_EXTERNAL_WRITE,
                    PermissionConstant.REQUEST_EXTERNAL_WRITE);
        }
        return writeStoragePermissionGranted;
    }

    public static boolean checkCameraPermission(Fragment fragment) {
        int cameraPermissionState = ContextCompat.checkSelfPermission(fragment.getContext(), CAMERA);

        boolean cameraPermissionGranted = cameraPermissionState == PackageManager.PERMISSION_GRANTED;

        if (!cameraPermissionGranted) {
            fragment.requestPermissions(PermissionConstant.PERMISSIONS_CAMERA,
                    PermissionConstant.REQUEST_CAMERA);
        }
        return cameraPermissionGranted;
    }
}

