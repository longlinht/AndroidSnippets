package io.github.longlinht.library.permission;

import java.io.Serializable;

/**
 * Created by ming on 2018/2/22.
 */

public class PermissionItem implements Serializable {

    String[] permissions;
    String rationalMessage;
    String rationalButton;
    String deniedMessage;
    String deniedButton;
    boolean needGotoSetting = true;

    public PermissionItem(String... permissions) {
        if (permissions == null || permissions.length <= 0) {
            throw new IllegalArgumentException("permissions must have one content at least");
        }

        this.permissions = permissions;
    }

    //申请权限时弹窗说明理由
    public PermissionItem rationalMessage(String rationalMessage) {
        this.rationalMessage = rationalMessage;
        return this;
    }

    //申请权限弹窗的按钮(默认为"OK")
    public PermissionItem rationalButton(String rationalButton) {
        this.rationalButton = rationalButton;
        return this;
    }

    //权限被拒绝的说明
    public PermissionItem deniedMessage(String deniedMessage) {
        this.deniedMessage = deniedMessage;
        return this;
    }

    //权限被拒的按钮(默认为"去设置"），点击会跳转设置
    public PermissionItem deniedButton(String deniedButton) {
        this.deniedButton = deniedButton;
        return this;
    }

    //有权限未授予是否引导用户去设置，默认开启，否则返回ondenied(拒绝的权限)
    public PermissionItem needGotoSetting(boolean needGotoSetting) {
        this.needGotoSetting = needGotoSetting;
        return this;
    }
}
