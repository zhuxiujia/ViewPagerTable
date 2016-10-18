package com.cry.viewpagertable;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 15/12/10.
 */
public class ViewPagerTableAdapter extends PagerAdapter {
    public List<View> viewList = new ArrayList<>();
    ViewGroup cont = null;

    public List<View> getViewList() {
        return viewList;
    }

    public void setViewList(List<View> viewList) {

        try {
            for (int i = 0; i < this.viewList.size(); i++) {
                cont.removeView(this.viewList.get(i));
            }
        } catch (Exception e) {
        }
        this.viewList = viewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView(viewList.get(position));
        } catch (Exception e) {
        }
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.cont = container;
        container.addView(viewList.get(position), 0);
        return viewList.get(position);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
