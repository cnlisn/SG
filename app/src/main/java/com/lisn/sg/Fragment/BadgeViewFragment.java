package com.lisn.sg.Fragment;


import android.view.View;
import android.widget.TextView;

import com.lisn.sg.BadgeView.BadgeListViewActivity;
import com.lisn.sg.BadgeView.BaseBadgeViewActivity;
import com.lisn.sg.R;
import com.lisn.sg.Utils.LsUtils;

/**
 * BadgeView 小红点
 */
public class BadgeViewFragment extends BaseFragment implements View.OnClickListener {


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
        tv_base.setOnClickListener(this);
        tv_ListView.setOnClickListener(this);
        tv_RecyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_base) {
            LsUtils.StarteActivity(mContext, BaseBadgeViewActivity.class);
        } else if (v.getId() == R.id.tv_ListView) {
            LsUtils.StarteActivity(mContext, BadgeListViewActivity.class);
        } else if (v.getId() == R.id.tv_RecyclerView) {
            LsUtils.StarteActivity(mContext, BadgeListViewActivity.class);
        }

    }
}
