package com.snippets.tao.androidsnippets;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.snippets.tao.androidsnippets.demo.AgorithmImpl;
import com.snippets.tao.androidsnippets.demo.SimpleJobIntentService;
import com.snippets.tao.androidsnippets.demo.ThreadLocalDemo;
import com.snippets.tao.androidsnippets.demo.handler.Main;
import com.snippets.tao.androidsnippets.source.concurrency.practice.PrintLettersWithSemaphore;
import com.snippets.tao.androidsnippets.source.concurrency.practice.PrintThreadWithReentrantLock;
import com.snippets.tao.androidsnippets.source.concurrency.practice.PrintThreadWithSync;
import com.snippets.tao.androidsnippets.ui.PageUpDownAnimation;
import com.snippets.tao.androidsnippets.utils.PermissionConstant;
import com.snippets.tao.androidsnippets.utils.PermissionUtils;
import com.snippets.tao.androidsnippets.utils.ScreenshotManager;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class MainActivity extends AppCompatActivity implements ScreenshotManager.OnScreenshotTakenListener{

    private ScreenshotManager mScreenshotManager;
    @RequiresApi(api = Build.VERSION_CODES.N)
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

        //PrintLettersWithSemaphore letters = new PrintLettersWithSemaphore();
        //letters.start();
        //PrintThreadWithSync printThreadWithSync = new PrintThreadWithSync();
        //printThreadWithSync.start();
        PrintThreadWithReentrantLock printThreadWithReentrantLock = new PrintThreadWithReentrantLock();
        printThreadWithReentrantLock.start();



        //new Main().start();

        //ThreadLocalDemo.test();
        //AgorithmImpl.test();

        /*
        try {
            ThreadLocalDemo.startDemo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        //Objects.requireNonNull(getScreenshotManager()).startScreenshotObserver();
        //Optional.of(getScreenshotManager()).ifPresent(ScreenshotManager::startScreenshotObserver);
        /*
        TextView pageDownView = (TextView)findViewById(R.id.pageDownView);
        TextView pageUpView = (TextView)findViewById(R.id.pageUpView);

        PageUpDownAnimation.startPageUpDownAnimation(pageDownView, pageUpView);
        mScreenshotManager = new ScreenshotManager(this, this);

        PermissionUtils.checkReadStoragePermission(this);
        */
        resolve(()->getScreenshotManager()).ifPresent(ScreenshotManager::startScreenshotObserver);


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

    private ScreenshotManager getScreenshotManager() {
        return new ScreenshotManager(this, null);
        //return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            // 可能会抛出空指针异常，直接返回一个空的 Optional 对象
            return Optional.empty();
        }
    }

    static void log(Object x) {
        System.out.println(x.toString());
    }
    static void foo() {
        log(null);
    }
}
