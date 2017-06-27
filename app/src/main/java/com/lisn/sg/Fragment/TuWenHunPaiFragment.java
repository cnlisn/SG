package com.lisn.sg.Fragment;


import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lisn.androidspan.AndroidSpan;
import com.lisn.androidspan.SpanOptions;
import com.lisn.sg.R;

/**
 * 图文混排
 */
public class TuWenHunPaiFragment extends BaseFragment {

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_tu_wen_hun_pai;
    }

    @Override
    protected void initView() {
        setToolbarTitle("图文混排");
        TextView tv_content = findViewById(R.id.tv_content);
        AndroidSpan androidSpan = new AndroidSpan()
                .drawBulletSpan("bullet", 40, Color.RED)
                .drawRelativeSize("我要变大", 2.0f)
                .drawUnderlineSpan("测试文本")
                .drawForegroundColor("红色的文本", Color.RED)
                .drawImageSpan("来个图片试试", mContext, R.mipmap.ic_launcher)
                .drawURLSpan("tel:18888888888")
                .drawCommonSpan("没有效果")
                .drawStrikethroughSpan("删除线")
                .drawURLSpan("http://www.baidu.com")
                .drawStyleSpan("粗体", android.graphics.Typeface.BOLD)
                .drawSubscriptSpan("下标")
                .drawSuperscriptSpan("上标")
                .drawMaskFilterSpan("模糊效果", 1f, BlurMaskFilter.Blur.NORMAL)
                .drawWithOptions("点击效果", new SpanOptions().addSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "哎呦，疼", Toast.LENGTH_LONG).show();
                    }
                }))
                .drawWithOptions("综合效果", new SpanOptions().addBackgroundColorSpan(Color.GREEN).addUnderlineSpan())
                .drawWithOptions("tel:18888888888", new SpanOptions().addForegroundColor(Color.BLUE));

        tv_content.setText(androidSpan.getSpanText());
        tv_content.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
