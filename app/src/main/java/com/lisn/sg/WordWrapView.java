package com.lisn.sg;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.lisn.sg.Utils.ScreenInfo;
import com.lisn.sg.View.FixGridLayout;

/**
 * 自动换行view
 */
public class WordWrapView extends BaseActivity {

    private Activity mContext;


    @Override
    public void setContentView() {
        mContext = this;
        setContentView(R.layout.activity_word_wrap_view);
    }

    @Override
    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar,"自定义，自动换行view",true);

        FixGridLayout fixGridLayout = (FixGridLayout) findViewById(R.id.fgl1);
        ScreenInfo screenInfo = new ScreenInfo(mContext);
        fixGridLayout.setmCellHeight(80);
        fixGridLayout.setmCellWidth(screenInfo.getWidth()/4);
        for (int i = 0; i < 17; i++) {
            CheckBox box = new CheckBox(WordWrapView.this);
            box.setText("第"+i+"个");
            fixGridLayout.addView(box);
        }
        FixGridLayout fixGridLayout2 = (FixGridLayout) findViewById(R.id.fgl2);
        fixGridLayout2.setmCellHeight(100);
        fixGridLayout2.setmCellWidth(100);
        for (int i = 0; i < 12; i++) {
            ImageView imageView = new ImageView(mContext);
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
            imageView.setBackground(drawable);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "点击了第"+ finalI +"个图片", Toast.LENGTH_SHORT).show();
                }
            });
            fixGridLayout2.addView(imageView);
        }
    }

}
