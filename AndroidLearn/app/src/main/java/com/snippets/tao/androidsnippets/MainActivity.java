package com.snippets.tao.androidsnippets;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.snippets.tao.androidsnippets.demo.AgorithmImpl;
import com.snippets.tao.androidsnippets.demo.BgImageSpan;
import com.snippets.tao.androidsnippets.demo.SimpleJobIntentService;
import com.snippets.tao.androidsnippets.demo.ThreadLocalDemo;
import com.snippets.tao.androidsnippets.demo.UrlImageSpan;
import com.snippets.tao.androidsnippets.demo.handler.Main;
import com.snippets.tao.androidsnippets.demo.jni.HelloJniActivity;
import com.snippets.tao.androidsnippets.source.audiovideo.chapter1.DrawPicActivity;
import com.snippets.tao.androidsnippets.source.audiovideo.chapter2.AudioRecordActivity;
import com.snippets.tao.androidsnippets.source.audiovideo.chapter3.CameraActivity;
import com.snippets.tao.androidsnippets.source.audiovideo.chapter3.H264Activity;
import com.snippets.tao.androidsnippets.source.concurrency.practice.PrintLettersWithSemaphore;
import com.snippets.tao.androidsnippets.source.concurrency.practice.PrintThreadWithReentrantLock;
import com.snippets.tao.androidsnippets.source.concurrency.practice.PrintThreadWithSync;
import com.snippets.tao.androidsnippets.ui.PageUpDownAnimation;
import com.snippets.tao.androidsnippets.utils.PermissionConstant;
import com.snippets.tao.androidsnippets.utils.PermissionUtils;
import com.snippets.tao.androidsnippets.utils.ScreenshotManager;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class MainActivity extends AppCompatActivity implements ScreenshotManager.OnScreenshotTakenListener{

    private ScreenshotManager mScreenshotManager;
    private TextView mContent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContent = findViewById(R.id.tv_content);

        String username = "用户名：";
        String message = "哈哈，我是一个天才";

        SpannableStringBuilder ssb = new SpannableStringBuilder(username);
        ssb.append(message);

        //得到宽高
        Rect bounds = new Rect();
        //先根据TextView 属性 设置字体大小
        Paint paint = mContent.getPaint();
        //获得字符串所占空间大小
        paint.getTextBounds(username, 0, username.length(), bounds);

        //Drawable drawable = getDrawable(R.drawable.round_rect);

        View view = LayoutInflater.from(this).inflate(R.layout.container, null);
        TextView textView = view.findViewById(R.id.tv_value);
        textView.setText(username);
        //view.buildDrawingCache();

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        BitmapDrawable drawable = new BitmapDrawable(bitmap);


        //drawable.setBounds(0, 0, (int)(bounds.width() * 1.5), (int)(bounds.height()* 1.5));

        //ImageSpan nameBgSpan = new BgImageSpan(drawable, mContent, username);
        ImageSpan nameBgSpan = new ImageSpan(this, bitmap);
        ssb.setSpan(nameBgSpan, 0, username.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        ForegroundColorSpan contentColorSpan = new ForegroundColorSpan(Color.parseColor("#ffc800"));
        ssb.setSpan(contentColorSpan, username.length(), username.length() + message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        String url = "http://img2.imgtn.bdimg.com/it/u=1467875646,1039972052&fm=26&gp=0.jpg";


        RequestOptions options = new RequestOptions()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(this)
                .load(url)
                .apply(options)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (resource instanceof BitmapDrawable) {
                            resource.setBounds(0, 0, 50, 50);

                            ImageSpan iconSpan = new ImageSpan(resource);
                            ssb.setSpan(iconSpan, username.length(), username.length() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                            mContent.setText(ssb);
                            mContent.postInvalidate();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });





        //ImageView imageView = findViewById(R.id.iv_icon);
        //Glide.with(this).load(url).into(imageView);




        Button button = findViewById(R.id.test_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, H264Activity.class);
                MainActivity.this.startActivity(intent);
                //SimpleJobIntentService.enqueueWork(MainActivity.this, intent);
            }
        });

        //PrintLettersWithSemaphore letters = new PrintLettersWithSemaphore();
        //letters.start();
        //PrintThreadWithSync printThreadWithSync = new PrintThreadWithSync();
        //printThreadWithSync.start();
        //PrintThreadWithReentrantLock printThreadWithReentrantLock = new PrintThreadWithReentrantLock();
        //printThreadWithReentrantLock.start();



        //new Main().start();

        //ThreadLocalDemo.test();
        AgorithmImpl.test();

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
        //resolve(()->getScreenshotManager()).ifPresent(ScreenshotManager::startScreenshotObserver);


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


    /*
    public static float getTextWidth(String text, float size) {

        TextPaint FontPaint = new TextPaint();

        FontPaint.setTextSize(size);

        return FontPaint.measureText(text);

    }
    */

    private static float getTextWith(String text, TextView tv) {
        Rect bounds = new Rect();
        TextPaint paint;

        paint = tv.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }
}




