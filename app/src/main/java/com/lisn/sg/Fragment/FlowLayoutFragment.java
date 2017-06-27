package com.lisn.sg.Fragment;


import android.app.FragmentBreadCrumbs;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lisn.sg.FlowLayout.DrawableUtils;
import com.lisn.sg.FlowLayout.FlowLayout;
import com.lisn.sg.R;
import com.lisn.sg.Utils.LsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 */
public class FlowLayoutFragment extends BaseFragment {

    private String[] data = new String[]{"全部", "这是", "测试标签",
            "这是测试标签", "FlowLayout", "衣服", "鞋子",
            "春", "夏", "深秋", "寒冬",
            "测一下看看效果如何", "心情还不错哦", "这是测试标签", "这是测试标签",
            "这是测试标签", "受益匪浅啊", "123456789", "电话号码"};
    private FlowLayout flowLayout;
    private int padding;
    private List<TextView> tvData = new ArrayList<>();
    private TextView selectContent;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_flow_layout;
    }

    @Override
    protected void initData(Bundle arguments) {
        padding = mLsUtils.dip2px(5);
        final int CornerRadius = mLsUtils.dip2px(30); //圆角半径
        final int color = 0xffcecece;// 按下后偏白的背景色
        for (final String s : data) {
            final TextView textView = new TextView(mContext);
            textView.setText(s);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setPadding(padding, padding, padding, padding);
            textView.setGravity(Gravity.CENTER);
            textView.setTag(false);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StateListDrawable selector;
                    boolean tag = (boolean) textView.getTag();
                    if (tag) {
                        textView.setTag(false);
                    } else {
                        mLsUtils.showToast(s);
                        textView.setTag(true);
                    }
                    if (!tag) {
                        textView.setTextColor(Color.WHITE);
                        selector = DrawableUtils.getSelector(false, Color.parseColor("#2c90d7"), color, CornerRadius);
                    } else {
                        textView.setTextColor(Color.DKGRAY);
                        selector = DrawableUtils.getSelector(true, Color.WHITE, color, CornerRadius);
                    }
                    textView.setBackgroundDrawable(selector);

                    getSelectContent();
                }
            });
            StateListDrawable selector = DrawableUtils.getSelector(true, Color.WHITE, color, CornerRadius);
            textView.setTextColor(Color.DKGRAY);
            textView.setBackgroundDrawable(selector);
            tvData.add(textView);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle("流式布局");
        flowLayout = findViewById(R.id.fl);
        flowLayout.setPadding(padding, padding, padding, padding);
        for (TextView textView : tvData) {
            flowLayout.addView(textView);
        }

        selectContent = findViewById(R.id.tv_SelectContent);
    }

    /**
     * 获取选择内容
     */
    private void getSelectContent() {
        StringBuilder sb = new StringBuilder();
        for (TextView textView : tvData) {
            boolean tag = (boolean) textView.getTag();
            if (tag) {
                sb.append(textView.getText().toString().trim() + "、");
            }
        }
        selectContent.setText("");
            String s = sb.toString();
        if (s.length()>1) {
            int lastIndexOf = s.lastIndexOf("、");
            String content = s.substring(0, lastIndexOf);
            selectContent.setText(content);
        }
    }

    /**
     * 添加选择内容
     * @param s
     */
    private void AddSelectContent(String s){
        selectContent.append(s+"、");
    }

    /**
     * 删除选择内容
     * @param s
     */
    private void RemoveSelectContent(String s){
        String content = selectContent.getText().toString();
        int indexOf = content.indexOf(s);
        int lastIndexOf = indexOf + s.length()+1;
        String substring = content.substring(0, indexOf + 1);
        // TODO: 2017/6/27  
        selectContent.append(s+"、");
    }
}
