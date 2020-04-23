package io.github.longlinht.library.base;

import io.github.longlinht.library.utils.ActivityUtils;

/**
 * Created by Tao He on 2018/11/23.
 * hetaoof@gmail.com
 */
public class DXExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        ActivityUtils.finishAllActivities();
        System.exit(2);
    }
}
