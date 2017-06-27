package com.lisn.sg.Utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * log日志打印工具
 * <p>
 * 使用方法：
 * LogUtils.init(TAG, LogUtils.LEVEL_E)
 * .append("打印等级为ERROR的一条日志")
 * .append("key", "value")
 * .print();
 */
public class LogUtils {
    private String message = null;
    private String tag = null;
    public static int LEVEL_I = 0;
    public static int LEVEL_D = 1;
    public static int LEVEL_W = 2;
    public static int LEVEL_E = 3;
    private int levelCode = LEVEL_I;

    /**
     * 设置TAG和日志等级
     * @param tag       设置TAG标签，方便过滤。为空则默认使用LogUtils的类名作为打印的TAG。
     * @param levelCode 要打印的日志等级码，{@link #LEVEL_I}、{@link #LEVEL_D}、{@link #LEVEL_W}、{@link #LEVEL_E}
     * @return
     */
    public static LogUtils init(String tag, int levelCode) {
        LogUtils instance = new LogUtils();
        // 初始化tag
        if (TextUtils.isEmpty(tag))
            tag = LogUtils.class.getName();
        instance.tag = tag;
        // 初始化message
        if (instance.message == null)
            instance.message = "";
        // 初始化等级
        if (levelCode < LEVEL_I)
            levelCode = LEVEL_I;
        else if (levelCode > LEVEL_E)
            levelCode = LEVEL_E;
        instance.levelCode = levelCode;

        return instance;
    }

    /**
     * 添加一个key : value形式的log
     * @param key   键
     * @param value 值
     * @return
     */
    public LogUtils append(String key, String value) {
        message += " " + key + " : " + value + " ,";
        return this;
    }

    /**
     * 添加一个仅有值的log
     * @param value 值
     * @return
     */
    public LogUtils append(String value) {
        message += " " + value + " ,";
        return this;
    }

    /**
     * log打印终止方法
     */
    public void print() {
        message = message.substring(0, message.lastIndexOf(",")) + "。";
        Log.e(tag, message);
    }
}