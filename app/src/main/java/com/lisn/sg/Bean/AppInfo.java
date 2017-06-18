package com.lisn.sg.Bean;

import android.graphics.drawable.Drawable;

/**
 * ****************************
 * 项目名称：SG
 * 创建人：lisn on 2017/6/18 22:07
 * 邮箱：cnlishan@163.com
 * 创建时间：2017/6/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 * ****************************
 * 版权所有违法必究
 */
public class AppInfo {
    public String AppLabel;    //app名称
    public String PackageName; //app包名
    public String ActionName;  //启动Activity名称
    public Drawable LoadIcon;  //app图标

    public AppInfo(String appLabel, String packageName, String actionName, Drawable loadIcon) {
        this.AppLabel = appLabel;
        this.ActionName = actionName;
        this.PackageName = packageName;
        this.LoadIcon = loadIcon;
    }
}
