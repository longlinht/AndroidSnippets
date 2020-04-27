package io.github.longlinht.library.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.longlinht.library.R;
import io.github.longlinht.library.utils.LocaleManagerUtil;

/**
 * Created by ming on 2018/2/22.
 */

public class ShadowPermissionActivity extends AppCompatActivity {

    /**
     * start ShadowPermissionActivity self
     *
     * @param context            Context
     * @param item               permission item
     * @param permissionListener permission listener
     */
    public static void start(Context context, PermissionItem item, PermissionListener permissionListener) {
        setPermissionListener(permissionListener);

        Intent intent = new Intent(context, ShadowPermissionActivity.class);
        intent.putExtra(ShadowPermissionActivity.BUNDLE_PERMISSION_ITEM, item);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }


    public static final int REQ_CODE_PERMISSION_REQUEST = 110;
    public static final int REQ_CODE_REQUEST_SETTING = 119;
    public static final int REQ_CODE_REQUEST_SYSTEM_ALERT_WINDOW = 120;
    public static final int REQ_CODE_REQUEST_WRITE_SETTING = 121;

    public static final String BUNDLE_PERMISSION_ITEM = "bundle_permission_item";

    boolean hasRequestedSystemAlertWindow = false;
    String permissionSystemAlertWindow;
    boolean hasRequestedWriteSettings = false;
    String permissionWriteSettings;
    String packageName;

    PermissionItem mPermissionItem;

    //权限名称
    private HashMap<String, String> permissionMap = new HashMap<>();
    //权限说明
    private HashMap<String, String> permissionIssueMap = new HashMap<>();

    private static OnPermissionRequestFinishedListener sOnPermissionRequestFinishedListener;
    private static PermissionListener sPermissionListener;

    public static void setOnPermissionRequestFinishedListener(OnPermissionRequestFinishedListener listener) {
        sOnPermissionRequestFinishedListener = listener;
    }

    private static void setPermissionListener(PermissionListener permissionListener) {
        sPermissionListener = permissionListener;
    }

