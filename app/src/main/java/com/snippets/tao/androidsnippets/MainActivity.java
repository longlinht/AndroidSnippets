package com.snippets.tao.androidsnippets;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.snippets.tao.androidsnippets.demo.AgorithmImpl;
import com.snippets.tao.androidsnippets.demo.SimpleJobIntentService;
import com.snippets.tao.androidsnippets.demo.ThreadLocalDemo;
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


        Button button = findViewById(R.id.test_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                SimpleJobIntentService.enqueueWork(MainActivity.this, intent);
            }
        });

        //ThreadLocalDemo.test();
        AgorithmImpl.test();

        try {
            ThreadLocalDemo.startDemo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
