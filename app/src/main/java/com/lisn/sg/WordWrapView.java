package com.lisn.sg;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.lisn.sg.View.FixGridLayout;

/**
 * 自动换行view
 */
public class WordWrapView extends BaseActivity {

    private Context mContext;


    @Override
    public void setContentView() {
        mContext = this;
        setContentView(R.layout.activity_word_wrap_view);
    }

    @Override
    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar,"自定义，自动换行view",true);

        FixGridLayout fixGridLayout = (FixGridLayout) findViewById(R.id.fgl);
        fixGridLayout.setmCellHeight(30);
        fixGridLayout.setmCellWidth(100);
        for (int i = 0; i < 17; i++) {
            CheckBox box = new CheckBox(WordWrapView.this);
            box.setText("第"+i+"个");
            fixGridLayout.addView(box);
        }
    }

}
