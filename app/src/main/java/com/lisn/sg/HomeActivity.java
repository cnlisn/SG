package com.lisn.sg;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.lisn.sg.Fragment.HomeFragment;

public class HomeActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment currentFragment;
    private int currentPos = -1;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar,"标题",false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle DrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        DrawerToggle.syncState();
        selectItem(1);
    }

    @Override
    public void initEvents() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.my_navigation_0:
                        selectItem(1);
                        break;
                }
                drawerLayout.closeDrawer(Gravity.START);
                return true;
            }
        });
    }

    private void selectItem(int pos) {
        //点击的正是当前正在显示的，直接返回
        if (currentPos == pos) return;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (currentFragment != null) {
            //隐藏当前正在显示的fragment
            transaction.hide(currentFragment);
        }
        currentPos = pos;
        Fragment fragment = manager.findFragmentByTag(getTag(pos));
        //通过findFragmentByTag判断是否已存在目标fragment，若存在直接show，否则去add
        if (fragment != null) {
            currentFragment = fragment;
            transaction.show(fragment);
        } else {
            transaction.add(R.id.fl_container, getFragment(pos), getTag(pos));
        }
        transaction.commitAllowingStateLoss();
    }

    private String getTag(int pos) {
        return "fg_tag_" + pos;
    }

    private Fragment getFragment(int pos) {
        switch (pos) {
            case 0:
                currentFragment = new HomeFragment();
                break;
//            case 1:
//                currentFragment = new PopFragment();
//                break;
//            case 2:
//                currentFragment = new ProcessFragment();
//                break;
//            case 3:
//                currentFragment = new RecycleFragment();
//                break;
//            case 4:
//                currentFragment = new OtherFragment();
//                break;
            default:
                currentFragment = new HomeFragment();
                break;
        }
        return currentFragment;
    }
}
