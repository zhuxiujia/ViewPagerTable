package com.cry.viewpagertable;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zxj on 15/12/10.
 */
public class ViewPagerTable extends ViewPager{
    public static Config config=new Config();
   final   ViewGroup.LayoutParams layoutparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);

    public ViewPagerTable(Context context) {
        super(context);
        init();
    }

    public ViewPagerTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }
    public void setConfig(Config config){
        ViewPagerTable.config=config;
    }

    public void setViewLayout(List<View> layouts){
        ViewPagerTableAdapter viewPagerTableAdapter =new ViewPagerTableAdapter();
        setAdapter(viewPagerTableAdapter);
        viewPagerTableAdapter.setViewList(layouts);
        viewPagerTableAdapter.notifyDataSetChanged();
    }
    public void setViewLayoutFragment(FragmentManager fm, List<Fragment> layouts){
        FragmentAdapter viewPagerTableAdapter =new FragmentAdapter(fm,layouts);
        setAdapter(viewPagerTableAdapter);
        viewPagerTableAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (config.noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (config.noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

}
