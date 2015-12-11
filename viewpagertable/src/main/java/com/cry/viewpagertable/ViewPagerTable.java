package com.cry.viewpagertable;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zxj on 15/12/10.
 */
public class ViewPagerTable extends ViewPager{
  final   ViewGroup.LayoutParams layoutparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
    ViewPagerTableAdapter viewPagerTableAdapter =new ViewPagerTableAdapter();
    public ViewPagerTable(Context context) {
        super(context);
        init();
    }

    public ViewPagerTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setAdapter(viewPagerTableAdapter);
    }


    public void setViewLayout(int... layouts){
        viewPagerTableAdapter.getViewList().clear();
        viewPagerTableAdapter.notifyDataSetChanged();
       for (int i=0;i<layouts.length;i++){
           View view=LayoutInflater.from(getContext()).inflate(layouts[i],null);
           view.setLayoutParams(layoutparams);
           viewPagerTableAdapter.getViewList().add(view);
       }
        viewPagerTableAdapter.notifyDataSetChanged();
    }
    public void setViewLayout(View... layouts){
        viewPagerTableAdapter.getViewList().clear();
        viewPagerTableAdapter.notifyDataSetChanged();
        for (int i=0;i<layouts.length;i++){
            layouts[i].setLayoutParams(layoutparams);
            viewPagerTableAdapter.getViewList().add(layouts[i]);
        }
        viewPagerTableAdapter.notifyDataSetChanged();
    }
    public void setViewLayout(List<View> layouts){
        viewPagerTableAdapter.getViewList().clear();
        viewPagerTableAdapter.notifyDataSetChanged();
        viewPagerTableAdapter.setViewList(layouts);
        viewPagerTableAdapter.notifyDataSetChanged();
    }

    public View getPagerView(int index){
       return viewPagerTableAdapter.getViewList().get(index);
    }

    public List<View> getPagerViewList(){
        return viewPagerTableAdapter.getViewList();
    }

    public ViewPagerTableAdapter getViewPagerTableAdapter() {
        return viewPagerTableAdapter;
    }

    @Override
    @Deprecated
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }
}
