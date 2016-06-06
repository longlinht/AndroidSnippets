package com.snippets.tao.androidsnippets;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Tao He on 16-6-6.
 * Email: hetaoof@gmail.com
 */
public class SkypeCallService extends Service{

    private static final String TAG = SkypeCallService.class.getSimpleName();
    public static final String SKYPE_CALL_ACTION = "com.snippets.tao.androidsnippets.SKYPE_CALL_ACTION";
    //private final static String CALLING_NUMBER = "15110062539";
    private final static String CALLING_NUMBER = "13439904939";
    private final static int DAY_OF_HOUR = 02;
    private final static int HOUR_OF_MIN = 00;
    private final static int MIN_OF_SECON = 00;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "  onCreate");
        super.onCreate();
        addAliveAlarm();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        onStartCommand(intent, 0, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "   onStartCommand");

        if (intent == null) return START_STICKY;

        if (intent.getAction() == null) return START_STICKY;

        if (intent.getAction().equals(SKYPE_CALL_ACTION)) {
            Intent sky = new Intent("android.intent.action.CALL_PRIVILEGED");
            sky.setClassName("com.skype.polaris",
                    "com.skype.raider.Main");
            sky.setData(Uri.parse("tel:" + CALLING_NUMBER));
            sky.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(sky);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "  onDestroy");
        super.onDestroy();
        System.exit(0);
    }

    private void addAliveAlarm() {
        Log.e(TAG, " addAliveAlarm");
        Intent i = new Intent(SKYPE_CALL_ACTION);
        i.setClass(this, SkypeCallService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmMgr.cancel(pi);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000,
                AlarmManager.INTERVAL_DAY, pi);
    }

    private static long getTriggerTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, DAY_OF_HOUR);
        calendar.set(Calendar.MINUTE, HOUR_OF_MIN);
        calendar.set(Calendar.SECOND, MIN_OF_SECON);

        Log.e(TAG, "trigger time: " + calendar.getTime().toString());

        return calendar.getTimeInMillis();
    }
}
