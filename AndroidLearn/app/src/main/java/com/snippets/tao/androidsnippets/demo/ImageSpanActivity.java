package com.snippets.tao.androidsnippets.demo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.snippets.tao.androidsnippets.R;

/**
 * Created by Tao He on 2019-10-23.
 * hetaoof@gmail.com
 */
public class ImageSpanActivity extends AppCompatActivity {

    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_span);

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

    }

}
