package com.snippets.tao.androidsnippets.demo;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.snippets.tao.androidsnippets.R;
import com.snippets.tao.androidsnippets.logger.Log;

import java.lang.reflect.Field;

/**
 * Created by Tao He on 2019-10-18.
 * hetaoof@gmail.com
 */

public class UrlImageSpan extends ImageSpan {

    private String url;
    private TextView tv;
    private boolean picShowed;

    public UrlImageSpan(Context context, String url, TextView tv) {
        super(context, R.mipmap.ic_launcher);
        this.url = url;
        this.tv = tv;

        Log.d("htdebug", "UrlImageSpan");
    }

    @Override
    public Drawable getDrawable() {
        if (!picShowed) {
            RequestOptions options = new RequestOptions()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(tv.getContext())
                    .load(url)
                    .apply(options)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            if (resource instanceof BitmapDrawable) {
                                resource.setBounds(0, 0, 50, 50);

                                Field mDrawable;
                                Field mDrawableRef;
                                try {
                                    mDrawable = ImageSpan.class.getDeclaredField("mDrawable");
                                    mDrawable.setAccessible(true);
                                    mDrawable.set(UrlImageSpan.this, resource);

                                    mDrawableRef = DynamicDrawableSpan.class.getDeclaredField("mDrawableRef");
                                    mDrawableRef.setAccessible(true);
                                    mDrawableRef.set(UrlImageSpan.this, null);

                                    tv.postInvalidate();

                                    picShowed = true;
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
        return super.getDrawable();
    }
}
