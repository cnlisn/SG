package com.lisn.sg.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lisn.sg.Bean.AppInfo;


import java.util.List;

/**
 * ****************************
 * 项目名称：SG
 * 创建人：lisn on 2017/6/18 22:27
 * 邮箱：cnlishan@163.com
 * 创建时间：2017/6/18.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 * ****************************
 * 版权所有违法必究
 */
public abstract class AppInfoAdapter<T> extends BaseAdapter {

    private final List<T> Datas;
    private final ListView listView;
    private OnAppInfoClickListener onAppInfoClickListener;

    public interface OnAppInfoClickListener {
        void onClick(AppInfo t, int posation);
    }

    public void setOnAppInfoClickListener(OnAppInfoClickListener listener) {
        this.onAppInfoClickListener = listener;
    }

    public AppInfoAdapter(final List<T> Datas, ListView listView) {
        this.Datas = Datas;
        this.listView = listView;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (onAppInfoClickListener != null) {
                    onAppInfoClickListener.onClick((AppInfo) Datas.get(i), i);
                }
            }
        });

    }

    @Override
    public int getCount() {
        return Datas.size();
    }

    @Override
    public Object getItem(int i) {
        return Datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T t = Datas.get(position);
        View view = getConvertView(t, position, convertView, parent);
        return view;
    }

    public abstract View getConvertView(T t, int position,
                                        View convertView, ViewGroup parent);
}
