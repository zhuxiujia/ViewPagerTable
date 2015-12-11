package com.cry.test.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.cry.test.R;
import com.cry.viewpagertable.ViewPagerTable;
import com.cry.viewpagertable.ViewPagerTableBottomGroup;
import com.cry.viewpagertable.ViewPagerTableItem;

public class MainActivity extends Activity {
    ViewPagerTable tableHost;
    ViewPagerTableBottomGroup button_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_group=(ViewPagerTableBottomGroup)findViewById(R.id.button_group);
        tableHost=(ViewPagerTable)findViewById(R.id.tablehost);
        tableHost.setViewLayout(R.layout.root0,R.layout.root1,R.layout.root2,R.layout.root3);


        button_group.setOnViewPagerTableGroupChangeListener(new ViewPagerTableBottomGroup.OnViewPagerTableGroupChangeListener() {
            @Override
            public void onGroupChange(int index) {
                tableHost.setCurrentItem(index);
            }
        });
        tableHost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 button_group.setChildChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ViewPagerTableItem.setAnimation_time(200);
        Log.i("ds","s");
    }
}
