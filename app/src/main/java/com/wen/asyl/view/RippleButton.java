package com.wen.asyl.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.wen.asyl.rippledrawabledemo.R;


/**
 * Description：xx <br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-12 10:54
 *
 * @author 姜文莒
 * @version : 1.0
 */
public class RippleButton extends Button {
  private RippleDrawable mRippleDrawable;
    public RippleButton(Context context) {
        this(context,null);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRippleDrawable=new RippleDrawable();
        //设置刷新接口，View中已经实现
        mRippleDrawable.setCallback(this);
       // setBackgroundDrawable(new RippleDrawable());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置Drawable绘制和刷新的区域
        mRippleDrawable.setBounds(0,0,getWidth(),getHeight());
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable who) {
        //验证Drawable是否ok
        return who==mRippleDrawable||super.verifyDrawable(who);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制自己的drawable
        mRippleDrawable.draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mRippleDrawable.onTouch(event);
        return super.onTouchEvent(event);
    }
}
