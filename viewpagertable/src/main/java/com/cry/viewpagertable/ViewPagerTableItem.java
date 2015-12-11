package com.cry.viewpagertable;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * Created by zxj on 15/12/10.
 */
public class ViewPagerTableItem extends RelativeLayout{
    final String TAG = "ds";
    private ImageView imageView_up;
    private ImageView imageView_down;
    private TextView textView;
    private boolean checked = false;
    private static int animation_time =600;

    private int clolor_down = 0xff0000ff;
    private int clolor_up = 0xffccccff;
    private int drawable_down = -1;
    private int drawable_up = -1;

    private int id_imageView_up =-1;
    private int id_imageView_down =-1;
    private int id_textView=-1;

    OnTouchUpListener onTouchUpListener=null;
    OnCheckedChangeWidgetListener onCheckedChangeWidgetListener=null;
    public  interface OnCheckedChangeWidgetListener{
        void change(boolean checked);
    }


    public  interface OnTouchUpListener{
         void onTouchUp();
    }

    public ViewPagerTableItem(Context context) {
        super(context);
        init();
    }

    public ViewPagerTableItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public ViewPagerTableItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPagerTableItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
        init();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerTableItem);
            clolor_down = a.getInt(R.styleable.ViewPagerTableItem_clolordown, 0xff0000ff);
            clolor_up = a.getInt(R.styleable.ViewPagerTableItem_colorUp, 0xffccccff);
            drawable_down = a.getResourceId(R.styleable.ViewPagerTableItem_drawable_down, -1);
            drawable_up = a.getResourceId(R.styleable.ViewPagerTableItem_drawable_up, -1);
            id_imageView_down =a.getResourceId(R.styleable.ViewPagerTableItem_imageview_down_id,-1);
            id_imageView_up =a.getResourceId(R.styleable.ViewPagerTableItem_imageview_up_id,-1);
            id_textView=a.getResourceId(R.styleable.ViewPagerTableItem_textview_id,-1);
            a.recycle();
        } catch (Exception e) {
        }
    }

    private void init() {
        setClickable(true);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        findAllChildViews();
    }

    public void setOnTouchUpListener(OnTouchUpListener onTouchUpListener) {
        this.onTouchUpListener = onTouchUpListener;
    }

    public void setOnCheckedChangeWidgetListener(OnCheckedChangeWidgetListener onCheckedChangeWidgetListener) {
        this.onCheckedChangeWidgetListener = onCheckedChangeWidgetListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if(onTouchUpListener!=null)onTouchUpListener.onTouchUp();
            setChecked(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
       //if(changed) findAllChildViews();

    }

    /*多次调用，所以需要判断*/
    private void findAllChildViews() {
        try {
            if (textView == null) {
                textView = (TextView) this.findViewById(id_textView);
            }
        } catch (Exception e) {
        }
        try {
            if (imageView_up == null) {
                imageView_up = (ImageView) this.findViewById(id_imageView_up);
                imageView_up.setImageResource(drawable_up);
            }
        } catch (Exception e) {
        }
        try {
            if (imageView_down == null) {
                imageView_down = (ImageView) this.findViewById(id_imageView_down);
                imageView_down.setImageResource(drawable_down);
            }
        } catch (Exception e) {
        }
        invateState();
    }

    private void invateState() {
        try {
            if (checked) {
                textView.setTextColor(clolor_down);
                imageView_up.setAlpha(0f);
                imageView_down.setAlpha(1f);
            } else {
                textView.setTextColor(clolor_up);
                imageView_up.setAlpha(1f);
                imageView_down.setAlpha(0f);
            }
        }catch (Exception e){}
    }


    public void setChecked(boolean checked) {
        if(this.checked!=checked) {
            this.checked = checked;
            invate();
            if(onCheckedChangeWidgetListener!=null)onCheckedChangeWidgetListener.change(checked);
        }
    }

    public void invate() {
        try {
            if (checked) {
                ColorAnimation(clolor_up, clolor_down);
                DrawableAnimation(checked);
            } else {
                ColorAnimation(clolor_down, clolor_up);
                DrawableAnimation(checked);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void DrawableAnimation(boolean checked) {
       if(checked){
           ObjectAnimator.ofFloat(imageView_up,"alpha",1,0).setDuration(animation_time).start();
           ObjectAnimator.ofFloat(imageView_down,"alpha",0,1).setDuration(animation_time).start();
       }else {
           ObjectAnimator.ofFloat(imageView_up,"alpha",0,1).setDuration(animation_time).start();
           ObjectAnimator.ofFloat(imageView_down,"alpha",1,0).setDuration(animation_time).start();
       }
    }

    private void ColorAnimation(int form, int to) {
        Integer colorFrom = form;
        Integer colorTo = to;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(animation_time);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setTextColor((Integer) animation.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }


    /*get set*/

    public int getDrawable_down() {
        return drawable_down;
    }

    public void setDrawable_down(int drawable_down) {
        this.drawable_down = drawable_down;
    }

    public int getDrawable_up() {
        return drawable_up;
    }

    public void setDrawable_up(int drawable_up) {
        this.drawable_up = drawable_up;
    }

    public int getClolor_up() {
        return clolor_up;
    }

    public void setClolor_up(int clolor_up) {
        this.clolor_up = clolor_up;
    }

    public int getClolor_down() {
        return clolor_down;
    }

    public void setClolor_down(int clolor_down) {
        this.clolor_down = clolor_down;
    }

    public boolean isChecked() {
        return checked;
    }

    public ImageView getImageView_down() {
        return imageView_down;
    }

    public void setImageView_down(ImageView imageView_down) {
        this.imageView_down = imageView_down;
    }

    public ImageView getImageView_up() {
        return imageView_up;
    }

    public void setImageView_up(ImageView imageView_up) {
        this.imageView_up = imageView_up;
    }

    public OnTouchUpListener getOnTouchUpListener() {
        return onTouchUpListener;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public static void setAnimation_time(int animation_time) {
        ViewPagerTableItem.animation_time = animation_time;
    }

    public static int getAnimation_time() {
        return animation_time;
    }
}
