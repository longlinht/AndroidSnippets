package com.snippets.tao.androidsnippets;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.snippets.tao.androidsnippets.ui.PageUpDownAnimation;
import com.snippets.tao.androidsnippets.utils.PermissionConstant;
import com.snippets.tao.androidsnippets.utils.PermissionUtils;
import com.snippets.tao.androidsnippets.utils.ScreenshotManager;

public class MainActivity extends AppCompatActivity implements ScreenshotManager.OnScreenshotTakenListener{

    private ScreenshotManager mScreenshotManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        TextView pageDownView = (TextView)findViewById(R.id.pageDownView);
        TextView pageUpView = (TextView)findViewById(R.id.pageUpView);

        PageUpDownAnimation.startPageUpDownAnimation(pageDownView, pageUpView);
        mScreenshotManager = new ScreenshotManager(this, this);

        PermissionUtils.checkReadStoragePermission(this);
        */


        /*
        Intent intent = new Intent();
        intent.setClass(this, SkypeCallService.class);

        startService(intent);
        finish();
        */
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onScreenshotTaken(String path) {
        Toast.makeText(this, "screenshot token!!!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PermissionConstant.REQUEST_EXTERNAL_READ){
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mScreenshotManager.startScreenshotObserver();
            }else{
                finish();
            }
        }
    }
}
