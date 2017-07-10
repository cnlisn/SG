package com.lisn.sg.Fragment;


import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.lisn.circleprogressview.CircleProgressView;
import com.lisn.sg.R;
import com.lisn.sg.Utils.SLog;
import com.lisn.sg.Utils.ToastUtil;

/**
 * 自定义圆形进度条
 */
public class CircleProgressViewFragment extends BaseFragment {


    private CircleProgressView mCircleProgressView;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_circle_progress_view;
    }

    @Override
    protected void initView() {
        setToolbarTitle("自定义圆形进度条");

        mCircleProgressView = (CircleProgressView) findViewById(R.id.circle_view);
        Button bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进度条从0到100
                ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
                animator.setDuration(4000);
                animator.setInterpolator(new LinearInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float current = (float) animation.getAnimatedValue();
                        mCircleProgressView.setmCurrent((int) current);
                        SLog.e("currrnt="+current);
                    }
                });
                animator.start();
                ToastUtil.showToast(mContext,R.layout.test_toast_layout);
                mCircleProgressView.setOnLoadingCompleteListener(new CircleProgressView.OnLoadingCompleteListener() {
                    @Override
                    public void complete() {
                        Toast.makeText(mContext, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
