package com.lisn.sg.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lisn.sg.HomeActivity;
import com.lisn.sg.Popup.PopupWindowActivity;
import com.lisn.sg.R;
import com.lisn.sg.Utils.LsUtils;

/**
 * Created by admin on 2017/6/19.
 */

public class PopFragment extends Fragment {
    private HomeActivity mContext;
    private String TAG = "PopFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = (HomeActivity) getActivity();
        Log.e(TAG, "onHiddenChanged: onCreateView");
        return inflater.inflate(R.layout.fragment_pop, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {// 不在最前端界面显示
            Log.e(TAG, "onHiddenChanged: 不在最前端界面显示");
        } else {// 重新显示到最前端中
            mContext.SetToolbarTitle("PopupWind");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mContext.SetToolbarTitle("PopupWind");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_popup = (TextView) view.findViewById(R.id.tv_popup);
        tv_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LsUtils.StarteActivity(mContext, PopupWindowActivity.class);
            }
        });
    }
}
