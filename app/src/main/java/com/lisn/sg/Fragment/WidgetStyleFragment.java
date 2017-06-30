package com.lisn.sg.Fragment;


import android.view.WindowManager;

import com.lisn.sg.R;

/**
 * 控件样式修改
 */
public class WidgetStyleFragment extends BaseFragment {



    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_widget_style;
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle("控件样式修改");
        //让你的app无法使用截图
        mContext.getWindow().addFlags(WindowManager.LayoutParams. FLAG_SECURE);
    }
}
