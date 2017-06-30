package com.lisn.sg.Fragment;


import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;


import com.lisn.scrollviewtabindicator.ScrollViewTabIndicator;
import com.lisn.sg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ScrollViewIndicator 垂直滑动的ViewPagerIndicator
 */
public class ScrollViewIndicatorFragment extends BaseFragment implements NestedScrollView.OnScrollChangeListener {

    private NestedScrollView mSv;
    private ScrollViewTabIndicator mTab;
    private ScrollViewTabIndicator mTab2;
    private int[] mTabMiddleLocation = new int[2];
    private int[] mTabTopLocation = new int[2];

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_scroll_view_indicator;
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle("ScrollViewIndicator");
        mSv = (NestedScrollView) findViewById(R.id.sv);
        mTab = (ScrollViewTabIndicator) findViewById(R.id.tab);
        mTab2 = (ScrollViewTabIndicator) findViewById(R.id.tab2);

        View view1 = findViewById(R.id.tv_1);
        View view2 = findViewById(R.id.tv_2);
        View view3 = findViewById(R.id.tv_3);
        View view4 = findViewById(R.id.tv_4);
        List<String> names = new ArrayList<>();
        names.add("详情");
        names.add("评论");
        names.add("须知");
        names.add("更多");
        List<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        mTab.setScrollView(mSv, this, names, views);
        //将mTab本身作为参数传入mTab2已达到同步状态
        mTab2.setScrollView(mSv, mTab, names, views);

        findViewById(R.id.tv_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        setVisibleAndGone();
    }

    private void setVisibleAndGone() {
        mTab2.getLocationOnScreen(mTabMiddleLocation);
        mTab.getLocationOnScreen(mTabTopLocation);
        Log.e("---", "setVisibleAndGone: mTab2= "+mTabMiddleLocation[1]+" mTab="+mTabTopLocation[1] );
        if (mTabMiddleLocation[1] <= mTabTopLocation[1]) {
            mTab.setVisibility(View.VISIBLE);
            mTab2.setVisibility(View.INVISIBLE);
        } else {
            mTab.setVisibility(View.INVISIBLE);
            mTab2.setVisibility(View.VISIBLE);
        }
    }
}