    private void initPermissionText() {
        permissionMap.put(Manifest.permission.READ_PHONE_STATE, getString(R.string.permission_title_phone_state));
        permissionMap.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.permission_title_storage));
        permissionMap.put(Manifest.permission.READ_EXTERNAL_STORAGE, getString(R.string.permission_title_storage));
        permissionMap.put(Manifest.permission.CAMERA, getString(R.string.permission_title_camera));
        permissionMap.put(Manifest.permission.READ_CONTACTS, getString(R.string.permission_title_contacts));

        permissionIssueMap.put(Manifest.permission.READ_PHONE_STATE, getString(R.string.permission_des_phone_state));
        permissionIssueMap.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, getString(R.string.permission_des_storage));
        permissionIssueMap.put(Manifest.permission.READ_EXTERNAL_STORAGE, getString(R.string.permission_des_storage));
        permissionIssueMap.put(Manifest.permission.CAMERA, getString(R.string.permission_des_camera));
        permissionIssueMap.put(Manifest.permission.READ_CONTACTS, getString(R.string.permission_des_contacts));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha = 0.f;
        window.setAttributes(wl);

        onNewIntent(getIntent());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManagerUtil.updateLocale(newBase));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            setIntent(intent);
        }
        initPermissionText();

        packageName = getPackageName();

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mPermissionItem = (PermissionItem) bundle.getSerializable(BUNDLE_PERMISSION_ITEM);
        if (mPermissionItem == null) {
            return;
        }

        checkPermissions(false);
    }

    private void permissionGranted() {
        if (sPermissionListener != null) {
            sPermissionListener.permissionGranted();
            sPermissionListener = null;
        }

        if (sOnPermissionRequestFinishedListener == null || !sOnPermissionRequestFinishedListener.onPermissionRequestFinishedAndCheckNext(mPermissionItem.permissions)) {
        }

        finish();
        overridePendingTransition(0, 0);
    }

    private void permissionDenied(List<String> deniedpermissions) {
        if (sPermissionListener != null) {
            sPermissionListener.permissionDenied();
            sPermissionListener = null;
        }

        if (sOnPermissionRequestFinishedListener == null || !sOnPermissionRequestFinishedListener.onPermissionRequestFinishedAndCheckNext(mPermissionItem.permissions)) {
        }

        finish();
        overridePendingTransition(0, 0);
    }

    private void gotoSetting() {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
                    Uri.parse("package:" + packageName));
            startActivityForResult(intent, REQ_CODE_REQUEST_SETTING);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            startActivityForResult(intent, REQ_CODE_REQUEST_SETTING);
        }
    }

    private void checkPermissions(boolean isAllRequested) {

        List<String> needPermissions = PermissionUtils.findDeniedPermissions(this, mPermissionItem.permissions);

        boolean showRationale = false;
        for (String permission : needPermissions) {
            if (!hasRequestedSystemAlertWindow && permission.equals(Manifest.permission.SYSTEM_ALERT_WINDOW)) {
                permissionSystemAlertWindow = Manifest.permission.SYSTEM_ALERT_WINDOW;
            } else if (!hasRequestedWriteSettings && permission.equals(Manifest.permission.WRITE_SETTINGS)) {
                permissionWriteSettings = Manifest.permission.WRITE_SETTINGS;
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showRationale = true;
            }
        }

        if (!PermissionUtils.isOverMarshmallow()) {
            permissionGranted();
        } else if (needPermissions.isEmpty()) {
            permissionGranted();
        } else if (isAllRequested) {
            //From Setting Activity
            permissionDenied(needPermissions);
        } else if (showRationale && !TextUtils.isEmpty(mPermissionItem.rationalMessage)) {
            //Need Show Rationale
            showRationaleDialog(needPermissions);
        } else {
            //Need Request Permissions
            requestPermissions(needPermissions);
        }
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public void requestPermissions(List<String> needPermissions) {
        //first SYSTEM_ALERT_WINDOW
        if (!hasRequestedSystemAlertWindow && !TextUtils.isEmpty(permissionSystemAlertWindow)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName));
            startActivityForResult(intent, REQ_CODE_REQUEST_SYSTEM_ALERT_WINDOW);
        } else if (!hasRequestedWriteSettings && !TextUtils.isEmpty(permissionWriteSettings)) {
            //second WRITE_SETTINGS
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + packageName));
            startActivityForResult(intent, REQ_CODE_REQUEST_WRITE_SETTING);
        } else {
            //other permission
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQ_CODE_PERMISSION_REQUEST);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> deniedPermissions = new ArrayList<>();

        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];

            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }

        if (deniedPermissions.isEmpty()) {
            permissionGranted();
        } else {
            showPermissionDenyDialog(deniedPermissions);
        }
    }

    private void showRationaleDialog(final List<String> needPermissions) {
        PermissionDialog.Builder builder = new PermissionDialog.Builder(this)
                .view(R.layout.permission_dialog)
                .findTextViewAndSetText(R.id.content, mPermissionItem.rationalMessage)
                .findTextViewAndSetText(R.id.confirm, TextUtils.isEmpty(mPermissionItem.rationalButton)
                        ? getString(R.string.permission_default_rational_button)
                        : mPermissionItem.rationalButton)
                .addDialogEventView(R.id.confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermissions(needPermissions);
                    }
                })
                .findViewAndSetVisibility(R.id.cancel, View.GONE)
                .setCancelable(false);
        if (!TextUtils.isEmpty(mPermissionItem.rationalButton)) {
            builder.findTextViewAndSetText(R.id.confirm, mPermissionItem.rationalButton);
        }
        builder.show();
    }

    public void showPermissionDenyDialog(final List<String> deniedPermissions) {
        mPermissionItem.deniedMessage(TextUtils.isEmpty(mPermissionItem.deniedMessage)
                ? getString(R.string.permission_default_denied_message)
                : mPermissionItem.deniedMessage);
        if (!mPermissionItem.needGotoSetting) {
            permissionDenied(deniedPermissions);
            return;
        }

        buildPermissionTipDialog(deniedPermissions);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_REQUEST_SETTING: {
                checkPermissions(true);
                break;
            }
            case REQ_CODE_REQUEST_SYSTEM_ALERT_WINDOW: {
                hasRequestedSystemAlertWindow = true;
                checkPermissions(false);
                break;
            }
            case REQ_CODE_REQUEST_WRITE_SETTING: {
                hasRequestedWriteSettings = true;
                checkPermissions(false);
                break;
            }
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    /**
     * 权限请求结束的回调
     */
    public interface OnPermissionRequestFinishedListener {
        /**
         * @param permissions 已经处理完的权限申请
         * @return 是否还有其他未处理的权限
         */
        boolean onPermissionRequestFinishedAndCheckNext(String[] permissions);
    }

    /**
     * 未授权的提示dialog
     */
    private void buildPermissionTipDialog(final List<String> deniedPermissions) {
        new PermissionDialog.Builder(this)
                .view(R.layout.permission_dialog)
                .findTextViewAndSetText(R.id.content, TextUtils.isEmpty(mPermissionItem.deniedMessage)
                        ? getString(R.string.permission_default_denied_message)
                        : mPermissionItem.deniedMessage)
                .findTextViewAndSetText(R.id.confirm, TextUtils.isEmpty(mPermissionItem.deniedButton)
                        ? getString(R.string.permission_default_denied_button)
                        : mPermissionItem.deniedButton)
                .setRecycler(new PermissionListAdapter(deniedPermissions))
                .addDialogEventView(R.id.confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoSetting();
                    }
                })
                .addDialogEventView(R.id.cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        permissionDenied(deniedPermissions);
                    }
                })
                .setCancelable(false)
                .show();
    }

    private class PermissionListAdapter extends RecyclerView.Adapter<PermissionHolder> {

        private final List<String> deniedPermissionList = new ArrayList<>();

        PermissionListAdapter(List<String> deniedPermissions) {
            if (deniedPermissions == null || deniedPermissions.size() == 0) {
                return;
            }
            deniedPermissionList.addAll(deniedPermissions);
            //存储读写权限的提示文案一样，所以只保留一个
            if (deniedPermissionList.contains(Manifest.permission.READ_EXTERNAL_STORAGE)
                    && deniedPermissionList.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                deniedPermissionList.remove(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }

        @Override
        public PermissionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.permission_item, parent, false);
            return new PermissionHolder(view);
        }

        @Override
        public void onBindViewHolder(PermissionHolder holder, int position) {
            if (position < deniedPermissionList.size() && position >= 0) {
                String permission = deniedPermissionList.get(position);
                String permissionName = permissionMap.get(permission);
                String permissionDesc = permissionIssueMap.get(permission);
                String item = permissionName + ": " + permissionDesc;
                holder.permissionText.setText(item);
            }
        }

        @Override
        public int getItemCount() {
            return deniedPermissionList.size();
        }
    }

    private class PermissionHolder extends RecyclerView.ViewHolder {
        TextView permissionText;

        PermissionHolder(View itemView) {
            super(itemView);
            permissionText = itemView.findViewById(R.id.tv_permission);
        }
    }

}
