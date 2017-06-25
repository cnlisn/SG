package com.lisn.sg.Fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lisn.sg.Adapter.AppInfoAdapter;
import com.lisn.sg.Bean.AppInfo;
import com.lisn.sg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private ArrayList AppInfoList;
    private Context T;
    private List<ResolveInfo> mApps;
    private ResolveInfo info;
    private AppInfoAdapter.OnAppInfoClickListener listener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        T = getActivity();

//        ImageButton imageButton = (ImageButton) view.findViewById(R.id.imagebutton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String packageName = getActivity().getPackageName();
//
//                Intent intent = new Intent();
//                ComponentName comp = new ComponentName(
//                        packageName,
//                        packageName+".MainActivity");
//                intent.setComponent(comp);
////                intent.setAction("android.intent.action.MAIN");
//                startActivity(intent);
//            }
//        });

        initView(view);

        return view;
    }

    private void initView(View view) {
        Button btn = (Button) view.findViewById(R.id.btn);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        AppInfoList = new ArrayList<AppInfo>();
        getList();
        AppInfoAdapter mAdapter = new AppInfoAdapter(AppInfoList, listView) {
            @Override
            public View getConvertView(AppInfo appInfo, int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    convertView = View.inflate(T, R.layout.item_appinfo, null);
                    viewHolder = new ViewHolder();
                    viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                    viewHolder.appName = (TextView) convertView.findViewById(R.id.appName);
                    viewHolder.packgeName = (TextView) convertView.findViewById(R.id.packgeName);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.icon.setBackground(appInfo.LoadIcon);
                viewHolder.appName.setText(appInfo.AppLabel);
                viewHolder.packgeName.setText(appInfo.PackageName);
                return convertView;
            }

        };

        //点击APPInfo条目打开对应的应用
        listener = new AppInfoAdapter.OnAppInfoClickListener() {
            @Override
            public void onClick(AppInfo appInfo, int posation) {
                Intent intent = new Intent();
                ComponentName comp = new ComponentName(
                        appInfo.PackageName,
                        appInfo.ActionName);
                intent.setComponent(comp);
//                intent.setAction("android.intent.action.MAIN");
                startActivity(intent);
           }
        };
        mAdapter.setOnAppInfoClickListener(listener);
        listView.setAdapter(mAdapter);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }


    private class ViewHolder {
        ImageView icon;
        TextView appName;
        TextView packgeName;
    }

    //获取系统已安装应用信息并存入list集合
    private void getList() {
        AppInfoList.clear();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        String packageName = getActivity().getPackageName();
//        mainIntent.setPackage(packageName);
        mApps = T.getPackageManager().queryIntentActivities(mainIntent, 0);
        AppInfo appInfo;
        for (int i = 0; i < mApps.size(); i++) {
            info = mApps.get(i);
            String appLabel = info.loadLabel(T.getPackageManager()).toString();
            String packageName = info.activityInfo.packageName;
            String actionName = info.activityInfo.name;
            Drawable loadIcon = info.activityInfo.loadIcon(T.getPackageManager());
            appInfo = new AppInfo(appLabel, packageName, actionName, loadIcon);
            AppInfoList.add(appInfo);
        }
    }

}
