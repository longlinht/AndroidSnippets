package io.github.longlinht.library.utils;

import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;

import io.github.longlinht.library.log.Logger;

import java.io.File;

import io.github.longlinht.library.R;

import static io.github.longlinht.library.BuildConfig.DEBUG;

/**
 * Created by Tao He on 18-7-19.
 * hetaoof@gmail.com
 */
public class AudioRecorder {
    private String outputFile;
    private MediaRecorder mediaRecorder;
    private boolean error = false;
    private long startTime;
    private long endTime;

    public AudioRecorder() {
        initRecorder();
    }

    public String getFilePath() {
        return outputFile;
    }

    public void setFilePath(String dir, String filePath) {
        File path = new File(dir);
        if (path != null && !path.exists()) {
            path.mkdirs();
        }
        outputFile = dir + File.separator + filePath;
        File file = new File(outputFile);
        if (file != null && file.exists()) {
            file.delete();
        }
        mediaRecorder.setOutputFile(outputFile);
    }

    private void initRecorder() {

        // 如果没有SD卡，不能初始化
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ToastUtils.showLong(GlobalContext.getApplication().getString(R.string.sdcard_unavailable));
            return;
        }

        mediaRecorder = new MediaRecorder();
        /* 录制的声音的来源 */
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        /* 声音的输出格式（必须在设置声音的编码格式之前设置） */
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        /* 设置声音的编码格式 */
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
    }

    public void record() {
        if (DEBUG) {
            Logger.d("record_start");
        }
        startTime = System.currentTimeMillis();
        if (mediaRecorder != null) {
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (Exception e) {
                error = true;
                e.printStackTrace();
            }
        }
    }

    public void close() {
        endTime = System.currentTimeMillis();
        if (DEBUG) {
            Logger.d("record_close");
        }
        if (DEBUG) {
            Logger.d(endTime - startTime + "::time");
        }
        if (!error && mediaRecorder != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        mediaRecorder.stop();
                        mediaRecorder.reset();
                        mediaRecorder.release();
                        mediaRecorder = null;
                    } catch (Exception e) {

                    }
                }
            }, 300);

        }
    }

    /**
     * 有的手机录音时需要手动授权，影响录音 需要在录音前先获得录音权限
     */
    public void testRecord() {
        //setFilePath(DEFAULT_AUDIO_TEMP_PATH);
        record();
        close();
    }

    /**
     * 获得录音时间(s)
     *
     * @return
     */
    public long getLength() {
        return endTime - startTime;
    }

    public void delete() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                File file = new File(outputFile);
                if (file != null && file.exists()) {
                    file.delete();
                }
            }
        }, 500);
    }

}
