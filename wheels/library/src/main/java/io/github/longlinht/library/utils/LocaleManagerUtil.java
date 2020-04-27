package io.github.longlinht.library.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import java.util.Locale;


public class LocaleManagerUtil {
    //语言设置
    public static final String LANGUAGE_LOCALE = "language_locale";

    public static final String LOCALE_DEFAULT = "default";
    public static final String LOCALE_ZH = "zh";
    public static final String LOCALE_EN = "en";
    public static final String LOCALE_IN = "in";

    public static Context updateLocale(Context context) {
        //需要切换语言再放开吧
//        Locale locale = getLanguageLocale(context);
//        if (locale == null) {
//            return context;
//        }
//        Locale.setDefault(locale);
//
//        Resources res = context.getResources();
//        Configuration config = new Configuration(res.getConfiguration());
//        if (Build.VERSION.SDK_INT >= 17) {
//            config.setLocale(locale);
//            context = context.createConfigurationContext(config);
//        } else {
//            config.locale = locale;
//            res.updateConfiguration(config, res.getDisplayMetrics());
//        }
        return context;
    }

    private static Locale getLanguageLocale(Context context) {
        switch (context.getSharedPreferences("common", Context.MODE_PRIVATE).getString(LANGUAGE_LOCALE, "")) {
            case LOCALE_ZH:
                return Locale.CHINA;
            case LOCALE_EN:
                return Locale.ENGLISH;
            case LOCALE_DEFAULT:
                return null;
            default: {
                //注：不带country会影响小米广告，要避免
                return new Locale("in", "ID");
            }
        }
    }


    private static Locale getSysLanguageLocale(Context context) {
        if (context == null)
            return null;
        Resources resources = context.getApplicationContext().getResources();

        Locale localeSystem = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            localeSystem = resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            localeSystem = resources.getSystem().getConfiguration().locale;
        }

        if (localeSystem != null) {
            return (Locale) localeSystem.clone();
        }
        return null;
    }


    //for AppControl application
    public static void setApplicationLanguage(Context context) {
        setApplicationLanguage(context, false);
    }

    //for AppControl application
    public static void setApplicationLanguage(Context context, boolean forceFollowSystem) {
        if (context == null)
            return;

        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = getLanguageLocale(context);
        if (locale == null && !forceFollowSystem) {
            return;
        }
        if (locale == null) {
            locale = getSysLanguageLocale(context);
        }

        if (locale == null) {
            return;
        }


        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(config);
            Locale.setDefault(locale);
        }
        resources.updateConfiguration(config, dm);
    }


}
