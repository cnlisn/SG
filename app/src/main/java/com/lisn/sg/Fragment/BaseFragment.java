package com.lisn.sg.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lisn.sg.HomeActivity;

/**
 * Created by admin on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment {


    public String ToolbarTitle;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    private boolean mIsPrepare; //mIsPrepare 表示是否已经加载view，该属性主要用于懒加载
    public FragmentActivity mContext; //贴附的activity
    private View mRootView; //根view

    public void setToolbarTitle(String toolbarTitle) {
        ToolbarTitle = toolbarTitle;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResouceId(), container, false);
        initData(getArguments());

        initView();

        mIsPrepare = true;

        onLazyLoad();

        setListener();

        return mRootView;
    }

    /**
     * 初始化数据
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    private void initData(Bundle arguments) {

    }

    /**
     * 初始化View
     */
    protected  void initView() {

    }

    /**
     * 各种监听事件的统一设置
     */
    protected  void setListener() {

    }

    /**
     * 懒加载，仅当用户可见且view初始化结束后才会执行
     * 懒加载数据，在oncreatview方法中调用可以直接理解为加载数据，方法中可以进行view的操作
     */
    protected  void onLazyLoad() {

    }

    /**
     * 设置根布局资源id
     * @return
     */
    protected abstract int setLayoutResouceId();

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(int id)
    {
        if (mRootView == null)
        {
            return null;
        }

        return (T) mRootView.findViewById(id);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mIsVisible = isVisibleToUser;
//        Log.e("---", "setUserVisibleHint: isVisibleToUser="+isVisibleToUser );
        if (isVisibleToUser)
        {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    private void onVisibleToUser() {
        if (mIsPrepare && mIsVisible)
        {
            onLazyLoad();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(ToolbarTitle))
            ((HomeActivity)mContext).SetToolbarTitle(ToolbarTitle);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {   // 不在最前端界面显示
        } else {        // 重新显示到最前端中
            if (!TextUtils.isEmpty(ToolbarTitle)) {
                ((HomeActivity)mContext).SetToolbarTitle(ToolbarTitle);
            }
        }
    }

}
