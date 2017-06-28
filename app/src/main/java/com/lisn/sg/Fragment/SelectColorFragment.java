package com.lisn.sg.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

import com.lisn.sg.R;

/**
 * 选择颜色
 */
public class SelectColorFragment extends BaseFragment {



    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_select_color;
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle("颜色选择Alert");
        final ImageButton imageButton = (ImageButton) findViewById(R.id.ib_selectColor);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectorColor(new OnColorClickListener() {
                    @Override
                    public void onColorClick(int color) {
                        imageButton.setBackgroundColor(color);
                    }
                });
            }
        });
    }

    private void selectorColor(final OnColorClickListener l) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        GridView gv = new GridView(mContext);
        gv.setNumColumns(4);
        gv.setAdapter(new BaseAdapter() {
            int[] colors = new int[]{Color.TRANSPARENT, 0xffffffff, 0xff000000, 0xffe51c23, 0xffE84E40, 0xff9c27b0, 0xff673ab7,
                    0xff3f51b5, 0xff5677fc, 0xff03a9f4, 0xff00bcd4, 0xff009688, 0xff259b24, 0xff8bc34a, 0xffcddc39,
                    0xffffeb3b, 0xffffc107, 0xffff9800, 0xffff5722, 0xff795548};

            @Override
            public int getCount() {
                return colors.length;
            }

            @Override
            public Object getItem(int position) {
                return colors[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View v = new View(mContext);
                v.setBackgroundColor(colors[position]);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        l.onColorClick(colors[position]);
                        dialog.dismiss();
                    }
                });
                DisplayMetrics dm = new DisplayMetrics();
                WindowManager wm = (WindowManager) mContext
                        .getSystemService(Context.WINDOW_SERVICE);
                wm.getDefaultDisplay().getMetrics(dm);
                GridView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                        (int) (dm.widthPixels / 5f));
                v.setLayoutParams(lp);
                return v;
            }
        });
        dialog.setView(gv);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x33FFFFFF));
    }

    interface OnColorClickListener {
        void onColorClick(int color);
    }
}
