package com.lisn.sg.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.lisn.sg.MainActivity;
import com.lisn.sg.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private ArrayList list;
    private Context T;
    private List<ResolveInfo> mApps;
    private ResolveInfo info;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        T=getActivity();

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.imagebutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = getActivity().getPackageName();

                Intent intent = new Intent();
                ComponentName comp = new ComponentName(
                        packageName,
                        packageName+".MainActivity");
                intent.setComponent(comp);
//                intent.setAction("android.intent.action.MAIN");
                startActivity(intent);
            }
        });

        initView(view);

        return view;
    }

    private void initView(View view) {
        Button btn = (Button) view.findViewById(R.id.btn);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        list = new ArrayList<String>();
        getList();
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(mAdapter);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getList() {
        list.clear();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        String packageName = getActivity().getPackageName();
//        mainIntent.setPackage(packageName);
        mApps = T.getPackageManager().queryIntentActivities(mainIntent, 0);
        for (int i = 0; i < mApps.size(); i++) {
            info = mApps.get(i);
            String appLabel = info.loadLabel(T.getPackageManager()).toString();
            String packagename = info.activityInfo.packageName;
            String appname = info.activityInfo.name;
            list.add("appLabel: " + appLabel + " || \n packagename: " + packagename
                    + " || \n appname: " + appname);
        }
    }

}
