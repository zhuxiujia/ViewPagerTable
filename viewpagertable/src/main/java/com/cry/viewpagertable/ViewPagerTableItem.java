package com.cry.viewpagertable;

import android.animation.ObjectAnimator;
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
public class ViewPagerTableItem extends RelativeLayout {
    final String TAG = getClass().getSimpleName();
    private ImageView imageView_up;
    private ImageView imageView_down;
    private TextView textView_up;
    private TextView textView_down;
    private boolean checked = false;

    private int id_imageView_up = -1;
    private int id_imageView_down = -1;
    private int id_textView_up = -1;
    private int id_textView_down = -1;

    OnTouchUpListener onTouchUpListener = null;
    OnCheckedChangeWidgetListener onCheckedChangeWidgetListener = null;

    public interface OnCheckedChangeWidgetListener {
        void change(boolean checked);
    }


    public interface OnTouchUpListener {
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
            id_imageView_down = a.getResourceId(R.styleable.ViewPagerTableItem_imageview_down_id, -1);
            id_imageView_up = a.getResourceId(R.styleable.ViewPagerTableItem_imageview_up_id, -1);
            id_textView_up = a.getResourceId(R.styleable.ViewPagerTableItem_textview_up_id, -1);
            id_textView_down = a.getResourceId(R.styleable.ViewPagerTableItem_textview_down_id, -1);
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
            if (onTouchUpListener != null) onTouchUpListener.onTouchUp();
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
            if (textView_up == null) {
                textView_up = (TextView) this.findViewById(id_textView_up);
            }
        } catch (Exception e) {
        }
        try {
            if (textView_down == null) {
                textView_down = (TextView) this.findViewById(id_textView_down);
            }
        } catch (Exception e) {
        }
        try {
            if (imageView_up == null) {
                imageView_up = (ImageView) this.findViewById(id_imageView_up);
            }
        } catch (Exception e) {
        }
        try {
            if (imageView_down == null) {
                imageView_down = (ImageView) this.findViewById(id_imageView_down);
            }
        } catch (Exception e) {
        }
        invateState();
    }

    private void invateState() {
        try {
            if (checked) {
                imageView_up.setAlpha(0f);
                imageView_down.setAlpha(1f);

                textView_up.setAlpha(0f);
                textView_down.setAlpha(1f);
            } else {
                imageView_up.setAlpha(1f);
                imageView_down.setAlpha(0f);

                textView_up.setAlpha(1f);
                textView_down.setAlpha(0f);
            }
        } catch (Exception e) {
        }
    }


    public void setChecked(boolean checked) {
        if (this.checked != checked) {
            this.checked = checked;
            invate();
            if (onCheckedChangeWidgetListener != null)
                onCheckedChangeWidgetListener.change(checked);
        }
    }

    public void invate() {
        try {
            TextViewAnimation(checked);
            ImageViewAnimation(checked);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void ImageViewAnimation(boolean checked) {
        if (ViewPagerTable.config.enable_tableItemAnimation) {
            if (checked) {
                ObjectAnimator.ofFloat(imageView_up, "alpha", 1, 0).setDuration(ViewPagerTable.config.animation_time).start();
                ObjectAnimator.ofFloat(imageView_down, "alpha", 0, 1).setDuration(ViewPagerTable.config.animation_time).start();
            } else {
                ObjectAnimator.ofFloat(imageView_up, "alpha", 0, 1).setDuration(ViewPagerTable.config.animation_time).start();
                ObjectAnimator.ofFloat(imageView_down, "alpha", 1, 0).setDuration(ViewPagerTable.config.animation_time).start();
            }
        } else {
            if (checked) {
                imageView_up.setAlpha(0f);
                imageView_down.setAlpha(1f);
            } else {
                imageView_up.setAlpha(1f);
                imageView_down.setAlpha(0f);
            }
        }
    }

    private void TextViewAnimation(boolean checked) {
        if (ViewPagerTable.config.enable_tableItemAnimation) {
            if (checked) {
                ObjectAnimator.ofFloat(textView_up, "alpha", 1, 0).setDuration(ViewPagerTable.config.animation_time).start();
                ObjectAnimator.ofFloat(textView_down, "alpha", 0, 1).setDuration(ViewPagerTable.config.animation_time).start();
            } else {
                ObjectAnimator.ofFloat(textView_up, "alpha", 0, 1).setDuration(ViewPagerTable.config.animation_time).start();
                ObjectAnimator.ofFloat(textView_down, "alpha", 1, 0).setDuration(ViewPagerTable.config.animation_time).start();
            }
        } else {
            if (checked) {
                textView_up.setAlpha(0f);
                textView_down.setAlpha(1f);
            } else {
                textView_up.setAlpha(1f);
                textView_down.setAlpha(0f);
            }
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public int getId_imageView_down() {
        return id_imageView_down;
    }

    public void setId_imageView_down(int id_imageView_down) {
        this.id_imageView_down = id_imageView_down;
    }

    public int getId_imageView_up() {
        return id_imageView_up;
    }

    public void setId_imageView_up(int id_imageView_up) {
        this.id_imageView_up = id_imageView_up;
    }

    public int getId_textView_down() {
        return id_textView_down;
    }

    public void setId_textView_down(int id_textView_down) {
        this.id_textView_down = id_textView_down;
    }

    public int getId_textView_up() {
        return id_textView_up;
    }

    public void setId_textView_up(int id_textView_up) {
        this.id_textView_up = id_textView_up;
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

    public OnCheckedChangeWidgetListener getOnCheckedChangeWidgetListener() {
        return onCheckedChangeWidgetListener;
    }

    public OnTouchUpListener getOnTouchUpListener() {
        return onTouchUpListener;
    }

    public TextView getTextView_down() {
        return textView_down;
    }

    public void setTextView_down(TextView textView_down) {
        this.textView_down = textView_down;
    }

    public TextView getTextView_up() {
        return textView_up;
    }

    public void setTextView_up(TextView textView_up) {
        this.textView_up = textView_up;
    }
}
