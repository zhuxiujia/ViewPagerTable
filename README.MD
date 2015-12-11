ViewPagerTable:

介绍:
   4步一键完成app的主页面分页，支持任意个数的页面



如何使用?:
          /*初始化控件*/
          button_group=(ViewPagerTableBottomGroup)findViewById(R.id.button_group);//底部选择器
          tableHost=(ViewPagerTable)findViewById(R.id.tablehost);//内容ViewPager
          /*设置ViewPager4个内容页面（个数任意），*/
          tableHost.setViewLayout(R.layout.root0,R.layout.root1,R.layout.root2,R.layout.root3);

          /*底部选择器选择状态改变侦听*/
          button_group.setOnViewPagerTableGroupChangeListener(new ViewPagerTableBottomGroup.OnViewPagerTableGroupChangeListener() {
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
                   button_group.setChildChecked(position);
              }

              @Override
              public void onPageScrollStateChanged(int state) {

              }
          });