package com.lisn.sg.Fragment;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lisn.sg.HomeActivity;
import com.lisn.sg.R;
import com.lisn.sg.Utils.LogUtils;
import com.lisn.sg.Utils.ScreenInfo;
import com.lisn.sg.View.SelectDateTime.DateUtils;
import com.lisn.sg.View.SelectDateTime.WheelMain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 选择日期时间控件
 */
public class SelectDateTimeFragment extends Fragment {

    private TextView tv_selectDataTime;
    private WheelMain wheelMainDate;
    private TextView tv_center;
    private HomeActivity mContext;

    public SelectDateTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = (HomeActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_select_date_time, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mContext.SetToolbarTitle("自定义日期时间选择控件");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {// 不在最前端界面显示
        } else {// 重新显示到最前端中
            mContext.SetToolbarTitle("自定义日期时间选择控件");
        }
    }

    private void initViews(View view) {
        tv_selectDataTime = (TextView) view.findViewById(R.id.tv_selectDataTime);
        tv_center = (TextView) view.findViewById(R.id.tv_center);
        tv_selectDataTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottoPopupWindow();
            }
        });
    }

    public void showBottoPopupWindow() {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        View menuView = LayoutInflater.from(mContext).inflate(R.layout.select_date_time_popup_window, null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int) (width * 0.8),
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(mContext);
        wheelMainDate = new WheelMain(menuView, true);
        wheelMainDate.screenheight = screenInfoDate.getHeight();

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelMainDate.initDateTimePicker(year, month, day, hours, minute);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(tv_center, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.6f);
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancle);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = (TextView) menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText("选择起始时间");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String beginTime = wheelMainDate.getTime().toString();
                String text = DateUtils.formateStringH(beginTime, DateUtils.yyyyMMddHHmm);
                tv_selectDataTime.setText(text);
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
                LogUtils.init("---", LogUtils.LEVEL_E)
                        .append("打印等级为ERROR的一条日志")
                        .append("text", text)
                        .print();
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        mContext.getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

}
