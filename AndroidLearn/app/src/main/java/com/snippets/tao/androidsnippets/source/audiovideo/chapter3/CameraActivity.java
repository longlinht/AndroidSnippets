package com.snippets.tao.androidsnippets.source.audiovideo.chapter3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;

import android.hardware.Camera;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import com.snippets.tao.androidsnippets.R;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao He on 2019-10-23.
 * hetaoof@gmail.com
 */
public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener {

    private static final int MY_PERMISSIONS_REQUEST = 1001;
    private static final String TAG = "htdebug";

    private static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();
    private MediaExtractor mediaExtractor;
    private MediaMuxer mediaMuxer;

    /**
     * 需要申请的运行时权限
     */
    private String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /**
     * 被用户拒绝的权限列表
     */
    private List<String> mPermissionList = new ArrayList<>();

    private SurfaceView mSurfaceView;
    private TextureView mTextureView;
    private Camera mCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        checkPermissions();

        //mTextureView = findViewById(R.id.tv_preview);
        //mTextureView.setSurfaceTextureListener(this);


        mSurfaceView = findViewById(R.id.sv_preview);
        mSurfaceView.getHolder().addCallback(this);


        mCamera = Camera.open(1);
        mCamera.setDisplayOrientation(90);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, permissions[i] + " 权限被用户禁止！");
                }
            }
            // 运行时权限的申请不是本demo的重点，所以不再做更多的处理，请同意权限申请。


        }
    }

    private void checkPermissions() {
        // Marshmallow开始才用申请运行时权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                        PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions[i]);
                }
            }
            if (!mPermissionList.isEmpty()) {
                String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);
                ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS_REQUEST);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.release();
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        /*
        try {
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
        } catch (IOException e) {

            e.printStackTrace();
        }
        */

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    private void process() throws IOException {
        mediaExtractor = new MediaExtractor();
        mediaExtractor.setDataSource(SDCARD_PATH + "/a.mp4");

        int videoTrackIndex = -1;
        int frameRate = 0;

        for(int i=0; i<mediaExtractor.getTrackCount(); i++) {
            MediaFormat format = mediaExtractor.getTrackFormat(i);
            String mime = format.getString(MediaFormat.KEY_MIME);
            if (!mime.startsWith("video/")) {
                continue;
            }

            frameRate = format.getInteger(MediaFormat.KEY_FRAME_RATE);
            mediaExtractor.selectTrack(i);
            mediaMuxer = new MediaMuxer(SDCARD_PATH + "/output.mp4", MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
            videoTrackIndex = mediaMuxer.addTrack(format);
            mediaMuxer.start();
        }

        if (mediaMuxer == null)
            return;

        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        info.presentationTimeUs = 0;
        ByteBuffer buffer = ByteBuffer.allocate(500 * 1024);
        int sampleSize = 0;
        while((sampleSize = mediaExtractor.readSampleData(buffer, 0)) > 0) {
            info.offset = 0;
            info.size = sampleSize;
            info.flags = MediaCodec.BUFFER_FLAG_SYNC_FRAME;
            info.presentationTimeUs += 1000 * 1000 / frameRate;
            mediaMuxer.writeSampleData(videoTrackIndex, buffer, info);
            mediaExtractor.advance();
        }

        mediaExtractor.release();

        mediaMuxer.stop();
        mediaMuxer.release();
    }

}
