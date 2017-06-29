package com.lisn.sg.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lisn.sg.BadgeView.BadgeListViewActivity;
import com.lisn.sg.BadgeView.BaseBadgeViewActivity;
import com.lisn.sg.Bean.AppInfo;
import com.lisn.sg.R;
import com.lisn.sg.Utils.LsUtils;

import java.util.List;

/**
 * BadgeView 小红点
 */
public class BadgeViewFragment extends BaseFragment implements View.OnClickListener {


    private EditText et_conut;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_badge_view;
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle("BadgeView");
        TextView tv_base = (TextView) findViewById(R.id.tv_base);
        TextView tv_ListView = (TextView) findViewById(R.id.tv_ListView);
        TextView tv_RecyclerView = (TextView) findViewById(R.id.tv_RecyclerView);
        TextView tv_sanxing = (TextView) findViewById(R.id.tv_sanxing);
        TextView tv_sanxing_clear = (TextView) findViewById(R.id.tv_sanxing_clear);
        TextView tv_sanxing_add_all = (TextView) findViewById(R.id.tv_sanxing_add_all);
        TextView tv_sanxing_clear_all = (TextView) findViewById(R.id.tv_sanxing_clear_all);
        et_conut = (EditText) findViewById(R.id.et_conut);
        tv_base.setOnClickListener(this);
        tv_ListView.setOnClickListener(this);
        tv_RecyclerView.setOnClickListener(this);
        tv_sanxing.setOnClickListener(this);
        tv_sanxing_clear.setOnClickListener(this);
        tv_sanxing_add_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_base) {
            LsUtils.StarteActivity(mContext, BaseBadgeViewActivity.class);
        } else if (v.getId() == R.id.tv_ListView) {
            LsUtils.StarteActivity(mContext, BadgeListViewActivity.class);
        } else if (v.getId() == R.id.tv_RecyclerView) {
            LsUtils.StarteActivity(mContext, BadgeListViewActivity.class);
        }else if (v.getId() == R.id.tv_sanxing) {
            String conut = et_conut.getText().toString().trim();
            int badgeCount = 0;
            try {
                badgeCount = Integer.parseInt(conut);
            } catch (NumberFormatException e) {
                LsUtils.showToast("Error input");
            }
            setBadgeOfSumsung(mContext, badgeCount);
        }else if (v.getId() == R.id.tv_sanxing_clear) {
            setBadgeOfSumsung(mContext, 0);
        }else if (v.getId() == R.id.tv_sanxing_clear_all) {

            getList(mContext, 0);
        }else if (v.getId() == R.id.tv_sanxing_add_all) {
            String conut = et_conut.getText().toString().trim();
            int badgeCount = 0;
            try {
                badgeCount = Integer.parseInt(conut);
            } catch (NumberFormatException e) {
                LsUtils.showToast("Error input");
            }
            getList(mContext, badgeCount);
        }

    }

    /**
     * 设置三星的Badge
     * @param context context
     * @param count count
     */
    private void setBadgeOfSumsung(Context context, int count) {
        // 获取你当前的应用
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    private  String getLauncherClassName(Context context) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        String appPackageName = getActivity().getPackageName();
        mainIntent.setPackage(appPackageName);
        List<ResolveInfo> mApps = context.getPackageManager().queryIntentActivities(mainIntent, 0);
        AppInfo appInfo;
        for (int i = 0; i < mApps.size(); i++) {
            ResolveInfo info = mApps.get(i);
            String appLabel = info.loadLabel(context.getPackageManager()).toString();
            String packageName = info.activityInfo.packageName;
            String actionName = info.activityInfo.name;
            Drawable loadIcon = info.activityInfo.loadIcon(context.getPackageManager());

            return actionName;

        }
        return appPackageName;
    }

    //获取系统已安装应用信息并存入list集合
    private void getList(Context context,int count) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> mApps = context.getPackageManager().queryIntentActivities(mainIntent, 0);
        for (int i = 0; i < mApps.size(); i++) {
            ResolveInfo info = mApps.get(i);
            String appLabel = info.loadLabel(context.getPackageManager()).toString();
            String packageName = info.activityInfo.packageName;
            String actionName = info.activityInfo.name;
            Drawable loadIcon = info.activityInfo.loadIcon(context.getPackageManager());

            if (actionName == null) {
                return;
            }else {
                Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
                intent.putExtra("badge_count", count);
                intent.putExtra("badge_count_package_name", packageName);
                intent.putExtra("badge_count_class_name", actionName);
                context.sendBroadcast(intent);
            }
        }
    }
}
