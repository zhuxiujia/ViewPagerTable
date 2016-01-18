package com.cry.viewpagertable;

/**
 * Created by zxj on 15/12/30.
 */
public class Config {
    public  int animation_time =300;
    public  boolean enable_tableItemAnimation =true;

    public void setAnimation_time(int animation_time) {
        this.animation_time = animation_time;
    }

    public int getAnimation_time() {
        return animation_time;
    }

    public void setEnable_tableItemAnimation(boolean enable_tableItemAnimation) {
        this.enable_tableItemAnimation = enable_tableItemAnimation;
    }

    public boolean isEnable_tableItemAnimation() {
        return enable_tableItemAnimation;
    }


}
