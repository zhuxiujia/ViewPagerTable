#ViewPagerTable介绍:<br />
1功能上优势：：4步一键完成app的主页面分页，支持任意个数的页面<br />
2极大的简化了大家配置TableHost等分页的繁琐代码，而且本框架可以使用其他图片框架display底部导航图片（方便自己配置皮肤什么的）
3动画-底部导航器默认有透明度渐变动画和（TextView）颜色渐变动画
![ABC](device-2015-12-11-170751.png)
##直接下载aar文件：
[点击下载aar文件](https://github.com/zhuxiujia/ViewPagerTable/viewpagertable-release.aar)
##如何使用?（具体请下载demo和库）:<br />

###Step1:         /*配置xml布局内容*/
                 <com.cry.viewpagertable.ViewPagerTable
                         android:id="@+id/tablehost"
                        />
                     <com.cry.viewpagertable.ViewPagerTableBottomGroup
                         android:id="@+id/button_group"
                     >
                         <com.cry.viewpagertable.ViewPagerTableItem
                             xmlns:ViewPagerTableItem="http://schemas.android.com/apk/res-auto"
                             ViewPagerTableItem:clolordown="0xffff6f40"
                             ViewPagerTableItem:colorUp="0xffcccccc"
                             ViewPagerTableItem:drawable_down="@mipmap/ic_launcher"<!-- 按下图片 --!>
                             ViewPagerTableItem:drawable_up="@mipmap/ic_launcher2"<!-- 抬起图片 --!>
                             ViewPagerTableItem:topimageview_id="@+id/top"<!-- 指定Item的顶部ImageView id --!>
                             ViewPagerTableItem:bottomimageview_id="@+id/bottom"<!-- 指定Item的底部ImageView id --!>
                             ViewPagerTableItem:textview_id="@+id/textView"<!-- 指定Item的TextView id --!>
                             >
                             <FrameLayout
                                 >
                                 <ImageView
                                     android:id="@+id/top"
                                     />
                                 <ImageView
                                     android:id="@+id/bottom"
                                     />
                             </FrameLayout>
                             <TextView
                                 android:id="@+id/textView"/>
                         </com.cry.viewpagertable.ViewPagerTableItem>
                         .
                         .
                         .




###Step2:            /*初始化控件*/
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
         
                   