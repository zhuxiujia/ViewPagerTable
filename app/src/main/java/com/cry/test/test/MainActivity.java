package com.cry.test.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.cry.test.R;
import com.cry.viewpagertable.ViewPagerTable;
import com.cry.viewpagertable.ViewPagerTableBottomGroup;
import com.cry.viewpagertable.ViewPagerTableItem;

public class MainActivity extends Activity {
    ViewPagerTable tableHost;
    ViewPagerTableBottomGroup table_bottom_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*初始化控件*/
        table_bottom_group =(ViewPagerTableBottomGroup)findViewById(R.id.button_group);//底部选择器
        tableHost=(ViewPagerTable)findViewById(R.id.tablehost);//内容ViewPager
        /*设置ViewPager4个内容页面（个数任意），*/
        tableHost.setViewLayout(R.layout.root0,R.layout.root1,R.layout.root2,R.layout.root3);

        /*底部选择器选择状态改变侦听*/
        table_bottom_group.setOnViewPagerTableGroupChangeListener(new ViewPagerTableBottomGroup.OnViewPagerTableGroupChangeListener() {
            @Override
            public void onGroupChange(int index) {
                tableHost.setCurrentItem(index);//设置ViewPager页面切换
            }
        });
        /*ViewPager页面切换事件侦听*/
        tableHost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 table_bottom_group.setChildChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ViewPagerTableItem.setAnimation_time(600);//底部选择器动画时间
    }


    /*取内部ImageView功能举例*/
    private void getLocalImageView(){
        table_bottom_group.getViewPagerTableItems().get(0).getImageViewTop().setImageResource(R.mipmap.ic_launcher);
        /*如果你不满足或者说配置一个底部图片皮肤什么的，可以使用其他图片框架display 内部ImageView*/
        //例如Picasso框架：Picasso.display(table_bottom_group.getViewPagerTableItems().get(0).getImageViewTop(),"http:www.abc.aa/a.png.....");
    }
}
