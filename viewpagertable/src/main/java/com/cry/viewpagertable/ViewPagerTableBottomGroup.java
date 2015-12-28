package com.cry.viewpagertable;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 15/12/10.
 */
public class ViewPagerTableBottomGroup extends LinearLayout{
    final String TAG="Group";
    List<ViewPagerTableItem> viewPagerTableItems =new ArrayList<>();
    private void init() {
        setOrientation(HORIZONTAL);
        setOnHierarchyChangeListener(new PassThroughHierarchyChangeListener());
    }

    public static interface OnViewPagerTableGroupChangeListener{
        void onGroupChange(int index);
    }
    OnViewPagerTableGroupChangeListener onViewPagerTableGroupChangeListener=null;

    public ViewPagerTableBottomGroup(Context context) {
        super(context);
        init();
    }

    public ViewPagerTableBottomGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewPagerTableBottomGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    public void setOnViewPagerTableGroupChangeListener(OnViewPagerTableGroupChangeListener onViewPagerTableGroupChangeListener) {
        this.onViewPagerTableGroupChangeListener = onViewPagerTableGroupChangeListener;
    }



    public void setChildChecked(int index){
        if(index< viewPagerTableItems.size())
            for (int i = 0; i < viewPagerTableItems.size(); i++) {
                if (i == index) {
                    viewPagerTableItems.get(i).setChecked(true);
                    //Log.i(TAG,"true:"+index);
                } else {
                    viewPagerTableItems.get(i).setChecked(false);
                    //Log.i(TAG,"false:"+index);
                }
            }
    }

    public List<ViewPagerTableItem> getViewPagerTableItems() {
        return viewPagerTableItems;
    }




    /**
     * <p>A pass-through listener acts upon the events and dispatches them
     * to another listener. This allows the table layout to set its own internal
     * hierarchy change listener without preventing the user to setup his.</p>
     */
    private class PassThroughHierarchyChangeListener implements
            OnHierarchyChangeListener {
        int index=0;
        /**
         * {@inheritDoc}
         */
        public void onChildViewAdded(View parent, View child) {
            if (parent == ViewPagerTableBottomGroup.this && child instanceof ViewPagerTableItem) {
                int id = child.getId();
                // generates an id if it's missing
                if (id == View.NO_ID) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        id = View.generateViewId();
                    }
                    child.setId(id);
                }

                final  int p=index;
                viewPagerTableItems.add((ViewPagerTableItem) child);
                viewPagerTableItems.get(p).setOnTouchUpListener(new ViewPagerTableItem.OnTouchUpListener() {
                    @Override
                    public void onTouchUp() {
                        setChildChecked(p);
                        if(onViewPagerTableGroupChangeListener!=null)onViewPagerTableGroupChangeListener.onGroupChange(p);
                    }
                });
                index++;
                if(p==0){viewPagerTableItems.get(0).setChecked(true);}
            }


        }

        /**
         * {@inheritDoc}
         */
        public void onChildViewRemoved(View parent, View child) {
            if (parent == ViewPagerTableBottomGroup.this && child instanceof ViewPagerTableItem) {
                ((ViewPagerTableItem) child).setOnCheckedChangeWidgetListener(null);
            }
        }}
}
