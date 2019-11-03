package com.snippets.tao.androidsnippets.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Tao He on 16-7-28.
 * Email: hetaoof@gmail.com
 */
public class ProcessUtils {

    private static String mProcessName;
    private static Context mContext;
    private static ProcessUtils mInstance;

    private ProcessUtils(){}

    public static ProcessUtils getInstance(Context context){
        if(mInstance == null) {
            mContext = context;
            mInstance = new ProcessUtils();
        }
        return mInstance;
    }
    public static String getProcessName(){
        if (mProcessName != null) {
            return mProcessName;
        }

        File cmdFile = new File("/proc/self/cmdline");

        if ( cmdFile.exists() && !cmdFile.isDirectory() ){
            BufferedReader reader = null;
            try{
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmdFile)));
                String procName = reader.readLine();

                if (!TextUtils.isEmpty(procName) ) {
                    mProcessName = procName.trim();
                    return mProcessName;
                }
            }catch(Exception e){
            }finally{
                if(reader != null){
                    try {
                        reader.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
        mProcessName = mContext.getApplicationInfo().processName;
        return mProcessName;
    }
}
