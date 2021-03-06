package com.lisn.sg.Utils;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.lisn.sg.App.BaseApp;

/**
 * Created by admin on 2017/6/19.
 */

public class LsUtils {
    private static Context mContext;

    public LsUtils(Context context) {
        this.mContext = context;
    }

    /**
     * 启动一个Activity
     * @param context context
     * @param cls     目标Class
     */
    public static void StarteActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 跳转到外部浏览器
     * @param context Context
     * @param url     url
     */
    public static void startOutsideBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * 测量View的宽高
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    private static Toast toast;
    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
    /*Toast工具*/
    public static void showToast(String s) {
        showToast(BaseApp.getInstance(),s);
    }

    public static int dip2px(float dip) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
