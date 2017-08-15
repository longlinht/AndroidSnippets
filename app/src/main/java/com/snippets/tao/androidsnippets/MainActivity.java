package com.snippets.tao.androidsnippets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.snippets.tao.androidsnippets.ui.PageUpDownAnimation;
import com.snippets.tao.androidsnippets.utils.ScreenshotManager;

public class MainActivity extends AppCompatActivity implements ScreenshotManager.OnScreenshotTakenListener{

    private ScreenshotManager mScreenshotManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView pageDownView = (TextView)findViewById(R.id.pageDownView);
        TextView pageUpView = (TextView)findViewById(R.id.pageUpView);

        PageUpDownAnimation.startPageUpDownAnimation(pageDownView, pageUpView);
        mScreenshotManager = new ScreenshotManager(this, this);

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
        mScreenshotManager.startScreenshotObserver();

    }

    @Override
    public void onPause() {
        super.onPause();
        mScreenshotManager.stopScreenshotObserver();
    }


    @Override
    public void onScreenshotTaken(String path) {

    }
}
