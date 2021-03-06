package com.lisn.sg.App;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by admin on 2017/6/26.
 */

public class BaseApp extends Application {
    public static BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;// 赋值
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 获取上下文
     * @return
     */
    public static Context getInstance() {
        return instance;
    }
}
